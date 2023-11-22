package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.lomboker.common.AnnotationData;

import java.util.List;

public class FieldAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToFields(Ast ast, List<AnnotationData> annotationsData) {
        this.astComponentFilter.extractFieldsFromGivenAstComponent(ast.getAstRoot())
                .forEach(field -> addAnnotationsToFieldPreamble(field.getPreamble(), annotationsData));
    }

    private void addAnnotationsToFieldPreamble(Preamble preamble, List<AnnotationData> annotationsData) {
        List<Annotation> annotationsPreambleHasToContain = this.annotationFactory.createFieldLombokAnnotations(annotationsData, getPreambleFrontSeparatorFromLastNewline(preamble));
        addAnnotationsToPreamble(preamble, annotationsPreambleHasToContain);
    }
}
