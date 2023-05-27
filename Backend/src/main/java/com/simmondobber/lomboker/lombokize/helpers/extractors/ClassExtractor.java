package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.common.Keywords;
import com.simmondobber.lomboker.lombokize.codeElement.CodeLine;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClassExtractor {

    public CodeLine getMostOuterClassHeaderFromClassCode(String classCode) {
        List<CodeLine> classCodeLinesContainingClassKeyword = getClassCodeLinesContainingClassKeyword(classCode);
        return classCodeLinesContainingClassKeyword.stream()
                .min(Comparator.comparing(CodeLine::getNestingLevel))
                .orElse(new CodeLine(""));
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
}
