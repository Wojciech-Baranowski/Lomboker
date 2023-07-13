package com.simmondobber.ast.parser.field;

import com.simmondobber.ast.components.complexAstComponents.field.preamble.FieldPreamble;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldPreambleParserTest {

    @Test
    public void parser_should_parse_field_preamble() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter next";
        String correctlyParsedString = "@Getter\nprivate final @Setter ";
        FieldPreambleParser fieldPreambleParser = new FieldPreambleParser(new Pointer(stringToParse));

        //When
        FieldPreamble parsedFieldPreamble = fieldPreambleParser.parse();
        String parsedString = parsedFieldPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_field_preamble_with_separators() {
        //Given
        String stringToParse = "@Getter \nprivate \n\n `123`\t final   @Setter \tnext";
        String correctlyParsedString = "@Getter \nprivate \n\n `123`\t final   @Setter \t";
        FieldPreambleParser fieldPreambleParser = new FieldPreambleParser(new Pointer(stringToParse));

        //When
        FieldPreamble parsedFieldPreamble = fieldPreambleParser.parse();
        String parsedString = parsedFieldPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_field_preamble() {
        //Given
        String stringToParse = "";
        String correctlyParsedString = "";
        FieldPreambleParser fieldPreambleParser = new FieldPreambleParser(new Pointer(stringToParse));

        //When
        FieldPreamble parsedFieldPreamble = fieldPreambleParser.parse();
        String parsedString = parsedFieldPreamble.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
