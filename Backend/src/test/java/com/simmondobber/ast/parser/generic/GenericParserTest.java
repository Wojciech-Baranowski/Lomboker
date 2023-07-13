package com.simmondobber.ast.parser.generic;

import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenericParserTest {

    @Test
    public void parser_should_parse_generic() {
        //Given
        String stringToParse = "<T extends BaseEntity>";
        String correctlyParsedString = "<T extends BaseEntity>";
        GenericParser genericParser = new GenericParser(new Pointer(stringToParse));

        //When
        Generic parsedGeneric = genericParser.parse();
        String parsedString = parsedGeneric.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_generic_with_separators() {
        //Given
        String stringToParse = "< \n\n `123`\t   T extends BaseEntity\t>  next";
        String correctlyParsedString = "< \n\n `123`\t   T extends BaseEntity\t>  ";
        GenericParser genericParser = new GenericParser(new Pointer(stringToParse));

        //When
        Generic parsedGeneric = genericParser.parse();
        String parsedString = parsedGeneric.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_generic() {
        //Given
        String stringToParse = "<>";
        String correctlyParsedString = "<>";
        GenericParser genericParser = new GenericParser(new Pointer(stringToParse));

        //When
        Generic parsedGeneric = genericParser.parse();
        String parsedString = parsedGeneric.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_nested_generic() {
        //Given
        String stringToParse = "<T extends List<Long>>";
        String correctlyParsedString = "<T extends List<Long>>";
        GenericParser genericParser = new GenericParser(new Pointer(stringToParse));

        //When
        Generic parsedGeneric = genericParser.parse();
        String parsedString = parsedGeneric.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
