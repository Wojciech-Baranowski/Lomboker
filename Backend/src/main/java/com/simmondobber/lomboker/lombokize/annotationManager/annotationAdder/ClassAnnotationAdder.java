package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.List;

public class ClassAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToClasses(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot()).stream()
                .forEach(clazz -> addAnnotationsToClassPreamble(clazz.getPreamble(), clazz.getClassTypeKeyword(), annotationsConfig));
    }

    private void addAnnotationsToClassPreamble(Preamble preamble, Keyword classType, AnnotationsConfig annotationsConfig) {
        List<Annotation> containedAnnotations = preamble.getAnnotations();
        List<Annotation> annotationsToContain = createAnnotationsToContain(preamble, classType, annotationsConfig);
        List<Annotation> annotationToAdd = getAnnotationsToAdd(containedAnnotations, annotationsToContain);
        addAnnotationsToPreamble(preamble, annotationToAdd);
    }

    private List<Annotation> createAnnotationsToContain(Preamble preamble, Keyword classType, AnnotationsConfig annotationsConfig) {
        String frontSeparator = getFrontSeparator(preamble);
        if (classType.getFullSyntax().trim().equals("class")) {
            return this.annotationFactory.createClassAnnotationsBasedOnConfig(annotationsConfig, frontSeparator);
        } else if (classType.getFullSyntax().trim().equals("enum")) {
            return this.annotationFactory.createEnumAnnotationsBasedOnConfig(annotationsConfig, frontSeparator);
        } else {
            return new ArrayList<>();
        }
    }
}
