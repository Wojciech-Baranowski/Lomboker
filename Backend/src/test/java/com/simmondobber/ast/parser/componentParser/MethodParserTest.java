package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.MethodParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MethodParserTest {

    @Test
    public void parser_should_parse_method() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point calculate(int x, int y);";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point calculate(int x, int y);";
        MethodParser methodParser = new MethodParser(new Pointer(stringToParse));

        //When
        Method parsedMethod = methodParser.parse();
        String parsedString = parsedMethod.getFullSyntax();
        List<AstComponent> components = parsedMethod.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(Args.class, components.get(3));
        Assertions.assertInstanceOf(Character.class, components.get(4));
        Assertions.assertEquals("@Getter\nprivate final @Setter private ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point ", components.get(1).getFullSyntax());
        Assertions.assertEquals("calculate", components.get(2).getFullSyntax());
        Assertions.assertEquals("(int x, int y)", components.get(3).getFullSyntax());
        Assertions.assertEquals(";", components.get(4).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_constructor_method() {
        //Given
        String stringToParse = "public Point(int x, int y) {\nthis.x = x;\nthis.y = y;\n}\n next";
        String correctlyParsedString = "public Point(int x, int y) {\nthis.x = x;\nthis.y = y;\n}\n ";
        MethodParser methodParser = new MethodParser(new Pointer(stringToParse));

        //When
        Method parsedMethod = methodParser.parse();
        String parsedString = parsedMethod.getFullSyntax();
        List<AstComponent> components = parsedMethod.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Args.class, components.get(2));
        Assertions.assertInstanceOf(MethodBody.class, components.get(3));
        Assertions.assertEquals("public ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point", components.get(1).getFullSyntax());
        Assertions.assertEquals("(int x, int y) ", components.get(2).getFullSyntax());
        Assertions.assertEquals("{\nthis.x = x;\nthis.y = y;\n}\n ", components.get(3).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_method_with_body() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point calculate(int x, int y) {\nthis.x = x;\nthis.y = y;\n}\n next";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point calculate(int x, int y) {\nthis.x = x;\nthis.y = y;\n}\n ";
        MethodParser methodParser = new MethodParser(new Pointer(stringToParse));

        //When
        Method parsedMethod = methodParser.parse();
        String parsedString = parsedMethod.getFullSyntax();
        List<AstComponent> components = parsedMethod.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(Args.class, components.get(3));
        Assertions.assertInstanceOf(MethodBody.class, components.get(4));
        Assertions.assertEquals("@Getter\nprivate final @Setter private ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point ", components.get(1).getFullSyntax());
        Assertions.assertEquals("calculate", components.get(2).getFullSyntax());
        Assertions.assertEquals("(int x, int y) ", components.get(3).getFullSyntax());
        Assertions.assertEquals("{\nthis.x = x;\nthis.y = y;\n}\n ", components.get(4).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_method_with_throws() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point calculate(int x, int y) throws NullPointerException, ValidatorException {\nthis.x = x;\nthis.y = y;\n}\n next";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point calculate(int x, int y) throws NullPointerException, ValidatorException {\nthis.x = x;\nthis.y = y;\n}\n ";
        MethodParser methodParser = new MethodParser(new Pointer(stringToParse));

        //When
        Method parsedMethod = methodParser.parse();
        String parsedString = parsedMethod.getFullSyntax();
        List<AstComponent> components = parsedMethod.getChildAstComponents();

        //Then
        Assertions.assertEquals(6, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(Args.class, components.get(3));
        Assertions.assertInstanceOf(Throws.class, components.get(4));
        Assertions.assertInstanceOf(MethodBody.class, components.get(5));
        Assertions.assertEquals("@Getter\nprivate final @Setter private ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point ", components.get(1).getFullSyntax());
        Assertions.assertEquals("calculate", components.get(2).getFullSyntax());
        Assertions.assertEquals("(int x, int y) ", components.get(3).getFullSyntax());
        Assertions.assertEquals("throws NullPointerException, ValidatorException ", components.get(4).getFullSyntax());
        Assertions.assertEquals("{\nthis.x = x;\nthis.y = y;\n}\n ", components.get(5).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_method_with_body_and_separators() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter\t private Point\n calculate (int x  , int y ) `123`\t {\nthis.x = x;\nthis.y = y;\n}\t\n next";
        String correctlyParsedString = "@Getter\nprivate final @Setter\t private Point\n calculate (int x  , int y ) `123`\t {\nthis.x = x;\nthis.y = y;\n}\t\n ";
        MethodParser methodParser = new MethodParser(new Pointer(stringToParse));

        //When
        Method parsedMethod = methodParser.parse();
        String parsedString = parsedMethod.getFullSyntax();
        List<AstComponent> components = parsedMethod.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(Args.class, components.get(3));
        Assertions.assertInstanceOf(MethodBody.class, components.get(4));
        Assertions.assertEquals("@Getter\nprivate final @Setter\t private ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point\n ", components.get(1).getFullSyntax());
        Assertions.assertEquals("calculate ", components.get(2).getFullSyntax());
        Assertions.assertEquals("(int x  , int y ) `123`\t ", components.get(3).getFullSyntax());
        Assertions.assertEquals("{\nthis.x = x;\nthis.y = y;\n}\t\n ", components.get(4).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
