package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.enums.Annotation;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.Set;

public class AnnotationFactory {

    public String createAnnotations(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        StringBuilder annotations = new StringBuilder();
        if (canAddGlobalGetterAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createGlobalGetter());
        }
        if (canAddGlobalSetterAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createGlobalSetter());
        }
        if (canAddNoArgsConstructorAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createNoArgsConstructor());
        }
        if (canAddAllArgsConstructorAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createAllArgsConstructor());
        }
        if (canAddBuilderAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createBuilder(annotationsConfig.isToBuilder()));
        }
        if (canAddSuperBuilderAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createSuperBuilder(annotationsConfig.isToBuilder()));
        }
        if (canAddToStringAnnotation(annotationsConfig, excludedAnnotations)) {
            annotations.append(createToString(annotationsConfig.isCallSuper()));
        }
        return annotations.toString();
    }

    private boolean canAddGlobalGetterAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isGetter() && !excludedAnnotations.contains(Annotation.GETTER);
    }

    private boolean canAddGlobalSetterAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isSetter() && !excludedAnnotations.contains(Annotation.SETTER);
    }

    private boolean canAddNoArgsConstructorAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isNoArgsConstructor() && !excludedAnnotations.contains(Annotation.NO_ARGS_CONSTRUCTOR);
    }

    private boolean canAddAllArgsConstructorAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isAllArgsConstructor() && !excludedAnnotations.contains(Annotation.ALL_ARGS_CONSTRUCTOR);
    }

    private boolean canAddBuilderAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isBuilder() && !excludedAnnotations.contains(Annotation.BUILDER);
    }

    private boolean canAddSuperBuilderAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isSuperBuilder() && !excludedAnnotations.contains(Annotation.SUPER_BUILDER);
    }

    private boolean canAddToStringAnnotation(AnnotationsConfig annotationsConfig, Set<Annotation> excludedAnnotations) {
        return annotationsConfig.isToString() && !excludedAnnotations.contains(Annotation.TO_STRING);
    }

    private String createGlobalGetter() {
        return Annotation.GETTER.getKeyword() + "\n";
    }

    private String createGlobalSetter() {
        return Annotation.SETTER.getKeyword() + "\n";
    }

    private String createNoArgsConstructor() {
        return Annotation.NO_ARGS_CONSTRUCTOR.getKeyword() + "\n";
    }

    private String createAllArgsConstructor() {
        return Annotation.ALL_ARGS_CONSTRUCTOR.getKeyword() + "\n";
    }

    private String createBuilder(boolean toBuilder) {
        return (toBuilder ? Annotation.BUILDER_TO_BUILDER.getKeyword() : Annotation.BUILDER.getKeyword()) + "\n";
    }

    private String createSuperBuilder(boolean toBuilder) {
        return (toBuilder ? Annotation.SUPER_BUILDER_TO_BUILDER.getKeyword() : Annotation.SUPER_BUILDER.getKeyword()) + "\n";
    }

    private String createToString(boolean callSuper) {
        return (callSuper ? Annotation.TO_STRING_CALL_SUPER.getKeyword() : Annotation.TO_STRING.getKeyword()) + "\n";
    }
}
