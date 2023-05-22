package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.codeElement.ClassField;
import com.simmondobber.lomboker.codeElement.ClassMethod;
import com.simmondobber.lomboker.codeElement.CodeLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.simmondobber.lomboker.Keywords.STATIC;

@Service
public class LombokizeService {

    public String lombokize(String classCode) {
        classCode = standardizeClassCodeWhitespaces(classCode);
        List<ClassMethod> gettersAndSettersContainedByClass = getGettersAndSetterContainedByClass(classCode);
        classCode = deleteGettersAndSettersFromClassCode(classCode, gettersAndSettersContainedByClass);
        classCode = addLombokAnnotationsToClassCode(classCode, gettersAndSettersContainedByClass);
        return classCode;
    }

    private String standardizeClassCodeWhitespaces(String classCode) {
        return classCode.replaceAll("\t", "    ");
    }

    private List<ClassMethod> getGettersAndSetterContainedByClass(String classCode) {
        List<ClassMethod> gettersAndSetters = getGettersAndSettersBasedOnClassFieldsInClassCode(classCode);
        return gettersAndSetters.stream()
                .filter(method -> classCode.contains(method.getMethodCode()))
                .toList();
    }

    private List<ClassMethod> getGettersAndSettersBasedOnClassFieldsInClassCode(String classCode) {
        List<ClassField> classFields = getClassFieldsFromClassCode(classCode);
        return classFields.stream()
                .map(field -> List.of(field.getCorrespondingGetter(), field.getCorrespondingSetter()))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ClassField> getClassFieldsFromClassCode(String classCode) {
        List<CodeLine> classCodeLines = mapClassCodeToCodeLines(classCode);
        List<CodeLine> classFieldsCodeLines = filterNonStaticClassFieldsFromCodeLines(classCodeLines);
        return createClassFieldsFromCodeLines(classFieldsCodeLines);
    }

    private List<CodeLine> mapClassCodeToCodeLines(String classCode) {
        return Arrays.stream(classCode.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(CodeLine::new)
                .toList();
    }

    private List<CodeLine> filterNonStaticClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return codeLines.stream()
                .filter(CodeLine::isClassField)
                .filter(this::isClassFieldNonStatic)
                .toList();
    }

    private boolean isClassFieldNonStatic(CodeLine classField) {
        return !classField.getLine().contains(" " + STATIC.getKeyword() + " ");
    }

    private List<ClassField> createClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return getCodeLinesAsStrings(codeLines).stream()
                .map(ClassField::new)
                .toList();
    }

    private List<String> getCodeLinesAsStrings(List<CodeLine> codeLines) {
        return codeLines.stream()
                .map(CodeLine::getLine)
                .toList();
    }

    private String deleteGettersAndSettersFromClassCode(String classCode, List<ClassMethod> gettersAndSetters) {
        List<String> gettersAndSettersCodeWithNewlinePrefix = mapGettersAndSettersToCodesWithNewlinePrefix(gettersAndSetters);
        String[] gettersAndSettersAsArray = getGettersAndSettersCodeAsArray(gettersAndSettersCodeWithNewlinePrefix);
        String[] emptyStringsArray = createArrayOfEmptyStringsOfGivenLength(gettersAndSetters.size());
        return StringUtils.replaceEach(classCode, gettersAndSettersAsArray, emptyStringsArray);
    }

    private List<String> mapGettersAndSettersToCodesWithNewlinePrefix(List<ClassMethod> gettersAndSetters) {
        return gettersAndSetters.stream()
                .map(ClassMethod::getMethodCode)
                .map(method -> "\n" + method)
                .toList();
    }

    private String[] getGettersAndSettersCodeAsArray(List<String> gettersAndSettersCode) {
        return gettersAndSettersCode.toArray(new String[0]);
    }

    private String[] createArrayOfEmptyStringsOfGivenLength(int length) {
        return Collections.nCopies(length, "").toArray(new String[0]);
    }

    private String addLombokAnnotationsToClassCode(String classCode, List<ClassMethod> gettersAndSetters) {
        for (ClassMethod method : gettersAndSetters) {
            classCode = addMethodAnnotationToClassCode(classCode, method);
        }
        return classCode;
    }

    private String addMethodAnnotationToClassCode(String classCode, ClassMethod method) {
        int indexOfClassFieldInClassCode = classCode.indexOf(method.getCorrespondingField().getLine());
        String firstPartOfClassCode = classCode.substring(0, indexOfClassFieldInClassCode);
        String secondPartOfClassCode = classCode.substring(indexOfClassFieldInClassCode);
        return StringUtils.join(firstPartOfClassCode, "    ", method.getMethodType().getAnnotation(), "\n", secondPartOfClassCode);
    }
}
