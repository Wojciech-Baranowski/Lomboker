package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.codeLine.ClassField;
import com.simmondobber.lomboker.codeLine.CodeLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static com.simmondobber.lomboker.Keywords.STATIC;

@Service
public class LombokizeService {

    public String lombokize(String classCode) {
        String classCodeWithStandardWhitespaces = getClassCodeWithStandardWhitespaces(classCode);
        List<CodeLine> classCodeLines = mapClassCodeToCodeLines(classCodeWithStandardWhitespaces);
        List<ClassField> classFields = filterAndMapCodeLinesToClassFields(classCodeLines);
        List<String> gettersAndSetters = mapClassFieldsToGettersAndSetters(classFields);
        return getClassCodeWithoutGettersAndSetters(classCode, gettersAndSetters);
    }

    private String getClassCodeWithoutGettersAndSetters(String classCode, List<String> gettersAndSetters) {
        List<String> gettersAndSettersWithNewlinePrefix = appendNewlinePrefixToGettersAndSetters(gettersAndSetters);
        String[] gettersAndSettersAsArray = getGettersAndSettersAsArray(gettersAndSettersWithNewlinePrefix);
        String[] emptyStringsArray = createArrayOfEmptyStringsOfGivenLength(gettersAndSetters.size());
        return StringUtils.replaceEach(classCode, gettersAndSettersAsArray, emptyStringsArray);
    }

    private int countGeneratedMethodsContainedInClassCode(Collection<String> methods, String classCode) {
        return (int) methods.stream()
                .filter(classCode::contains)
                .count();
    }

    private Integer getCodeLineNumber(List<CodeLine> classCodeLines, String codeLine) {
        return IntStream.range(0, classCodeLines.size())
                .boxed()
                .filter(i -> codeLine.equals(classCodeLines.get(i).getLine()))
                .findFirst()
                .orElse(null);
    }

    private List<CodeLine> mapClassCodeToCodeLines(String classCode) {
        return Arrays.stream(classCode.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(CodeLine::new)
                .toList();
    }

    private List<String> mapClassFieldsToGettersAndSetters(List<ClassField> classFields) {
        return classFields.stream()
                .map(field -> List.of(field.getGetter(), field.getSetter()))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ClassField> filterAndMapCodeLinesToClassFields(List<CodeLine> codeLines) {
        List<CodeLine> classFieldsCodeLines = filterNonStaticClassFieldsFromCodeLines(codeLines);
        return createClassFieldsFromCodeLines(classFieldsCodeLines);
    }

    private List<CodeLine> filterNonStaticClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return codeLines.stream()
                .filter(CodeLine::isClassField)
                .filter(this::isClassFieldNonStatic)
                .toList();
    }

    private List<ClassField> createClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return getCodeLinesAsStrings(codeLines).stream()
                .map(ClassField::new)
                .toList();
    }

    private String[] createArrayOfEmptyStringsOfGivenLength(int length) {
        return Collections.nCopies(length, "").toArray(new String[0]);
    }

    private List<String> appendNewlinePrefixToGettersAndSetters(List<String> gettersAndSetters) {
        return gettersAndSetters.stream()
                .map(method -> "\n" + method)
                .toList();
    }

    private List<String> getCodeLinesAsStrings(List<CodeLine> codeLines) {
        return codeLines.stream()
                .map(CodeLine::getLine)
                .toList();
    }

    private String getClassCodeWithStandardWhitespaces(String classCode) {
        return classCode.replaceAll("\t", "    ");
    }

    private String[] getGettersAndSettersAsArray(List<String> gettersAndSetters) {
        return gettersAndSetters.toArray(new String[0]);
    }

    private boolean isClassFieldNonStatic(CodeLine classField) {
        return !classField.getLine().contains(" " + STATIC.getKeyword() + " ");
    }
}
