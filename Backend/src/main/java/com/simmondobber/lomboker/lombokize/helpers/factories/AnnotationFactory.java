package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.enums.Annotations;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.Set;

public class AnnotationFactory {

    public String createAnnotations(AnnotationsConfig annotationsConfig, Set<String> excludedAnnotations) {
        StringBuilder annotations = new StringBuilder();
        if (canAddGlobalGetterAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createGlobalGetter());
        }
        if (canAddGlobalSetterAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createGlobalSetter());
        }
        return annotations.toString();
    }

    private boolean canAddGlobalGetterAnnotation(AnnotationsConfig annotationsConfig, Set<String> excludedAnnotations) {
        return annotationsConfig.isGlobalGetter() && !excludedAnnotations.contains(Annotations.GETTER.getKeyword());
    }

    private boolean canAddGlobalSetterAnnotation(AnnotationsConfig annotationsConfig, Set<String> excludedAnnotations) {
        return annotationsConfig.isGlobalSetter() && !excludedAnnotations.contains(Annotations.SETTER.getKeyword());
    }

    private String createGlobalGetter() {
        return Annotations.GETTER.getKeyword() + "\n";
    }

    private String createGlobalSetter() {
        return Annotations.SETTER.getKeyword() + "\n";
    }
}
