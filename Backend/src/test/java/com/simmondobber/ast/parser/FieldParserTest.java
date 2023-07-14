package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldParserTest {

    @Test
    public void parser_should_parse_field() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point p;";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point p;";
        FieldParser fieldParser = new FieldParser(new Pointer(stringToParse));

        //When
        Field parsedfield = fieldParser.parse();
        String parsedString = parsedfield.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_field_with_value_assignment() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point p = new Point(1, 1);";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point p = new Point(1, 1);";
        FieldParser fieldParser = new FieldParser(new Pointer(stringToParse));

        //When
        Field parsedField = fieldParser.parse();
        String parsedString = parsedField.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_field_with_separators() {
        //Given
        String stringToParse = "@Getter\nprivate\t\t final @Setter private   Point \n\n\npp=new    Point(1, 1)   `123`;  next";
        String correctlyParsedString = "@Getter\nprivate\t\t final @Setter private   Point \n\n\npp=new    Point(1, 1)   `123`;  ";
        FieldParser fieldParser = new FieldParser(new Pointer(stringToParse));

        //When
        Field parsedField = fieldParser.parse();
        String parsedString = parsedField.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
