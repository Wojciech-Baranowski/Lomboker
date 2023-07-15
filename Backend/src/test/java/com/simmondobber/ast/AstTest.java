package com.simmondobber.ast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AstTest {

    @Test
    public void point_class_parse_test() {
        //Given
        String code = "package com.simmondobber.ast.parser.integrationTests.exampleClass;\n" +
                "\n" +
                "import lombok.Getter;\n" +
                "\n" +
                "@Getter\n" +
                "public class Point<T> implements GraphicsObject {\n" +
                "\n" +
                "    private int x;\n" +
                "    private int y;\n" +
                "\n" +
                "    public Point(int x, int y) {\n" +
                "        this.x = x;\n" +
                "        this.y = y;\n" +
                "    }\n" +
                "\n" +
                "    public Point(Point<T> other) {\n" +
                "        this.x = other.x;\n" +
                "        this.y = other.y;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void draw() {\n" +
                "        System.out.println(this.x);\n" +
                "        System.out.println(this.y);\n" +
                "    }\n" +
                "\n" +
                "    public void setX(int x) {\n" +
                "        this.x = x;\n" +
                "    }\n" +
                "\n" +
                "    public void setY(int y) {\n" +
                "        this.y = y;\n" +
                "    }\n" +
                "}\n";
        //When
        Ast ast = new Ast(code);
        String astSyntaxCode = ast.getCode();

        //Then
        Assertions.assertEquals(code, astSyntaxCode);
    }

    @Test
    public void pointer_class_parse_test() {
        //Given
        String code = "package com.simmondobber.ast.parser.utils;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class Pointer {\n" +
                "\n" +
                "    private static final String WORD_REGEX = \"[A-Za-z0-9_$]+\";\n" +
                "    private static final String COMPOUND_WORD_REGEX = \"[A-Za-z0-9_$.]+\";\n" +
                "    private static final String SEPARATOR_REGEX = \"[\\\\s`]+\";\n" +
                "    private static final Map<Character, Character> PARENTHESIS = Map.of('(', ')', '{', '}', '<', '>', '[', ']');\n" +
                "    private static final Map<Character, Character> INSIDE_PARENTHESIS = Map.of('(', ')', '{', '}', '[', ']');\n" +
                "\n" +
                "    private final String code;\n" +
                "    private int left;\n" +
                "    private int right;\n" +
                "\n" +
                "    public Pointer(String code) {\n" +
                "        this.code = code;\n" +
                "        this.left = 0;\n" +
                "        this.right = 0;\n" +
                "    }\n" +
                "\n" +
                "    public char lookupCharacter() {\n" +
                "        char character = 0;\n" +
                "        if (isNotOutOfBounds()) {\n" +
                "            character = this.code.charAt(this.right);\n" +
                "        }\n" +
                "        return character;\n" +
                "    }\n" +
                "\n" +
                "    public String lookupWord() {\n" +
                "        String word = getWord();\n" +
                "        moveBackToCachedIndex();\n" +
                "        return word;\n" +
                "    }\n" +
                "\n" +
                "    public char lookupCharacterAfterWordAndSeparator() {\n" +
                "        cacheIndex();\n" +
                "        omitWord();\n" +
                "        omitSeparator();\n" +
                "        char character = lookupCharacter();\n" +
                "        moveBackToCachedIndex();\n" +
                "        return character;\n" +
                "    }\n" +
                "\n" +
                "    public int lookupIndexOf(char character) {\n" +
                "        return this.code.indexOf(character, this.right);\n" +
                "    }\n" +
                "\n" +
                "    public String getCharacter() {\n" +
                "        String character = \"\";\n" +
                "        if (isNotOutOfBounds()) {\n" +
                "            character = Character.toString(this.code.charAt(this.right));\n" +
                "            omitCharacter();\n" +
                "        }\n" +
                "        return character;\n" +
                "    }\n" +
                "\n" +
                "    public String getWord() {\n" +
                "        cacheIndex();\n" +
                "        omitWord();\n" +
                "        return getBetween();\n" +
                "    }\n" +
                "\n" +
                "    public String getCompoundWord() {\n" +
                "        cacheIndex();\n" +
                "        omitCompoundWord();\n" +
                "        return getBetween();\n" +
                "    }\n" +
                "\n" +
                "    public String getSeparator() {\n" +
                "        cacheIndex();\n" +
                "        omitSeparator();\n" +
                "        return getBetween();\n" +
                "    }\n" +
                "\n" +
                "    public String getUntil(char stopCharacter) {\n" +
                "        cacheIndex();\n" +
                "        omitUntil(stopCharacter);\n" +
                "        return getBetween();\n" +
                "    }\n" +
                "\n" +
                "    public String getUntil(String stopCharacters) {\n" +
                "        cacheIndex();\n" +
                "        omitUntil(stopCharacters);\n" +
                "        return getBetween();\n" +
                "    }\n" +
                "\n" +
                "    public String getInside(char stopCharacter) {\n" +
                "        cacheIndex();\n" +
                "        omitInside(stopCharacter);\n" +
                "        return getBetween();\n" +
                "    }\n" +
                "\n" +
                "    public String getInsideInclusive(char stopCharacter) {\n" +
                "        cacheIndex();\n" +
                "        omitInside(stopCharacter);\n" +
                "        return getBetween() + getCharacter();\n" +
                "    }\n" +
                "\n" +
                "    public void omitCharacter() {\n" +
                "        this.right++;\n" +
                "    }\n" +
                "\n" +
                "    public void omitWord() {\n" +
                "        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(WORD_REGEX)) {\n" +
                "            this.right++;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void omitCompoundWord() {\n" +
                "        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(COMPOUND_WORD_REGEX)) {\n" +
                "            this.right++;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void omitSeparator() {\n" +
                "        while (isNotEnd() && Character.toString(this.code.charAt(this.right)).matches(SEPARATOR_REGEX)) {\n" +
                "            if (this.code.charAt(this.right) == '`') {\n" +
                "                omitIdentifier();\n" +
                "            } else {\n" +
                "                this.right++;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void omitUntil(char stopCharacter) {\n" +
                "        int balance = 0;\n" +
                "        while (isNotEnd() && (!isBalanced(balance) || this.code.charAt(this.right) != stopCharacter)) {\n" +
                "            this.right++;\n" +
                "            if (PARENTHESIS.containsKey(this.code.charAt(this.right))) {\n" +
                "                balance++;\n" +
                "            }\n" +
                "            if (PARENTHESIS.containsValue(this.code.charAt(this.right))) {\n" +
                "                balance--;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void omitUntil(String stopCharacters) {\n" +
                "        int balance = 0;\n" +
                "        while (isNotEnd() && (!isBalanced(balance) || !stopCharacters.contains(Character.toString(this.code.charAt(this.right))))) {\n" +
                "            this.right++;\n" +
                "            if (PARENTHESIS.containsKey(this.code.charAt(this.right))) {\n" +
                "                balance++;\n" +
                "            }\n" +
                "            if (PARENTHESIS.containsValue(this.code.charAt(this.right))) {\n" +
                "                balance--;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void omitInside(char stopCharacter) {\n" +
                "        int balance = 0;\n" +
                "        while (isNotEnd() && (!isBalanced(balance) || this.code.charAt(this.right) != stopCharacter)) {\n" +
                "            this.right++;\n" +
                "            if (INSIDE_PARENTHESIS.containsKey(this.code.charAt(this.right))) {\n" +
                "                balance++;\n" +
                "            }\n" +
                "            if (INSIDE_PARENTHESIS.containsValue(this.code.charAt(this.right))) {\n" +
                "                balance--;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public int getCurrentPosition() {\n" +
                "        return this.right;\n" +
                "    }\n" +
                "\n" +
                "    public void setCurrentPosition(int index) {\n" +
                "        this.right = index;\n" +
                "    }\n" +
                "\n" +
                "    private void omitIdentifier() {\n" +
                "        this.right = this.code.indexOf('`', this.right + 1) + 1;\n" +
                "    }\n" +
                "\n" +
                "    private void cacheIndex() {\n" +
                "        this.left = this.right;\n" +
                "    }\n" +
                "\n" +
                "    private void moveBackToCachedIndex() {\n" +
                "        this.right = this.left;\n" +
                "    }\n" +
                "\n" +
                "    private String getBetween() {\n" +
                "        return this.code.substring(this.left, this.right);\n" +
                "    }\n" +
                "\n" +
                "    private boolean isBalanced(int currentBalance) {\n" +
                "        return (currentBalance == -1 && isCurrentCharacterClosingParenthesis()) ||\n" +
                "                (currentBalance == 1 && isCurrentCharacterOpeningParenthesis()) ||\n" +
                "                (currentBalance == 0 && !isCurrentCharacterOpeningParenthesis() && !isCurrentCharacterClosingParenthesis());\n" +
                "    }\n" +
                "\n" +
                "    private boolean isCurrentCharacterOpeningParenthesis() {\n" +
                "        return PARENTHESIS.containsKey(this.code.charAt(this.right));\n" +
                "    }\n" +
                "\n" +
                "    private boolean isCurrentCharacterClosingParenthesis() {\n" +
                "        return PARENTHESIS.containsValue(this.code.charAt(this.right));\n" +
                "    }\n" +
                "\n" +
                "    public boolean isNotEnd() {\n" +
                "        return this.right < this.code.length() - 1;\n" +
                "    }\n" +
                "\n" +
                "    private boolean isNotOutOfBounds() {\n" +
                "        return this.right < this.code.length();\n" +
                "    }\n" +
                "}\n";

        //When
        Ast ast = new Ast(code);
        String astSyntaxCode = ast.getCode();

        //Then
        Assertions.assertEquals(code, astSyntaxCode);
    }
}
