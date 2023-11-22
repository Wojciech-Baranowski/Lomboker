package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.common.AnnotationData;
import com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder.ClassAnnotationAdder;
import com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder.FieldAnnotationAdder;

import java.util.List;

public class AnnotationManager {

    private final AnnotationCleaner annotationCleaner;
    private final ClassAnnotationAdder classAnnotationAdder;
    private final FieldAnnotationAdder fieldAnnotationAdder;

    public AnnotationManager() {
        this.annotationCleaner = new AnnotationCleaner();
        this.classAnnotationAdder = new ClassAnnotationAdder();
        this.fieldAnnotationAdder = new FieldAnnotationAdder();
    }

    public void cleanAndAddRequiredLombokAnnotations(Ast ast, List<AnnotationData> annotationsData) {
        this.annotationCleaner.removeLombokAnnotations(ast);
        this.classAnnotationAdder.addAnnotationsToClasses(ast, annotationsData);
        this.fieldAnnotationAdder.addAnnotationsToFields(ast, annotationsData);
    }
}
