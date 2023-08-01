package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder.ClassAnnotationAdder;
import com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder.FieldAnnotationAdder;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

public class AnnotationManager {

    private final AnnotationCleaner annotationCleaner;
    private final ClassAnnotationAdder classAnnotationAdder;
    private final FieldAnnotationAdder fieldAnnotationAdder;

    public AnnotationManager() {
        this.annotationCleaner = new AnnotationCleaner();
        this.classAnnotationAdder = new ClassAnnotationAdder();
        this.fieldAnnotationAdder = new FieldAnnotationAdder();
    }

    public void cleanAndAddRequiredLombokAnnotations(Ast ast, AnnotationsConfig annotationsConfig) {
        this.annotationCleaner.removeLombokAnnotations(ast);
        this.classAnnotationAdder.addAnnotationsToClasses(ast, annotationsConfig);
        this.fieldAnnotationAdder.addAnnotationsToFields(ast, annotationsConfig);
    }
}
