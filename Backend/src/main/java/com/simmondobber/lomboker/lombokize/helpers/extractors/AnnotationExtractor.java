package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.lombokize.classElements.CodeLine;
import com.simmondobber.lomboker.lombokize.enums.Annotation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.simmondobber.lomboker.lombokize.enums.Annotation.*;

public class AnnotationExtractor {

    private static final Set<Annotation> BUILDER_ANNOTATIONS = Set.of(BUILDER, BUILDER_TO_BUILDER, SUPER_BUILDER, SUPER_BUILDER_TO_BUILDER);
    private static final Set<Annotation> TO_STRING_ANNOTATIONS = Set.of(TO_STRING, TO_STRING_CALL_SUPER);

    public Set<Annotation> getAnnotationsAboveCodeLine(String classCode, CodeLine codeLine) {
        Set<Annotation> annotations = new HashSet<>();
        int currentLineIndex = classCode.indexOf(codeLine.getLine());
        int previousLineIndex = getPreviousLineIndex(classCode, currentLineIndex);
        while (isPreviousLineAnnotation(classCode, currentLineIndex, previousLineIndex)) {
            String previousLine = getPreviousLine(classCode, currentLineIndex, previousLineIndex);
            currentLineIndex = previousLineIndex;
            previousLineIndex = getPreviousLineIndex(classCode, currentLineIndex);
            annotations.add(Annotation.getBySymbol(previousLine));
        }
        return annotations;
    }

    private boolean isPreviousLineAnnotation(String classCode, int currentLineIndex, int previousLineIndex) {
        String previousLine = getPreviousLine(classCode, currentLineIndex, previousLineIndex);
        return Annotation.getBySymbol(previousLine) != null;
    }

    private String getPreviousLine(String classCode, int currentLineIndex, int previousLineIndex) {
        String classCodeTrimmedToCurrentLine = classCode.substring(0, Math.max(currentLineIndex - 1, 0));
        return classCodeTrimmedToCurrentLine.substring(previousLineIndex);
    }

    private int getPreviousLineIndex(String classCode, int currentLineIndex) {
        String classCodeTrimmedToCurrentLine = classCode.substring(0, Math.max(currentLineIndex - 1, 0));
        return classCodeTrimmedToCurrentLine.lastIndexOf("\n") + 1;
    }

    public Set<Annotation> getAllAnnotationsDisjointWithGivenAnnotationSet(Set<Annotation> annotations) {
        if (!Collections.disjoint(annotations, BUILDER_ANNOTATIONS)) {
            annotations.addAll(BUILDER_ANNOTATIONS);
        }
        if (!Collections.disjoint(annotations, TO_STRING_ANNOTATIONS)) {
            annotations.addAll(TO_STRING_ANNOTATIONS);
        }
        return annotations;
    }
}
