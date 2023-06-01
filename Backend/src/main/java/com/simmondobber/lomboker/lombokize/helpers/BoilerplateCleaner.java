package com.simmondobber.lomboker.lombokize.helpers;

import com.simmondobber.lomboker.lombokize.codeElement.ClassHeader;
import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import com.simmondobber.lomboker.lombokize.enums.Annotations;
import com.simmondobber.lomboker.lombokize.helpers.extractors.ClassExtractor;
import com.simmondobber.lomboker.lombokize.helpers.extractors.MethodsExtractor;
import com.simmondobber.lomboker.lombokize.helpers.factories.AnnotationFactory;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class BoilerplateCleaner {

    private final MethodsExtractor methodsExtractor;
    private final ClassExtractor classExtractor;
    private final AnnotationFactory annotationFactory;

    public BoilerplateCleaner() {
        this.methodsExtractor = new MethodsExtractor();
        this.classExtractor = new ClassExtractor();
        this.annotationFactory = new AnnotationFactory();
    }

    public String clearClassCodeFromBoilerplate(String classCode, boolean thisPrefix, AnnotationsConfig annotationsConfig) {
        List<ClassMethod> gettersAndSetters = this.methodsExtractor.getGettersAndSettersContainedByClass(classCode, thisPrefix);
        classCode = deleteGettersAndSettersFromClassCode(classCode, gettersAndSetters);
        classCode = addMethodAnnotationsToClassCode(classCode, gettersAndSetters);
        classCode = deleteRedundantAnnotationsFromClassCode(classCode, annotationsConfig);
        classCode = addGlobalAnnotationsToClassCode(classCode, annotationsConfig);
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

    private String addGlobalAnnotationsToClassCode(String classCode, AnnotationsConfig annotationsConfig) {
        ClassHeader classHeader = this.classExtractor.getMostOuterClassHeaderFromClassCode(classCode);
        int classHeaderIndex = classCode.indexOf(classHeader.getHeaderLine());
        String annotations = this.annotationFactory.createAnnotations(annotationsConfig, classHeader.getClassAnnotations());
        String firstPartOfClassCode = classCode.substring(0, classHeaderIndex);
        String secondPartOfClassCode = classCode.substring(classHeaderIndex);
        return StringUtils.join(firstPartOfClassCode, annotations, secondPartOfClassCode);
    }

    private String deleteRedundantAnnotationsFromClassCode(String classCode, AnnotationsConfig annotationsConfig) {
        if (annotationsConfig.isGlobalGetter()) {
            classCode = classCode.replaceAll(getGetterKeywordWithNewLineAndIndentPrefix(), "");
        }
        if (annotationsConfig.isGlobalSetter()) {
            classCode = classCode.replaceAll(getSetterKeywordWithNewLineAndIndentPrefix(), "");
        }
        return classCode;
    }

    private String getGetterKeywordWithNewLineAndIndentPrefix() {
        return "\n    " + Annotations.GETTER.getKeyword();
    }

    private String getSetterKeywordWithNewLineAndIndentPrefix() {
        return "\n    " + Annotations.SETTER.getKeyword();
    }
}
