package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.lomboker.common.AnnotationData;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.List;

public class FieldAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToFields(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.extractFieldsFromGivenAstComponent(ast.getAstRoot(), annotationsConfig.isActOnInnerClasses())
                .forEach(field -> addAnnotationsToFieldPreamble(field.getPreamble(), annotationsConfig.getAnnotationsData()));
    }

    private void addAnnotationsToFieldPreamble(Preamble preamble, List<AnnotationData> annotationsData) {
        List<Annotation> annotationsPreambleHasToContain = this.annotationFactory.createFieldLombokAnnotations(annotationsData, getPreambleFrontSeparatorFromLastNewline(preamble));
        addAnnotationsToPreamble(preamble, annotationsPreambleHasToContain);
    }
}
