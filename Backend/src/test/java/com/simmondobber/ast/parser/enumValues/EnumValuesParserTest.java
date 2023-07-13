package com.simmondobber.ast.parser.enumValues;

import com.simmondobber.ast.components.complexAstComponents.enumValues.EnumValues;
import com.simmondobber.ast.parser.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumValuesParserTest {

    @Test
    public void parser_should_parse_enum_values() {
        //Given
        String stringToParse = "VAL1, VAL2, VAL3,\nVAL4, VAL5;";
        String correctlyParsedString = "VAL1, VAL2, VAL3,\nVAL4, VAL5;";
        EnumValuesParser enumValuesParser = new EnumValuesParser(new Pointer(stringToParse));

        //When
        EnumValues parsedEnumValues = enumValuesParser.parse();
        String parsedString = parsedEnumValues.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_enum_values_in_enum_only_class() {
        //Given
        String stringToParse = "VAL1, VAL2, VAL3,\nVAL4, VAL5\n}";
        String correctlyParsedString = "VAL1, VAL2, VAL3,\nVAL4, VAL5\n";
        EnumValuesParser enumValuesParser = new EnumValuesParser(new Pointer(stringToParse));

        //When
        EnumValues parsedEnumValues = enumValuesParser.parse();
        String parsedString = parsedEnumValues.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_enum_values_with_separators() {
        //Given
        String stringToParse = "VAL1, VAL2, VAL3,\nVAL4, VAL5  \t;  \n\n `123`\t";
        String correctlyParsedString = "VAL1, VAL2, VAL3,\nVAL4, VAL5  \t;  \n\n `123`\t";
        EnumValuesParser enumValuesParser = new EnumValuesParser(new Pointer(stringToParse));

        //When
        EnumValues parsedEnumValues = enumValuesParser.parse();
        String parsedString = parsedEnumValues.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_enum_values_in_enum_only_class_with_separators() {
        //Given
        String stringToParse = "VAL1, VAL2, VAL3,\nVAL4, VAL5\n  \n\n `123`\t}";
        String correctlyParsedString = "VAL1, VAL2, VAL3,\nVAL4, VAL5\n  \n\n `123`\t";
        EnumValuesParser enumValuesParser = new EnumValuesParser(new Pointer(stringToParse));

        //When
        EnumValues parsedEnumValues = enumValuesParser.parse();
        String parsedString = parsedEnumValues.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
