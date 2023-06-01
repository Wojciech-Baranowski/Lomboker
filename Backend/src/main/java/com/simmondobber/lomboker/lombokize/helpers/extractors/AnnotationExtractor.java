package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.lombokize.codeElement.CodeLine;

import java.util.HashSet;
import java.util.Set;

public class AnnotationExtractor {

    public Set<String> getAnnotationsAboveCodeLine(String classCode, CodeLine codeLine) {
        Set<String> annotations = new HashSet<>();
        int currentLineIndex = classCode.indexOf(codeLine.getLine());
        int previousLineIndex = getPreviousLineIndex(classCode, currentLineIndex);
        while (isPreviousLineAnnotation(classCode, currentLineIndex, previousLineIndex)) {
            String previousLine = getPreviousLine(classCode, currentLineIndex, previousLineIndex);
            currentLineIndex = previousLineIndex;
            previousLineIndex = getPreviousLineIndex(classCode, currentLineIndex);
            annotations.add(previousLine);
        }
        return annotations;
    }

    private boolean isPreviousLineAnnotation(String classCode, int currentLineIndex, int previousLineIndex) {
        String previousLine = getPreviousLine(classCode, currentLineIndex, previousLineIndex);
        return previousLine.startsWith("@");
    }

    private String getPreviousLine(String classCode, int currentLineIndex, int previousLineIndex) {
        String classCodeTrimmedToCurrentLine = classCode.substring(0, Math.max(currentLineIndex - 1, 0));
        return classCodeTrimmedToCurrentLine.substring(previousLineIndex);
    }

    private int getPreviousLineIndex(String classCode, int currentLineIndex) {
        String classCodeTrimmedToCurrentLine = classCode.substring(0, Math.max(currentLineIndex - 1, 0));
        return classCodeTrimmedToCurrentLine.lastIndexOf("\n") + 1;
    }
}
