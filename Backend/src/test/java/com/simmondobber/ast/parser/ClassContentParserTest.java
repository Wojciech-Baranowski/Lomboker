package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassContentParserTest {

    @Test
    public void parser_should_parse_class_content() {
        //Given
        String stringToParse = "public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}";
        String correctlyParsedString = "public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}";
        ClassContentParser classContentParser = new ClassContentParser(new Pointer(stringToParse));

        //When
        ClassContent parsedClassContent = classContentParser.parse();
        String parsedString = parsedClassContent.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_with_nested_class() {
        //Given
        String stringToParse = "public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public enum PointType {TYPE1, TYPE2}public int getX() {return x;}public void setX(int x) {this.x = x;}\n}";
        String correctlyParsedString = "public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public enum PointType {TYPE1, TYPE2}public int getX() {return x;}public void setX(int x) {this.x = x;}\n";
        ClassContentParser classContentParser = new ClassContentParser(new Pointer(stringToParse));

        //When
        ClassContent parsedClassContent = classContentParser.parse();
        String parsedString = parsedClassContent.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_content_with_separators() {
        //Given
        String stringToParse = "public Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;} \t }";
        String correctlyParsedString = "public Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;} \t ";
        ClassContentParser classContentParser = new ClassContentParser(new Pointer(stringToParse));

        //When
        ClassContent parsedClassContent = classContentParser.parse();
        String parsedString = parsedClassContent.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
