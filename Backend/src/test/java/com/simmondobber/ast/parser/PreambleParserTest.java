package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Preamble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PreambleParserTest {

    @Test
    public void parser_should_parse_preamble() {
        //Given
        String stringToParse = "@Getter\nprivate final <T extends BaseEntity> @Setter next";
        String correctlyParsedString = "@Getter\nprivate final <T extends BaseEntity> @Setter ";
        PreambleParser preambleParser = new PreambleParser(new Pointer(stringToParse));

        //When
        Preamble parsedPreamble = preambleParser.parse();
        String parsedString = parsedPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_preamble_with_separators() {
        //Given
        String stringToParse = "@Getter \nprivate \n\n `123`\t final <T extends BaseEntity>  @Setter \tnext";
        String correctlyParsedString = "@Getter \nprivate \n\n `123`\t final <T extends BaseEntity>  @Setter \t";
        PreambleParser preambleParser = new PreambleParser(new Pointer(stringToParse));

        //When
        Preamble parsedPreamble = preambleParser.parse();
        String parsedString = parsedPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_preamble() {
        //Given
        String stringToParse = "";
        String correctlyParsedString = "";
        PreambleParser preambleParser = new PreambleParser(new Pointer(stringToParse));

        //When
        Preamble parsedPreamble = preambleParser.parse();
        String parsedString = parsedPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
