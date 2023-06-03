package com.simmondobber.lomboker.lombokize.helpers.cleaners;

import com.simmondobber.lomboker.lombokize.classElements.Method;
import com.simmondobber.lomboker.lombokize.classElements.MethodType;
import com.simmondobber.lomboker.lombokize.helpers.extractors.methodExtractor.MethodExtractor;
import com.simmondobber.lomboker.lombokize.helpers.factories.AnnotationFactory;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class BoilerplateCleaner {

    private final MethodExtractor methodExtractor;
    private final AnnotationFactory annotationFactory;

    public BoilerplateCleaner() {
        this.methodExtractor = new MethodExtractor();
        this.annotationFactory = new AnnotationFactory();
    }

    public String clearClassCodeFromBoilerplateAndAddChosenAnnotations(String classCode, AnnotationsConfig annotationsConfig, boolean thisPrefix) {
        List<Method> gettersAndSetters = this.methodExtractor.getGettersAndSettersContainedByClass(classCode, thisPrefix);
        classCode = deleteGettersAndSetters(classCode, gettersAndSetters);
        classCode = addAnnotations(classCode, gettersAndSetters, annotationsConfig);
        return classCode;
    }

    private String deleteGettersAndSetters(String classCode, List<Method> gettersAndSetters) {
        List<String> gettersAndSettersCodeWithNewlinePrefix = mapMethodsToCodesWithNewlinePrefix(gettersAndSetters);
        String[] gettersAndSettersAsArray = gettersAndSettersCodeWithNewlinePrefix.toArray(new String[0]);
        String[] emptyStringsArray = Collections.nCopies(gettersAndSetters.size(), "").toArray(new String[0]);
        return StringUtils.replaceEach(classCode, gettersAndSettersAsArray, emptyStringsArray);
    }

    private List<String> mapMethodsToCodesWithNewlinePrefix(List<Method> gettersAndSetters) {
        return gettersAndSetters.stream()
                .map(Method::getMethodCode)
                .map(method -> "\n" + method)
                .toList();
    }

    private String addAnnotations(String classCode, List<Method> gettersAndSetters, AnnotationsConfig annotationsConfig) {
        for (Method method : gettersAndSetters) {
            if (!isMethodCoveredByGlobalAnnotation(method, annotationsConfig)) {
                classCode = addMethodAnnotation(classCode, method);
            }
        }
        return this.annotationFactory.createGlobalAnnotations(annotationsConfig) + classCode;
    }

    private boolean isMethodCoveredByGlobalAnnotation(Method method, AnnotationsConfig annotationsConfig) {
        return (method.getMethodType().equals(MethodType.GETTER) && annotationsConfig.isGetter()) || (method.getMethodType().equals(MethodType.SETTER) && annotationsConfig.isSetter());
    }

    private String addMethodAnnotation(String classCode, Method method) {
        int indexOfClassFieldInClassCode = classCode.indexOf(method.getCorrespondingFieldCode());
        String firstPartOfClassCode = classCode.substring(0, indexOfClassFieldInClassCode);
        String secondPartOfClassCode = classCode.substring(indexOfClassFieldInClassCode);
        return StringUtils.join(firstPartOfClassCode, "    ", method.getMethodType().getAnnotation(), "\n", secondPartOfClassCode);
    }
}
