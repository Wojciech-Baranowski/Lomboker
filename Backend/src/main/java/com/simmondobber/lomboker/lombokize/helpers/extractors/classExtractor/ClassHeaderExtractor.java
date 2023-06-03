package com.simmondobber.lomboker.lombokize.helpers.extractors.classExtractor;

import com.simmondobber.lomboker.common.Keywords;
import com.simmondobber.lomboker.lombokize.classElements.CodeLine;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClassHeaderExtractor {

    public List<CodeLine> getOuterClassHeaders(String classCode) {
        List<CodeLine> classHeaders = getClassCodeLinesContainingClassKeyword(classCode);
        int outerClassNestingLevel = getOuterClassNestingLevel(classHeaders);
        return classHeaders.stream()
                .filter(header -> header.getNestingLevel() == outerClassNestingLevel)
                .toList();
    }

    private List<CodeLine> getClassCodeLinesContainingClassKeyword(String classCode) {
        List<CodeLine> codeLines = mapClassCodeToCodeLines(classCode);
        return codeLines.stream()
                .filter(this::isCodeLineContainingClassKeyword)
                .toList();
    }

    private int getOuterClassNestingLevel(List<CodeLine> classHeaders) {
        return classHeaders.stream()
                .min(Comparator.comparing(CodeLine::getNestingLevel))
                .orElse(new CodeLine(""))
                .getNestingLevel();
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
}
