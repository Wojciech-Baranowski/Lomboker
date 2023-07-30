package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.List;

public class FieldAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToFields(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getFieldListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .map(Field::getPreamble)
                .forEach(preamble -> addAnnotationsToFieldPreamble(preamble, annotationsConfig));
    }

    private void addAnnotationsToFieldPreamble(Preamble preamble, AnnotationsConfig annotationsConfig) {
        String frontSeparator = getFrontSeparator(preamble);
        List<Annotation> containedAnnotations = preamble.getAnnotations();
        List<Annotation> annotationsToContain = this.annotationFactory.createFieldAnnotationsBasedOnConfig(annotationsConfig, frontSeparator);
        List<Annotation> annotationToAdd = getAnnotationsToAdd(containedAnnotations, annotationsToContain);
        addAnnotationsToPreamble(preamble, annotationToAdd);
    }
}
