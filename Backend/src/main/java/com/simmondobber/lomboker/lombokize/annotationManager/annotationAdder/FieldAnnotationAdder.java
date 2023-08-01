package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.List;

public class FieldAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToFields(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getFieldListFromAstComponent((ComplexAstComponent) ast.getAstRoot())
                .forEach(field -> addAnnotationsToFieldPreamble(field.getPreamble(), annotationsConfig));
    }

    private void addAnnotationsToFieldPreamble(Preamble preamble, AnnotationsConfig annotationsConfig) {
        List<Annotation> annotationsPreambleHasToContain = this.annotationFactory.createFieldLombokAnnotations(annotationsConfig, getPreambleFrontSeparatorFromLastNewline(preamble));
        addAnnotationsToPreamble(preamble, annotationsPreambleHasToContain);
    }
}
