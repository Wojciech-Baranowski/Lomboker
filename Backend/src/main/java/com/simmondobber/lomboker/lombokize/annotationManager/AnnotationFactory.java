package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.parser.complexComponentParser.AnnotationParser;
import com.simmondobber.lomboker.common.AnnotationData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationFactory {

    public final List<AnnotationData> annotationsExcludedForEnum = List.of(AnnotationData.SETTER, AnnotationData.BUILDER, AnnotationData.BUILDER_TO_BUILDER, AnnotationData.SUPER_BUILDER, AnnotationData.SUPER_BUILDER_TO_BUILDER);

    public List<Annotation> createAllLombokAnnotations() {
        return Arrays.stream(AnnotationData.values())
                .map(annotationData -> createAnnotation(annotationData, ""))
                .toList();
    }

    public List<Annotation> createClassLombokAnnotations(List<AnnotationData> annotationsData, String separator) {
        return annotationsData.stream()
                .map(annotationData -> createAnnotation(annotationData, separator))
                .toList();
    }

    public List<Annotation> createEnumLombokAnnotations(List<AnnotationData> annotationsData, String separator) {
        return annotationsData.stream()
                .filter(annotationData -> !annotationsExcludedForEnum.contains(annotationData))
                .map(annotationData -> createAnnotation(annotationData, separator))
                .toList();
    }

    public List<Annotation> createFieldLombokAnnotations(List<AnnotationData> annotationsData, String separator) {
        List<Annotation> annotations = new ArrayList<>();
        if (!annotationsData.contains(AnnotationData.GETTER)) {
            annotations.add(createAnnotation(AnnotationData.GETTER, separator));
        }
        if (!annotationsData.contains(AnnotationData.SETTER)) {
            annotations.add(createAnnotation(AnnotationData.SETTER, separator));
        }
        return annotations;
    }

    public Annotation createAnnotation(AnnotationData annotationData, String separator) {
        String annotationCode = annotationData.getKeyword() + separator;
        return new AnnotationParser(annotationCode).parse();
    }
}
