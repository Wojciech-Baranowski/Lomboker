package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.ClassExtension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.complexComponentParser.ClassParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<AstComponent> components = parsedClass.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Keyword.class, components.get(1));
        Assertions.assertInstanceOf(Type.class, components.get(2));
        Assertions.assertInstanceOf(ClassExtension.class, components.get(3));
        Assertions.assertInstanceOf(ClassBody.class, components.get(4));
        Assertions.assertEquals("public ", components.get(0).getSyntax());
        Assertions.assertEquals("class ", components.get(1).getSyntax());
        Assertions.assertEquals("Point<int, int> ", components.get(2).getSyntax());
        Assertions.assertEquals("extends GraphicsObject ", components.get(3).getSyntax());
        Assertions.assertEquals("{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}", components.get(4).getSyntax());
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
        List<AstComponent> components = parsedClass.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Keyword.class, components.get(1));
        Assertions.assertInstanceOf(Type.class, components.get(2));
        Assertions.assertInstanceOf(ClassExtension.class, components.get(3));
        Assertions.assertInstanceOf(ClassBody.class, components.get(4));
        Assertions.assertEquals("public \t \t", components.get(0).getSyntax());
        Assertions.assertEquals("class`1234` ", components.get(1).getSyntax());
        Assertions.assertEquals("Point<int, int>    ", components.get(2).getSyntax());
        Assertions.assertEquals("extends GraphicsObject \t\t", components.get(3).getSyntax());
        Assertions.assertEquals("{VALUE;public Point(int x, int y) {this.x = x;this.y = y;}private int x;private int y;public int getX() {return x;}public void setX(int x) {this.x = x;}}\t\t\n\t ", components.get(4).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
