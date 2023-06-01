package com.simmondobber.lomboker.lombokize.helpers.formatters;

import com.simmondobber.lomboker.lombokize.enums.IndentType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ClassCodeFormatter {

    private static final String QUAD_SPACE = "    ";

    public String standardizeClassIndents(String classCode, IndentType indentType) {
        String classCodeWithStandardIndents = classCode.replaceAll(indentType.getIndent(), QUAD_SPACE);
        return removeBaseIndents(classCodeWithStandardIndents);
    }

    public String formatClassCodeIndentsBack(String classCode, IndentType indentType) {
        return classCode.replaceAll(QUAD_SPACE, indentType.getIndent());
    }

    private String removeBaseIndents(String classCode) {
        int baseIndentLevel = classCode.split("[^ ]", 2)[0].length();
        return Arrays.stream(classCode.split("\n"))
                .map(line -> removeBaseIndent(line, baseIndentLevel))
                .collect(Collectors.joining("\n"));
    }

    private String removeBaseIndent(String line, int baseIndentLevel) {
        return line.substring(Math.min(line.length(), baseIndentLevel));
    }
}
