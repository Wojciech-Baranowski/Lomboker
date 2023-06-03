package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.enums.Annotation;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

public class AnnotationFactory {

    public String createGlobalAnnotations(AnnotationsConfig annotationsConfig) {
        StringBuilder annotations = new StringBuilder();
        if (annotationsConfig.isGetter()) {
            annotations.append(createGlobalGetter());
        }
        if (annotationsConfig.isSetter()) {
            annotations.append(createGlobalSetter());
        }
        if (annotationsConfig.isNoArgsConstructor()) {
            annotations.append(createNoArgsConstructor());
        }
        if (annotationsConfig.isAllArgsConstructor()) {
            annotations.append(createAllArgsConstructor());
        }
        if (annotationsConfig.isBuilder()) {
            annotations.append(createBuilder(annotationsConfig.isToBuilder()));
        }
        if (annotationsConfig.isSuperBuilder()) {
            annotations.append(createSuperBuilder(annotationsConfig.isToBuilder()));
        }
        if (annotationsConfig.isToString()) {
            annotations.append(createToString(annotationsConfig.isCallSuper()));
        }
        return annotations.toString();
    }

    private String createGlobalGetter() {
        return Annotation.GETTER.getSymbol() + "\n";
    }

    private String createGlobalSetter() {
        return Annotation.SETTER.getSymbol() + "\n";
    }

    private String createNoArgsConstructor() {
        return Annotation.NO_ARGS_CONSTRUCTOR.getSymbol() + "\n";
    }

    private String createAllArgsConstructor() {
        return Annotation.ALL_ARGS_CONSTRUCTOR.getSymbol() + "\n";
    }

    private String createBuilder(boolean toBuilder) {
        return (toBuilder ? Annotation.BUILDER_TO_BUILDER.getSymbol() : Annotation.BUILDER.getSymbol()) + "\n";
    }

    private String createSuperBuilder(boolean toBuilder) {
        return (toBuilder ? Annotation.SUPER_BUILDER_TO_BUILDER.getSymbol() : Annotation.SUPER_BUILDER.getSymbol()) + "\n";
    }

    private String createToString(boolean callSuper) {
        return (callSuper ? Annotation.TO_STRING_CALL_SUPER.getSymbol() : Annotation.TO_STRING.getSymbol()) + "\n";
    }
}
