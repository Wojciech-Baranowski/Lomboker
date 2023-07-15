package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<AstComponent> components = parsedClassContent.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Method.class, components.get(0));
        Assertions.assertInstanceOf(Field.class, components.get(1));
        Assertions.assertInstanceOf(Field.class, components.get(2));
        Assertions.assertInstanceOf(Method.class, components.get(3));
        Assertions.assertInstanceOf(Method.class, components.get(4));
        Assertions.assertEquals("public Point(int x, int y) {this.x = x;this.y = y;}", components.get(0).getSyntax());
        Assertions.assertEquals("private int x;", components.get(1).getSyntax());
        Assertions.assertEquals("private int y;", components.get(2).getSyntax());
        Assertions.assertEquals("public int getX() {return x;}", components.get(3).getSyntax());
        Assertions.assertEquals("public void setX(int x) {this.x = x;}", components.get(4).getSyntax());
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
        List<AstComponent> components = parsedClassContent.getChildAstComponents();

        //Then
        Assertions.assertEquals(6, components.size());
        Assertions.assertInstanceOf(Method.class, components.get(0));
        Assertions.assertInstanceOf(Field.class, components.get(1));
        Assertions.assertInstanceOf(Field.class, components.get(2));
        Assertions.assertInstanceOf(Class.class, components.get(3));
        Assertions.assertInstanceOf(Method.class, components.get(4));
        Assertions.assertInstanceOf(Method.class, components.get(5));
        Assertions.assertEquals("public Point(int x, int y) {this.x = x;this.y = y;}", components.get(0).getSyntax());
        Assertions.assertEquals("private int x;", components.get(1).getSyntax());
        Assertions.assertEquals("private int y;", components.get(2).getSyntax());
        Assertions.assertEquals("public enum PointType {TYPE1, TYPE2}", components.get(3).getSyntax());
        Assertions.assertEquals("public int getX() {return x;}", components.get(4).getSyntax());
        Assertions.assertEquals("public void setX(int x) {this.x = x;}\n", components.get(5).getSyntax());
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
        List<AstComponent> components = parsedClassContent.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Method.class, components.get(0));
        Assertions.assertInstanceOf(Field.class, components.get(1));
        Assertions.assertInstanceOf(Field.class, components.get(2));
        Assertions.assertInstanceOf(Method.class, components.get(3));
        Assertions.assertInstanceOf(Method.class, components.get(4));
        Assertions.assertEquals("public Point(int x, int y) {this.x = x;this.y = y;}\t\t ", components.get(0).getSyntax());
        Assertions.assertEquals("private int x;`123`", components.get(1).getSyntax());
        Assertions.assertEquals("private int y;\n   ", components.get(2).getSyntax());
        Assertions.assertEquals("public int getX() {return x;}", components.get(3).getSyntax());
        Assertions.assertEquals("public void setX(int x) {this.x = x;} \t ", components.get(4).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
