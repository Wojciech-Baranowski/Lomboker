package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.ComplexAstComponent;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.simpleAstComponents.ClassTypeKeyword;
import com.simmondobber.lomboker.common.ClassTypeKeywords;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.List;

public class ClassAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToClasses(Ast ast, AnnotationsConfig annotationsConfig) {
        this.astComponentFilter.getClassListFromAstComponent((ComplexAstComponent) ast.getAstRoot())
                .forEach(clazz -> addAnnotationsToClassPreamble(clazz.getPreamble(), clazz.getClassTypeKeyword(), annotationsConfig));
    }

    private void addAnnotationsToClassPreamble(Preamble preamble, ClassTypeKeyword classType, AnnotationsConfig annotationsConfig) {
        List<Annotation> annotationsPreambleHasToContain = createAnnotationsClassPreambleHasToContain(preamble, classType, annotationsConfig);
        addAnnotationsToPreamble(preamble, annotationsPreambleHasToContain);
    }

    private List<Annotation> createAnnotationsClassPreambleHasToContain(Preamble preamble, ClassTypeKeyword classType, AnnotationsConfig annotationsConfig) {
        if (List.of(ClassTypeKeywords.CLASS.getKeyword(), ClassTypeKeywords.RECORD.getKeyword()).contains(classType.getSyntax())) {
            return this.annotationFactory.createClassLombokAnnotations(annotationsConfig, getPreambleFrontSeparatorFromLastNewline(preamble));
        } else if (classType.getSyntax().equals(ClassTypeKeywords.ENUM.getKeyword())) {
            return this.annotationFactory.createEnumLombokAnnotations(annotationsConfig, getPreambleFrontSeparatorFromLastNewline(preamble));
        } else if (List.of(ClassTypeKeywords.INTERFACE.getKeyword(), ClassTypeKeywords.ANNOTATION.getKeyword()).contains(classType.getSyntax())) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
