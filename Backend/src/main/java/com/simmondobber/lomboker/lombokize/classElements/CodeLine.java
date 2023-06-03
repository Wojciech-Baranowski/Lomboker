package com.simmondobber.lomboker.lombokize.classElements;

import lombok.Getter;

@Getter
public class CodeLine {

    private final String line;
    private final int nestingLevel;

    public CodeLine(String line) {
        this.line = line;
        this.nestingLevel = line.split("[^ ]", 2)[0].length() / 4;
    }

    public boolean isClassField() {
        return this.nestingLevel == 1 && isLineEndingWithSemicolon(this.line);
    }

    private boolean isLineEndingWithSemicolon(String line) {
        int indexOfLastCharacter = line.length() - 1;
        return line.charAt(indexOfLastCharacter) == ';';
    }
}
