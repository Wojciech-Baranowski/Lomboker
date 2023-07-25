package com.simmondobber.lomboker.lombokize.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.comparator.AstComparator;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

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
        addAnnotationsToMethods(ast, annotationsConfig);
    }

    private void addAnnotationsToClasses(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Class::getPreamble)
                .forEach(preamble -> addAnnotationsToClassPreamble(preamble, annotationsConfig));
    }

    private void addAnnotationsToMethods(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getMethodListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Method::getPreamble)
                .forEach(preamble -> addAnnotationsToMethodPreamble(preamble, annotationsConfig));
    }

    private void addAnnotationsToClassPreamble(Preamble preamble, AnnotationsConfig annotationsConfig) {
        List<Annotation> containedAnnotations = preamble.getAnnotations();
        List<Annotation> annotationsToContain = this.annotationFactory.createClassAnnotationsBasedOnConfig(annotationsConfig);
        List<Annotation> annotationToAdd = getAnnotationsToAdd(containedAnnotations, annotationsToContain);
        preamble.getPreambleComponents().addAll(annotationToAdd);
    }

    private void addAnnotationsToMethodPreamble(Preamble preamble, AnnotationsConfig annotationsConfig) {
        List<Annotation> containedAnnotations = preamble.getAnnotations();
        List<Annotation> annotationsToContain = this.annotationFactory.createMethodAnnotationsBasedOnConfig(annotationsConfig);
        List<Annotation> annotationToAdd = getAnnotationsToAdd(containedAnnotations, annotationsToContain);
        preamble.getPreambleComponents().addAll(annotationToAdd);
    }

    private List<Annotation> getAnnotationsToAdd(List<Annotation> containedAnnotations, List<Annotation> annotationsToContain) {
        return annotationsToContain.stream()
                .filter(annotationToContain -> isAnnotationAlreadyContained(containedAnnotations, annotationToContain))
                .toList();
    }

    private boolean isAnnotationAlreadyContained(List<Annotation> containedAnnotations, Annotation annotationToContain) {
        return containedAnnotations.stream()
                .anyMatch(containedAnnotation -> this.astComparator.areMethodsEqual(containedAnnotation.getSyntax(), annotationToContain.getSyntax()));
    }
}
