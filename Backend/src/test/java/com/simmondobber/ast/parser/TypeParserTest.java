package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.parser.complexComponentParser.type.TypeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypeParserTest {

    @Test
    public void parser_should_parse_type() {
        //Given
        String stringToParse = "Entity";
        String correctlyParsedString = "Entity";
        TypeParser typeParser = new TypeParser(new Pointer(stringToParse));

        //When
        Type parsedType = typeParser.parse();
        String parsedString = parsedType.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_type_with_generic() {
        //Given
        String stringToParse = "List<Long>";
        String correctlyParsedString = "List<Long>";
        TypeParser typeParser = new TypeParser(new Pointer(stringToParse));

        //When
        Type parsedType = typeParser.parse();
        String parsedString = parsedType.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_type_with_separators() {
        //Given
        String stringToParse = "List   <\tLong > \n\n `123`\t  next";
        String correctlyParsedString = "List   <\tLong > \n\n `123`\t  ";
        TypeParser typeParser = new TypeParser(new Pointer(stringToParse));

        //When
        Type parsedType = typeParser.parse();
        String parsedString = parsedType.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_type_with_empty_generic() {
        //Given
        String stringToParse = "List<>";
        String correctlyParsedString = "List<>";
        TypeParser typeParser = new TypeParser(new Pointer(stringToParse));

        //When
        Type parsedType = typeParser.parse();
        String parsedString = parsedType.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
