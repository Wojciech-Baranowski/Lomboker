package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.simpleAstComponents.Separator;

public abstract class AstParser {

    private static final String WORD_REGEX = "[A-Za-z0-9_$]+";
    private static final String WHITESPACE_REGEX = "\\s+";

    protected final String stringToParse;
    protected Pointer pointer;

    public AstParser(String stringToParse) {
        this.stringToParse = stringToParse;
        this.pointer = new Pointer();
    }

    public AstParser(String stringToParse, Pointer pointer) {
        this.stringToParse = stringToParse;
        this.pointer = pointer;
    }

    public abstract AstComponent parse();

    protected void omitCharacter() {
        this.pointer.increment();
    }

    protected void omitWord() {
        while (isPointerInbound() && (getNextCharacterAsString().matches(WORD_REGEX))) {
            this.pointer.increment();
        }
    }

    protected char getNextCharacterAndMove() {
        this.pointer.increment();
        return this.stringToParse.charAt(this.pointer.getIndex());
    }

    protected String getNextWordAndMove() {
        int startingIndex = this.pointer.getIndex();
        while (isPointerInbound() && (getNextCharacterAsString().matches(WORD_REGEX))) {
            this.pointer.increment();
        }
        return this.stringToParse.substring(startingIndex, this.pointer.getIndex());
    }

    protected Separator getNextSeparatorAndMove() {
        int startingIndex = this.pointer.getIndex();
        String separatorString = "";
        if (startingIndex < this.stringToParse.length() - 1) {
            while (isPointerInbound() && (getNextCharacterAsString().matches(WHITESPACE_REGEX) || getNextCharacterAsString().matches("\\$"))) {
                if (getNextCharacterAsString().equals("$")) {
                    this.pointer.setIndex(this.stringToParse.indexOf("!"));
                } else {
                    this.pointer.increment();
                }
            }
            separatorString = this.stringToParse.substring(startingIndex, this.pointer.getIndex() + 1);
        }
        return new Separator(separatorString);
    }

    protected String getUntilCharacterAndMove(char character) {
        int startingPosition = this.pointer.getIndex();
        int endingPosition = this.stringToParse.indexOf(')', this.pointer.getIndex());
        this.pointer.setIndex(endingPosition);
        return stringToParse.substring(startingPosition, endingPosition);
    }

    protected String getBetween(int left, int right) {
        return this.stringToParse.substring(left + 1, right);
    }

    protected int findCharacterPosition(char character) {
        return this.stringToParse.indexOf(character, this.pointer.getIndex());
    }

    protected int findWordPosition(String word) {
        return this.stringToParse.indexOf(word, this.pointer.getIndex());
    }

    private boolean isPointerInbound() {
        return this.pointer.getIndex() < this.stringToParse.length() - 1;
    }

    private String getNextCharacterAsString() {
        return Character.toString(this.stringToParse.charAt(this.pointer.getIndex() + 1));
    }

}
