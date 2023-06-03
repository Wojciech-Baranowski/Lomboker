package com.simmondobber.lomboker.lombokize.helpers.cleaners;

import com.simmondobber.lomboker.lombokize.enums.Annotation;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationCleaner {

    private static final Set<Annotation> BUILDER_ANNOTATIONS = Set.of(Annotation.BUILDER, Annotation.BUILDER_TO_BUILDER, Annotation.SUPER_BUILDER, Annotation.SUPER_BUILDER_TO_BUILDER);
    private static final Set<Annotation> TO_STRING_ANNOTATIONS = Set.of(Annotation.TO_STRING, Annotation.TO_STRING_CALL_SUPER);

    public String removeRedundantAnnotations(String code, AnnotationsConfig annotationsConfig) {
        List<String> annotationsToRemove = getAnnotationsToRemoveKeywords(annotationsConfig);
        return Arrays.stream(code.split("\n"))
                .map(line -> replaceLineWithEmptyStringIfLineIsAnnotation(line, annotationsToRemove))
                .collect(Collectors.joining("\n"));
    }

    private String replaceLineWithEmptyStringIfLineIsAnnotation(String line, List<String> annotationsToRemove) {
        return annotationsToRemove.contains(line.trim()) ? "" : line;
    }

    private List<String> getAnnotationsToRemoveKeywords(AnnotationsConfig annotationsConfig) {
        List<Annotation> annotationsToRemove = getAnnotationsToRemove(annotationsConfig);
        return annotationsToRemove.stream()
                .map(Annotation::getSymbol)
                .toList();
    }

    private List<Annotation> getAnnotationsToRemove(AnnotationsConfig annotationsConfig) {
        List<Annotation> annotationsToRemove = new ArrayList<>();
        if (annotationsConfig.isGetter()) {
            annotationsToRemove.add(Annotation.GETTER);
        }
        if (annotationsConfig.isSetter()) {
            annotationsToRemove.add(Annotation.SETTER);
        }
        if (annotationsConfig.isNoArgsConstructor()) {
            annotationsToRemove.add(Annotation.NO_ARGS_CONSTRUCTOR);
        }
        if (annotationsConfig.isAllArgsConstructor()) {
            annotationsToRemove.add(Annotation.ALL_ARGS_CONSTRUCTOR);
        }
        if (annotationsConfig.isBuilder() || annotationsConfig.isSuperBuilder()) {
            annotationsToRemove.addAll(BUILDER_ANNOTATIONS);
        }
        if (annotationsConfig.isToString()) {
            annotationsToRemove.addAll(TO_STRING_ANNOTATIONS);
        }
        return annotationsToRemove;
    }
}
