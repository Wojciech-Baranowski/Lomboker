package com.simmondobber.lomboker.lombokize.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.comparator.AstComparator;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.List;

public class AnnotationAdder {

    private final AstComponentFilter astComponentFilter;
    private final AnnotationFactory annotationFactory;
    private final AstComparator astComparator;

    public AnnotationAdder() {
        this.astComponentFilter = new AstComponentFilter();
        this.annotationFactory = new AnnotationFactory();
        this.astComparator = new AstComparator();
    }

    public void addAnnotations(Ast ast, AnnotationsConfig annotationsConfig) {
        addAnnotationsToClasses(ast, annotationsConfig);
        addAnnotationsToFields(ast, annotationsConfig);
    }

    private void addAnnotationsToClasses(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Class::getPreamble)
                .forEach(preamble -> addAnnotationsToClassPreamble(preamble, annotationsConfig));
    }

    private void addAnnotationsToFields(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getFieldListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Field::getPreamble)
                .forEach(preamble -> addAnnotationsToFieldPreamble(preamble, annotationsConfig));
    }

    private void addAnnotationsToClassPreamble(Preamble preamble, AnnotationsConfig annotationsConfig) {
        String frontSeparator = getFrontSeparator(preamble);
        List<Annotation> containedAnnotations = preamble.getAnnotations();
        List<Annotation> annotationsToContain = this.annotationFactory.createClassAnnotationsBasedOnConfig(annotationsConfig, frontSeparator);
        List<Annotation> annotationToAdd = getAnnotationsToAdd(containedAnnotations, annotationsToContain);
        addAnnotationsToPreamble(preamble, annotationToAdd);
    }

    private void addAnnotationsToFieldPreamble(Preamble preamble, AnnotationsConfig annotationsConfig) {
        String frontSeparator = getFrontSeparator(preamble);
        List<Annotation> containedAnnotations = preamble.getAnnotations();
        List<Annotation> annotationsToContain = this.annotationFactory.createFieldAnnotationsBasedOnConfig(annotationsConfig, frontSeparator);
        List<Annotation> annotationToAdd = getAnnotationsToAdd(containedAnnotations, annotationsToContain);
        addAnnotationsToPreamble(preamble, annotationToAdd);
    }

    private List<Annotation> getAnnotationsToAdd(List<Annotation> containedAnnotations, List<Annotation> annotationsToContain) {
        return annotationsToContain.stream()
                .filter(annotationToContain -> !isAnnotationAlreadyContained(containedAnnotations, annotationToContain))
                .toList();
    }

    private boolean isAnnotationAlreadyContained(List<Annotation> containedAnnotations, Annotation annotationToContain) {
        return containedAnnotations.stream()
                .anyMatch(containedAnnotation -> this.astComparator.areAnnotationsEqual(containedAnnotation.getSyntax(), annotationToContain.getSyntax()));
    }

    private void addAnnotationsToPreamble(Preamble preamble, List<Annotation> annotationsToAdd) {
        List<PreambleComponent> preambleComponentsWithAddedAnnotations = new ArrayList<>();
        preambleComponentsWithAddedAnnotations.addAll(annotationsToAdd);
        preambleComponentsWithAddedAnnotations.addAll(preamble.getPreambleComponents());
        preamble.getPreambleComponents().clear();
        preamble.getPreambleComponents().addAll(preambleComponentsWithAddedAnnotations);
    }

    private String getFrontSeparator(Preamble preamble) {
        String preambleFrontSeparator = preamble.getFrontSeparator();
        int lastNewlineIndex = preambleFrontSeparator.lastIndexOf("\n");
        if (lastNewlineIndex == -1) {
            return preambleFrontSeparator;
        } else {
            return preambleFrontSeparator.substring(lastNewlineIndex);
        }
    }
}
