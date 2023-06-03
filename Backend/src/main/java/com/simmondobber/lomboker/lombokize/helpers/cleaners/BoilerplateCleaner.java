package com.simmondobber.lomboker.lombokize.helpers.cleaners;

import com.simmondobber.lomboker.lombokize.classElements.Header;
import com.simmondobber.lomboker.lombokize.classElements.Method;
import com.simmondobber.lomboker.lombokize.enums.Annotation;
import com.simmondobber.lomboker.lombokize.helpers.extractors.AnnotationExtractor;
import com.simmondobber.lomboker.lombokize.helpers.extractors.MethodExtractor;
import com.simmondobber.lomboker.lombokize.helpers.extractors.classExtractor.ClassHeaderExtractor;
import com.simmondobber.lomboker.lombokize.helpers.factories.AnnotationFactory;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BoilerplateCleaner {

    private final MethodExtractor methodExtractor;
    private final ClassHeaderExtractor classHeaderExtractor;
    private final AnnotationExtractor annotationExtractor;
    private final AnnotationFactory annotationFactory;

    public BoilerplateCleaner() {
        this.methodExtractor = new MethodExtractor();
        this.classHeaderExtractor = new ClassHeaderExtractor();
        this.annotationExtractor = new AnnotationExtractor();
        this.annotationFactory = new AnnotationFactory();
    }

    public String clearClassCodeFromBoilerplateAndAddChosenAnnotations(String classCode, AnnotationsConfig annotationsConfig, boolean thisPrefix) {
        List<Method> gettersAndSetters = this.methodExtractor.getGettersAndSettersContainedByClass(classCode, thisPrefix);
        classCode = deleteGettersAndSettersFromClassCode(classCode, gettersAndSetters);
        classCode = addMethodAnnotationsToClassCode(classCode, gettersAndSetters);
        classCode = deleteRedundantAnnotationsFromClassCode(classCode, annotationsConfig);
        classCode = addGlobalAnnotationsToClassCode(classCode, annotationsConfig);
        return classCode;
    }

    private String deleteGettersAndSettersFromClassCode(String classCode, List<Method> gettersAndSetters) {
        List<String> gettersAndSettersCodeWithNewlinePrefix = mapMethodsToCodesWithNewlinePrefix(gettersAndSetters);
        String[] gettersAndSettersAsArray = getMethodsCodeAsArray(gettersAndSettersCodeWithNewlinePrefix);
        String[] emptyStringsArray = createArrayOfEmptyStringsOfGivenLength(gettersAndSetters.size());
        return StringUtils.replaceEach(classCode, gettersAndSettersAsArray, emptyStringsArray);
    }

    private List<String> mapMethodsToCodesWithNewlinePrefix(List<Method> gettersAndSetters) {
        return gettersAndSetters.stream()
                .map(Method::getMethodCode)
                .map(method -> "\n" + method)
                .toList();
    }

    private String[] getMethodsCodeAsArray(List<String> gettersAndSettersCode) {
        return gettersAndSettersCode.toArray(new String[0]);
    }

    private String[] createArrayOfEmptyStringsOfGivenLength(int length) {
        return Collections.nCopies(length, "").toArray(new String[0]);
    }

    private String addMethodAnnotationsToClassCode(String classCode, List<Method> gettersAndSetters) {
        for (Method method : gettersAndSetters) {
            classCode = addMethodAnnotationToClassCode(classCode, method);
        }
        return classCode;
    }

    private String addMethodAnnotationToClassCode(String classCode, Method method) {
        int indexOfClassFieldInClassCode = classCode.indexOf(method.getCorrespondingFieldCode());
        String firstPartOfClassCode = classCode.substring(0, indexOfClassFieldInClassCode);
        String secondPartOfClassCode = classCode.substring(indexOfClassFieldInClassCode);
        return StringUtils.join(firstPartOfClassCode, "    ", method.getMethodType().getAnnotation(), "\n", secondPartOfClassCode);
    }

    private String addGlobalAnnotationsToClassCode(String classCode, AnnotationsConfig annotationsConfig) {
        Header header = this.classHeaderExtractor.getOuterClassHeader(classCode);
        Set<Annotation> excludedAnnotations = this.annotationExtractor.getAllAnnotationsDisjointWithGivenAnnotationSet(header.getClassAnnotations());
        String annotations = this.annotationFactory.createAnnotations(annotationsConfig, excludedAnnotations);
        return insertGlobalAnnotations(classCode, annotations, header);
    }

    private String insertGlobalAnnotations(String classCode, String annotations, Header header) {
        int classHeaderIndex = classCode.indexOf(header.getLine());
        String firstPartOfClassCode = classCode.substring(0, classHeaderIndex);
        String secondPartOfClassCode = classCode.substring(classHeaderIndex);
        return StringUtils.join(firstPartOfClassCode, annotations, secondPartOfClassCode);
    }

    private String deleteRedundantAnnotationsFromClassCode(String classCode, AnnotationsConfig annotationsConfig) {
        if (annotationsConfig.isGetter()) {
            classCode = classCode.replaceAll(getGetterKeywordWithNewLineAndIndentPrefix(), "");
        }
        if (annotationsConfig.isSetter()) {
            classCode = classCode.replaceAll(getSetterKeywordWithNewLineAndIndentPrefix(), "");
        }
        return classCode;
    }

    private String getGetterKeywordWithNewLineAndIndentPrefix() {
        return "\n    " + Annotation.GETTER.getSymbol();
    }

    private String getSetterKeywordWithNewLineAndIndentPrefix() {
        return "\n    " + Annotation.SETTER.getSymbol();
    }
}
