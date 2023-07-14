package com.simmondobber.ast.parser.method;

import com.simmondobber.ast.components.complexAstComponents.method.Method;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodParserTest {

    @Test
    public void parser_should_parse_method() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point calculate(int x, int y);";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point calculate(int x, int y);";
        MethodParser methodParser = new MethodParser(new Pointer(stringToParse));

        //When
        Method parsedMethod = methodParser.parse();
        String parsedString = parsedMethod.getSyntax();

        //Then
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
        String parsedString = parsedMethod.getSyntax();

        //Then
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
        String parsedString = parsedMethod.getSyntax();

        //Then
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
        String parsedString = parsedMethod.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
