package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.filter.AstComponentFilter;

import java.util.List;

public class AnnotationCleaner {

    private final AstComponentFilter astComponentFilter;
    private final AnnotationFactory annotationFactory;

    public AnnotationCleaner() {
        this.astComponentFilter = new AstComponentFilter();
        this.annotationFactory = new AnnotationFactory();
    }

    public void removeAnnotations(Ast ast) {
        removeAnnotationsFromClasses(ast);
        removeAnnotationsFromFields(ast);
    }

    private void removeAnnotationsFromClasses(Ast ast) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Class::getPreamble)
                .forEach(this::removeAnnotationsFromPreamble);
    }

    private void removeAnnotationsFromFields(Ast ast) {
        this.astComponentFilter.getFieldListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Field::getPreamble)
                .forEach(this::removeAnnotationsFromPreamble);
    }

    private void removeAnnotationsFromPreamble(Preamble preamble) {
        List<String> annotationsToRemoveNamesSyntax = this.annotationFactory.createAllAnnotations().stream()
                .map(annotation -> annotation.getName().getSyntax().trim())
                .toList();
        List<Annotation> preambleAnnotationsToRemove = preamble.getAnnotations().stream()
                .filter(annotation -> annotationsToRemoveNamesSyntax.contains(annotation.getName().getSyntax().trim()))
                .toList();
        preamble.getPreambleComponents().removeAll(preambleAnnotationsToRemove);
    }
}
