package com.simmondobber.ast.parser.class_;

import com.simmondobber.ast.components.complexAstComponents.class_.ClassBody;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassBodyParserTest {

    @Test
    public void parser_should_parse_class_body() {
        //Given
        String stringToParse = "{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        String correctlyParsedString = "{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        ClassBodyParser classBodyParser = new ClassBodyParser(new Pointer(stringToParse));

        //When
        ClassBody parsedClassBody = classBodyParser.parse();
        String parsedString = parsedClassBody.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_with_nested_class() {
        //Given
        String stringToParse = "{\npublic Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;}} \t next";
        String correctlyParsedString = "{\npublic Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;}} \t ";
        ClassBodyParser classBodyParser = new ClassBodyParser(new Pointer(stringToParse));

        //When
        ClassBody parsedClassBody = classBodyParser.parse();
        String parsedString = parsedClassBody.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_content_with_separators() {
        //Given
        String stringToParse = "{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        String correctlyParsedString = "{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        ClassBodyParser classBodyParser = new ClassBodyParser(new Pointer(stringToParse));

        //When
        ClassBody parsedClassBody = classBodyParser.parse();
        String parsedString = parsedClassBody.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
