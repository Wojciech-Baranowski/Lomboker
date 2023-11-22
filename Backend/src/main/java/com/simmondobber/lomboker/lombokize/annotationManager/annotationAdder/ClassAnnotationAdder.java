package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.simpleAstComponents.ClassTypeKeyword;
import com.simmondobber.lomboker.common.AnnotationData;
import com.simmondobber.lomboker.common.ClassTypeKeywordData;

import java.util.ArrayList;
import java.util.List;

public class ClassAnnotationAdder extends AnnotationAdder {

    public void addAnnotationsToClasses(Ast ast, List<AnnotationData> annotationsData) {
        this.astComponentFilter.extractClassesFromGivenAstComponent(ast.getAstRoot())
                .forEach(clazz -> addAnnotationsToClassPreamble(clazz.getPreamble(), clazz.getClassTypeKeyword(), annotationsData));
    }

    private void addAnnotationsToClassPreamble(Preamble preamble, ClassTypeKeyword classType, List<AnnotationData> annotationsData) {
        List<Annotation> annotationsPreambleHasToContain = createAnnotationsClassPreambleHasToContain(preamble, classType, annotationsData);
        addAnnotationsToPreamble(preamble, annotationsPreambleHasToContain);
    }

    private List<Annotation> createAnnotationsClassPreambleHasToContain(Preamble preamble, ClassTypeKeyword classType, List<AnnotationData> annotationsData) {
        if (List.of(ClassTypeKeywordData.CLASS.getKeyword(), ClassTypeKeywordData.RECORD.getKeyword()).contains(classType.getSyntax())) {
            return this.annotationFactory.createClassLombokAnnotations(annotationsData, getPreambleFrontSeparatorFromLastNewline(preamble));
        } else if (classType.getSyntax().equals(ClassTypeKeywordData.ENUM.getKeyword())) {
            return this.annotationFactory.createEnumLombokAnnotations(annotationsData, getPreambleFrontSeparatorFromLastNewline(preamble));
        } else if (List.of(ClassTypeKeywordData.INTERFACE.getKeyword(), ClassTypeKeywordData.ANNOTATION.getKeyword()).contains(classType.getSyntax())) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
