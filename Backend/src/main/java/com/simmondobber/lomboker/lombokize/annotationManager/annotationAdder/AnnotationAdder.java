package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.PreambleComponent;
import com.simmondobber.ast.filter.AstComponentFilter;
import com.simmondobber.lomboker.lombokize.annotationManager.AnnotationFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AnnotationAdder {

    protected final AstComponentFilter astComponentFilter;
    protected final AnnotationFactory annotationFactory;

    public AnnotationAdder() {
        this.astComponentFilter = new AstComponentFilter();
        this.annotationFactory = new AnnotationFactory();
    }

    protected void addAnnotationsToPreamble(Preamble preamble, List<Annotation> annotationsToAdd) {
        List<PreambleComponent> allPreambleAnnotations = createPreambleComponentListWithAnnotationsAddedFirst(preamble, annotationsToAdd);
        preamble.setPreambleComponents(allPreambleAnnotations);
    }

    private List<PreambleComponent> createPreambleComponentListWithAnnotationsAddedFirst(Preamble preamble, List<Annotation> annotationsToAdd) {
        List<PreambleComponent> allPreambleAnnotations = new ArrayList<>();
        allPreambleAnnotations.addAll(annotationsToAdd);
        allPreambleAnnotations.addAll(preamble.getPreambleComponents());
        return allPreambleAnnotations;
    }

    protected String getPreambleFrontSeparatorFromLastNewline(Preamble preamble) {
        String preambleFrontSeparator = preamble.getFrontSeparator();
        int lastNewlineIndex = preambleFrontSeparator.lastIndexOf("\n");
        if(lastNewlineIndex != -1) {
            return preambleFrontSeparator.substring(lastNewlineIndex);
        } else {
            return "";
        }
    }
}
