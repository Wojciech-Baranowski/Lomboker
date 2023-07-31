package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.comparator.AstComparator;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.PreambleComponent;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.lomboker.lombokize.annotationManager.AnnotationFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AnnotationAdder {

    protected final AstComponentFilter astComponentFilter;
    protected final AnnotationFactory annotationFactory;
    private final AstComparator astComparator;

    public AnnotationAdder() {
        this.astComponentFilter = new AstComponentFilter();
        this.annotationFactory = new AnnotationFactory();
        this.astComparator = new AstComparator();
    }

    protected void addAnnotationsToPreamble(Preamble preamble, List<Annotation> annotationsToAdd) {
        List<PreambleComponent> preambleComponentsWithAddedAnnotations = new ArrayList<>();
        preambleComponentsWithAddedAnnotations.addAll(annotationsToAdd);
        preambleComponentsWithAddedAnnotations.addAll(preamble.getPreambleComponents());
        preamble.getPreambleComponents().clear();
        preamble.getPreambleComponents().addAll(preambleComponentsWithAddedAnnotations);
    }

    protected List<Annotation> getAnnotationsToAdd(List<Annotation> containedAnnotations, List<Annotation> annotationsToContain) {
        return annotationsToContain.stream()
                .filter(annotationToContain -> !isAnnotationAlreadyContained(containedAnnotations, annotationToContain))
                .toList();
    }

    private boolean isAnnotationAlreadyContained(List<Annotation> containedAnnotations, Annotation annotationToContain) {
        return containedAnnotations.stream()
                .anyMatch(containedAnnotation -> this.astComparator.areAnnotationsEqual(containedAnnotation.getFullSyntax(), annotationToContain.getFullSyntax()));
    }

    protected String getFrontSeparator(Preamble preamble) {
        String preambleFrontSeparator = preamble.getFrontSeparator();
        int lastNewlineIndex = preambleFrontSeparator.lastIndexOf("\n");
        if (lastNewlineIndex == -1) {
            return preambleFrontSeparator;
        } else {
            return preambleFrontSeparator.substring(lastNewlineIndex);
        }
    }
}
