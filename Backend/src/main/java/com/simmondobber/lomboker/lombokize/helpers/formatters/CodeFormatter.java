package com.simmondobber.lomboker.lombokize.helpers.formatters;

import com.simmondobber.lomboker.lombokize.enums.IndentType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CodeFormatter {

    private static final String QUAD_SPACE = "    ";

    public String formatIndentsToSpaces(String code, IndentType indentType) {
        return code.replaceAll(indentType.getIndent(), QUAD_SPACE);
    }

    public String formatSpacesToIndents(String code, IndentType indentType) {
        return code.replaceAll(QUAD_SPACE, indentType.getIndent());
    }

    public String addBaseIndents(String code) {
        return Arrays.stream(code.split("\n"))
                .map(this::addBaseIndent)
                .collect(Collectors.joining("\n"));
    }

    private String addBaseIndent(String line) {
        return !line.isEmpty() ? QUAD_SPACE + line : line;
    }

    public String removeBaseIndents(String code) {
        int baseIndentLevel = getBaseIndentLevel(code);
        return Arrays.stream(code.split("\n"))
                .map(line -> removeBaseIndent(line, baseIndentLevel))
                .collect(Collectors.joining("\n"));
    }

    private int getBaseIndentLevel(String code) {
        return code.split("[^ [ \n]]", 2)[0]
                .replaceAll("\n", "")
                .length();
    }

    private String removeBaseIndent(String line, int baseIndentLevel) {
        return line.substring(Math.min(line.length(), baseIndentLevel));
    }
}
