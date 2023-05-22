package com.simmondobber.lomboker.lombokize.helpers;

import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class BoilerplateCleaner {

    public String clearClassCodeFromBoilerPlate(String classCode, List<ClassMethod> gettersAndSetters) {
        classCode = deleteGettersAndSettersFromClassCode(classCode, gettersAndSetters);
        classCode = addMethodAnnotationsToClassCode(classCode, gettersAndSetters);
        return classCode;
    }

    private String deleteGettersAndSettersFromClassCode(String classCode, List<ClassMethod> gettersAndSetters) {
        List<String> gettersAndSettersCodeWithNewlinePrefix = mapMethodsToCodesWithNewlinePrefix(gettersAndSetters);
        String[] gettersAndSettersAsArray = getMethodsCodeAsArray(gettersAndSettersCodeWithNewlinePrefix);
        String[] emptyStringsArray = createArrayOfEmptyStringsOfGivenLength(gettersAndSetters.size());
        return StringUtils.replaceEach(classCode, gettersAndSettersAsArray, emptyStringsArray);
    }

    private List<String> mapMethodsToCodesWithNewlinePrefix(List<ClassMethod> gettersAndSetters) {
        return gettersAndSetters.stream()
                .map(ClassMethod::getMethodCode)
                .map(method -> "\n" + method)
                .toList();
    }

    private String[] getMethodsCodeAsArray(List<String> gettersAndSettersCode) {
        return gettersAndSettersCode.toArray(new String[0]);
    }

    private String[] createArrayOfEmptyStringsOfGivenLength(int length) {
        return Collections.nCopies(length, "").toArray(new String[0]);
    }

    private String addMethodAnnotationsToClassCode(String classCode, List<ClassMethod> gettersAndSetters) {
        for (ClassMethod method : gettersAndSetters) {
            classCode = addMethodAnnotationToClassCode(classCode, method);
        }
        return classCode;
    }

    private String addMethodAnnotationToClassCode(String classCode, ClassMethod method) {
        int indexOfClassFieldInClassCode = classCode.indexOf(method.getCorrespondingFieldCode());
        String firstPartOfClassCode = classCode.substring(0, indexOfClassFieldInClassCode);
        String secondPartOfClassCode = classCode.substring(indexOfClassFieldInClassCode);
        return StringUtils.join(firstPartOfClassCode, "    ", method.getMethodType().getAnnotation(), "\n", secondPartOfClassCode);
    }
}
