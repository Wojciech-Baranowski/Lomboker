package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<AstComponent> components = parsedEnumValues.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Listing.class, components.get(0));
        Assertions.assertInstanceOf(Character.class, components.get(1));
        Assertions.assertEquals("VAL1, VAL2, VAL3,\nVAL4, VAL5", components.get(0).getSyntax());
        Assertions.assertEquals(";", components.get(1).getSyntax());
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
        List<AstComponent> components = parsedEnumValues.getChildAstComponents();

        //Then
        Assertions.assertEquals(1, components.size());
        Assertions.assertInstanceOf(Listing.class, components.get(0));
        Assertions.assertEquals("VAL1, VAL2, VAL3,\nVAL4, VAL5\n", components.get(0).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_enum_values_with_separators() {
        //Given
        String stringToParse = "VAL1, VAL2, VAL3,\nVAL4, VAL5  \t;  \n\n `123`\t next";
        String correctlyParsedString = "VAL1, VAL2, VAL3,\nVAL4, VAL5  \t;  \n\n `123`\t ";
        EnumValuesParser enumValuesParser = new EnumValuesParser(new Pointer(stringToParse));

        //When
        EnumValues parsedEnumValues = enumValuesParser.parse();
        String parsedString = parsedEnumValues.getSyntax();
        List<AstComponent> components = parsedEnumValues.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Listing.class, components.get(0));
        Assertions.assertInstanceOf(Character.class, components.get(1));
        Assertions.assertEquals("VAL1, VAL2, VAL3,\nVAL4, VAL5  \t", components.get(0).getSyntax());
        Assertions.assertEquals(";  \n\n `123`\t ", components.get(1).getSyntax());
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
        List<AstComponent> components = parsedEnumValues.getChildAstComponents();

        //Then
        Assertions.assertEquals(1, components.size());
        Assertions.assertInstanceOf(Listing.class, components.get(0));
        Assertions.assertEquals("VAL1, VAL2, VAL3,\nVAL4, VAL5\n  \n\n `123`\t", components.get(0).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
