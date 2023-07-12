package com.simmondobber.ast.parser;

import java.util.Map;

public class Pointer {

    private static final String WORD_REGEX = "[A-Za-z0-9_$]+";
    private static final String SEPARATOR_REGEX = "[\\s`]+";
    private static final Map<Character, Character> PARENTHESIS = Map.of('(', ')', '{', '}', '<', '>', '[', ']');

    private final String code;
    private int left;
    private int right;

    public Pointer(String code) {
        this.code = code;
        this.left = 0;
        this.right = 0;
    }

    public String getCharacter() {
        String character = "";
        if (isNotOutOfBounds()) {
            character = Character.toString(this.code.charAt(this.right));
            omitCharacter();
        }
        return character;
    }

    public String getWord() {
        cacheIndex();
        omitWord();
        return getBetween();
    }

    public String getSeparator() {
        cacheIndex();
        omitSeparator();
        return getBetween();
    }

    public String getUntil(char stopCharacter) {
        cacheIndex();
        omitUntil(stopCharacter, 0);
        return getBetween();
    }

    public String getInside(char stopCharacter) {
        cacheIndex();
        omitUntil(stopCharacter, 1);
        return getBetween();
    }

    public void omitCharacter() {
        this.right++;
    }

    public void omitWord() {
        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(WORD_REGEX)) {
            this.right++;
        }
    }

    public void omitSeparator() {
        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(SEPARATOR_REGEX)) {
            if (this.code.charAt(this.right) == '`') {
                omitIdentifier();
            }
            this.right++;
        }
    }

    public void omitUntil(char stopCharacter, int initialBalance) {
        int balance = initialBalance;
        while (isNotEnd() && (balance != 0 || this.code.charAt(this.right) != stopCharacter)) {
            this.right++;
            if (PARENTHESIS.containsKey(this.code.charAt(this.right))) {
                balance++;
            }
            if (PARENTHESIS.containsValue(this.code.charAt(this.right))) {
                balance--;
            }
        }
    }

    private void omitIdentifier() {
        this.right = this.code.indexOf('`', this.right + 1) + 1;
    }

    private void cacheIndex() {
        this.left = this.right;
    }

    private String getBetween() {
        return this.code.substring(this.left, this.right);
    }

    private boolean isNotEnd() {
        return this.right < this.code.length() - 1;
    }

    private boolean isNotOutOfBounds() {
        return this.right < this.code.length();
    }
}
