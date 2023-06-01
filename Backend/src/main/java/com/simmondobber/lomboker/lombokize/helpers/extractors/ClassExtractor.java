package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.common.Keywords;
import com.simmondobber.lomboker.lombokize.codeElement.ClassHeader;
import com.simmondobber.lomboker.lombokize.codeElement.CodeLine;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ClassExtractor {

    private final AnnotationExtractor annotationExtractor;

    public ClassExtractor() {
        this.annotationExtractor = new AnnotationExtractor();
    }

    public ClassHeader getMostOuterClassHeaderFromClassCode(String classCode) {
        CodeLine mostOuterClassHeaderCodeLine = getMostOuterClassHeaderCodeLine(classCode);
        Set<String> mostOuterClassAnnotations = this.annotationExtractor.getAnnotationsAboveCodeLine(classCode, mostOuterClassHeaderCodeLine);
        return new ClassHeader(mostOuterClassHeaderCodeLine, mostOuterClassAnnotations);
    }

    private List<CodeLine> getClassCodeLinesContainingClassKeyword(String classCode) {
        List<CodeLine> codeLines = mapClassCodeToCodeLines(classCode);
        return codeLines.stream()
                .filter(this::isCodeLineContainingClassKeyword)
                .toList();
    }

    private List<CodeLine> mapClassCodeToCodeLines(String classCode) {
        return Arrays.stream(classCode.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(CodeLine::new)
                .toList();
    }

    private boolean isCodeLineContainingClassKeyword(CodeLine codeLine) {
        String separateClassKeyword = " " + Keywords.CLASS.getKeyword() + " ";
        return codeLine.getLine().contains(separateClassKeyword);
    }

    private CodeLine getMostOuterClassHeaderCodeLine(String classCode) {
        List<CodeLine> classCodeLinesContainingClassKeyword = getClassCodeLinesContainingClassKeyword(classCode);
        return classCodeLinesContainingClassKeyword.stream()
                .min(Comparator.comparing(CodeLine::getNestingLevel))
                .orElse(new CodeLine(""));
    }
}
