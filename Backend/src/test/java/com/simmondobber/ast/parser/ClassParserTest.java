package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassParserTest {

    @Test
    public void parser_should_parse_class() {
        //Given
        String stringToParse = "public class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        String correctlyParsedString = "public class Point<int, int> extends GraphicsObject {VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        ClassParser classParser = new ClassParser(new Pointer(stringToParse));

        //When
        Class parsedClass = classParser.parse();
        String parsedString = parsedClass.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_with_separators() {
        //Given
        String stringToParse = "public \t \tclass`1234` Point<int, int>    extends GraphicsObject \t\t{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}\t\t\n\t next";
        String correctlyParsedString = "public \t \tclass`1234` Point<int, int>    extends GraphicsObject \t\t{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}\t\t\n\t ";
        ClassParser classParser = new ClassParser(new Pointer(stringToParse));

        //When
        Class parsedClass = classParser.parse();
        String parsedString = parsedClass.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
