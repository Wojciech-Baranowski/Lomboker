package com.simmondobber.ast.parser.method;

import com.simmondobber.ast.components.complexAstComponents.method.preamble.MethodPreamble;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodPreambleParserTest {

    @Test
    public void parser_should_parse_method_preamble() {
        //Given
        String stringToParse = "@Getter\nprivate final <T extends BaseEntity> @Setter next";
        String correctlyParsedString = "@Getter\nprivate final <T extends BaseEntity> @Setter ";
        MethodPreambleParser methodPreambleParser = new MethodPreambleParser(new Pointer(stringToParse));

        //When
        MethodPreamble parsedMethodPreamble = methodPreambleParser.parse();
        String parsedString = parsedMethodPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_method_preamble_with_separators() {
        //Given
        String stringToParse = "@Getter \nprivate \n\n `123`\t final <T extends BaseEntity>  @Setter \tnext";
        String correctlyParsedString = "@Getter \nprivate \n\n `123`\t final <T extends BaseEntity>  @Setter \t";
        MethodPreambleParser methodPreambleParser = new MethodPreambleParser(new Pointer(stringToParse));

        //When
        MethodPreamble parsedMethodPreamble = methodPreambleParser.parse();
        String parsedString = parsedMethodPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_method_preamble() {
        //Given
        String stringToParse = "";
        String correctlyParsedString = "";
        MethodPreambleParser methodPreambleParser = new MethodPreambleParser(new Pointer(stringToParse));

        //When
        MethodPreamble parsedMethodPreamble = methodPreambleParser.parse();
        String parsedString = parsedMethodPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
