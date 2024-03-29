package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
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

    public void removeLombokAnnotations(Ast ast, boolean actOnInnerClasses) {
        removeLombokAnnotationsFromClasses(ast, actOnInnerClasses);
        removeLombokAnnotationsFromFields(ast, actOnInnerClasses);
    }

    private void removeLombokAnnotationsFromClasses(Ast ast, boolean actOnInnerClasses) {
        this.astComponentFilter.extractClassesFromGivenAstComponent(ast.getAstRoot(), actOnInnerClasses)
                .forEach(clazz -> removeLombokAnnotationsFromPreamble(clazz.getPreamble()));
    }

    private void removeLombokAnnotationsFromFields(Ast ast, boolean actOnInnerClasses) {
        this.astComponentFilter.extractFieldsFromGivenAstComponent(ast.getAstRoot(), actOnInnerClasses)
                .forEach(clazz -> removeLombokAnnotationsFromPreamble(clazz.getPreamble()));
    }

    private void removeLombokAnnotationsFromPreamble(Preamble preamble) {
        List<Annotation> preambleAnnotationsToRemove = getLombokAnnotationsToRemove(preamble);
        preamble.getPreambleComponents().removeAll(preambleAnnotationsToRemove);
    }

    private List<Annotation> getLombokAnnotationsToRemove(Preamble preamble) {
        List<String> namesOfAnnotationsToRemove = getNamesOfLombokAnnotationsToRemove();
        return preamble.getAnnotations().stream()
                .filter(annotation -> namesOfAnnotationsToRemove.contains(annotation.getPath().getSyntax()))
                .toList();
    }

    private List<String> getNamesOfLombokAnnotationsToRemove() {
        return this.annotationFactory.createAllLombokAnnotations().stream()
                .map(annotation -> annotation.getPath().getSyntax())
                .toList();
    }
}
