package com.simmondobber.lomboker.codeElement;

import lombok.Getter;

@Getter
public class CodeLine {

    protected final String line;
    protected final boolean classField;
    protected final int nesting;

    public CodeLine(String line) {
        this.line = line;
        this.nesting = getNestingLevel();
        this.classField = isLineClassFieldDeclaration();
    }

    private int getNestingLevel() {
        char indentCharacter = this.line.charAt(0);
        int charactersPerIndentationLevel = (indentCharacter == '\t' ? 1 : 4);
        String characterOtherThanIndent = "[^" + indentCharacter + "]";
        return this.line.split(characterOtherThanIndent, 2)[0].length() / charactersPerIndentationLevel;
    }

    private boolean isLineClassFieldDeclaration() {
        return this.nesting == 1 && this.line.charAt(this.line.length() - 1) == ';';
    }
}
