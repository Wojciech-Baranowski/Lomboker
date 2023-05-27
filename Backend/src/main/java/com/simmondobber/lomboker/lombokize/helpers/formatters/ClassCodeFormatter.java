package com.simmondobber.lomboker.lombokize.helpers.formatters;

import com.simmondobber.lomboker.lombokize.enums.IndentType;

public class ClassCodeFormatter {

    private static final String QUAD_SPACE = "    ";

    public String formatClassCodeIndentsToSpaces(String classCode, IndentType indentType) {
        return classCode.replaceAll(indentType.getSymbol(), QUAD_SPACE);
    }

    public String formatClassCodeIndentsFromSpaces(String classCode, IndentType indentType) {
        return classCode.replaceAll(QUAD_SPACE, indentType.getSymbol());
    }
}
