package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<AstComponent> components = parsedClassBody.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(EnumValues.class, components.get(1));
        Assertions.assertInstanceOf(ClassContent.class, components.get(2));
        Assertions.assertInstanceOf(Character.class, components.get(3));
        Assertions.assertEquals("{", components.get(0).getSyntax());
        Assertions.assertEquals("VALUE;", components.get(1).getSyntax());
        Assertions.assertEquals("public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}", components.get(2).getSyntax());
        Assertions.assertEquals("}", components.get(3).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_body_with_only_enum_values() {
        //Given
        String stringToParse = "{VALUE1, VALUE2\n}$";
        String correctlyParsedString = "{VALUE1, VALUE2\n}";
        ClassBodyParser classBodyParser = new ClassBodyParser(new Pointer(stringToParse));

        //When
        ClassBody parsedClassBody = classBodyParser.parse();
        String parsedString = parsedClassBody.getSyntax();
        List<AstComponent> components = parsedClassBody.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(EnumValues.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("{", components.get(0).getSyntax());
        Assertions.assertEquals("VALUE1, VALUE2\n", components.get(1).getSyntax());
        Assertions.assertEquals("}", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_class_body() {
        //Given
        String stringToParse = "{}$";
        String correctlyParsedString = "{}";
        ClassBodyParser classBodyParser = new ClassBodyParser(new Pointer(stringToParse));

        //When
        ClassBody parsedClassBody = classBodyParser.parse();
        String parsedString = parsedClassBody.getSyntax();
        List<AstComponent> components = parsedClassBody.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Character.class, components.get(1));
        Assertions.assertEquals("{", components.get(0).getSyntax());
        Assertions.assertEquals("}", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_class_body_with_separators() {
        //Given
        String stringToParse = "{\npublic Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;}} \t next";
        String correctlyParsedString = "{\npublic Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;}} \t ";
        ClassBodyParser classBodyParser = new ClassBodyParser(new Pointer(stringToParse));

        //When
        ClassBody parsedClassBody = classBodyParser.parse();
        String parsedString = parsedClassBody.getSyntax();
        List<AstComponent> components = parsedClassBody.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(ClassContent.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("{\n", components.get(0).getSyntax());
        Assertions.assertEquals("public Point(int x, int y) {this.x = x;this.y = y;}\t\t private int x;`123`private int y;\n   public int getX() {return x;}public void setX(int x) {this.x = x;}", components.get(1).getSyntax());
        Assertions.assertEquals("} \t ", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
