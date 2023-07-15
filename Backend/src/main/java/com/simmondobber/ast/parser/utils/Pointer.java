package com.simmondobber.ast.parser.utils;

import java.util.Map;

public class Pointer {

    private static final String WORD_REGEX = "[A-Za-z0-9_$]+";
    private static final String COMPOUND_WORD_REGEX = "[A-Za-z0-9_$.]+";
    private static final String SEPARATOR_REGEX = "[\\s`]+";
    private static final Map<Character, Character> PARENTHESIS = Map.of('(', ')', '{', '}', '<', '>', '[', ']');
    private static final Map<Character, Character> INSIDE_PARENTHESIS = Map.of('(', ')', '{', '}', '[', ']');

    private final String code;
    private int left;
    private int right;

    public Pointer(String code) {
        this.code = code;
        this.left = 0;
        this.right = 0;
    }

    public char lookupCharacter() {
        char character = 0;
        if (isNotOutOfBounds()) {
            character = this.code.charAt(this.right);
        }
        return character;
    }

    public String lookupWord() {
        String word = getWord();
        moveBackToCachedIndex();
        return word;
    }

    public char lookupCharacterAfterWordAndSeparator() {
        cacheIndex();
        omitWord();
        omitSeparator();
        char character = lookupCharacter();
        moveBackToCachedIndex();
        return character;
    }

    public int lookupIndexOf(char character) {
        return this.code.indexOf(character, this.right);
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

    public String getCompoundWord() {
        cacheIndex();
        omitCompoundWord();
        return getBetween();
    }

    public String getSeparator() {
        cacheIndex();
        omitSeparator();
        return getBetween();
    }

    public String getUntil(char stopCharacter) {
        cacheIndex();
        omitUntil(stopCharacter);
        return getBetween();
    }

    public String getUntil(String stopCharacters) {
        cacheIndex();
        omitUntil(stopCharacters);
        return getBetween();
    }

    public String getInside(char stopCharacter) {
        cacheIndex();
        omitInside(stopCharacter);
        return getBetween();
    }

    public String getInsideInclusive(char stopCharacter) {
        cacheIndex();
        omitInside(stopCharacter);
        return getBetween() + getCharacter();
    }

    public void omitCharacter() {
        this.right++;
    }

    public void omitWord() {
        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(WORD_REGEX)) {
            this.right++;
        }
    }

    public void omitCompoundWord() {
        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(COMPOUND_WORD_REGEX)) {
            this.right++;
        }
    }

    public void omitSeparator() {
        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(SEPARATOR_REGEX)) {
            if (this.code.charAt(this.right) == '`') {
                omitIdentifier();
            } else {
                this.right++;
            }
        }
    }

    public void omitUntil(char stopCharacter) {
        int balance = 0;
        while (isNotEnd() && (!isBalanced(balance) || this.code.charAt(this.right) != stopCharacter)) {
            this.right++;
            if (PARENTHESIS.containsKey(this.code.charAt(this.right))) {
                balance++;
            }
            if (PARENTHESIS.containsValue(this.code.charAt(this.right))) {
                balance--;
            }
        }
    }

    public void omitUntil(String stopCharacters) {
        int balance = 0;
        while (isNotEnd() && (!isBalanced(balance) || !stopCharacters.contains(Character.toString(this.code.charAt(this.right))))) {
            this.right++;
            if (PARENTHESIS.containsKey(this.code.charAt(this.right))) {
                balance++;
            }
            if (PARENTHESIS.containsValue(this.code.charAt(this.right))) {
                balance--;
            }
        }
    }

    public void omitInside(char stopCharacter) {
        int balance = 0;
        while (isNotEnd() && (!isBalanced(balance) || this.code.charAt(this.right) != stopCharacter)) {
            this.right++;
            if (INSIDE_PARENTHESIS.containsKey(this.code.charAt(this.right))) {
                balance++;
            }
            if (INSIDE_PARENTHESIS.containsValue(this.code.charAt(this.right))) {
                balance--;
            }
        }
    }

    public int getCurrentPosition() {
        return this.right;
    }

    public void setCurrentPosition(int index) {
        this.right = index;
    }

    private void omitIdentifier() {
        this.right = this.code.indexOf('`', this.right + 1) + 1;
    }

    private void cacheIndex() {
        this.left = this.right;
    }

    private void moveBackToCachedIndex() {
        this.right = this.left;
    }

    private String getBetween() {
        return this.code.substring(this.left, this.right);
    }

    private boolean isBalanced(int currentBalance) {
        return (currentBalance == -1 && isCurrentCharacterClosingParenthesis()) ||
                (currentBalance == 1 && isCurrentCharacterOpeningParenthesis()) ||
                (currentBalance == 0 && !isCurrentCharacterOpeningParenthesis() && !isCurrentCharacterClosingParenthesis());
    }

    private boolean isCurrentCharacterOpeningParenthesis() {
        return PARENTHESIS.containsKey(this.code.charAt(this.right));
    }

    private boolean isCurrentCharacterClosingParenthesis() {
        return PARENTHESIS.containsValue(this.code.charAt(this.right));
    }

    public boolean isNotEnd() {
        return this.right < this.code.length() - 1;
    }

    private boolean isNotOutOfBounds() {
        return this.right < this.code.length();
    }
}
