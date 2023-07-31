package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.FieldParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FieldParserTest {

    @Test
    public void parser_should_parse_field() {
        //Given
        String stringToParse = "@Getter\nprivate final @Setter private Point p;";
        String correctlyParsedString = "@Getter\nprivate final @Setter private Point p;";
        FieldParser fieldParser = new FieldParser(new Pointer(stringToParse));

        //When
        Field parsedField = fieldParser.parse();
        String parsedString = parsedField.getFullSyntax();
        List<AstComponent> components = parsedField.getChildAstComponents();

        //Then
        Assertions.assertEquals(4, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(Character.class, components.get(3));
        Assertions.assertEquals("@Getter\nprivate final @Setter private ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point ", components.get(1).getFullSyntax());
        Assertions.assertEquals("p", components.get(2).getFullSyntax());
        Assertions.assertEquals(";", components.get(3).getFullSyntax());
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
        String parsedString = parsedField.getFullSyntax();
        List<AstComponent> components = parsedField.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(ValueAssignment.class, components.get(3));
        Assertions.assertInstanceOf(Character.class, components.get(4));
        Assertions.assertEquals("@Getter\nprivate final @Setter private ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point ", components.get(1).getFullSyntax());
        Assertions.assertEquals("p ", components.get(2).getFullSyntax());
        Assertions.assertEquals("= new Point(1, 1)", components.get(3).getFullSyntax());
        Assertions.assertEquals(";", components.get(4).getFullSyntax());
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
        String parsedString = parsedField.getFullSyntax();
        List<AstComponent> components = parsedField.getChildAstComponents();

        //Then
        Assertions.assertEquals(5, components.size());
        Assertions.assertInstanceOf(Preamble.class, components.get(0));
        Assertions.assertInstanceOf(Type.class, components.get(1));
        Assertions.assertInstanceOf(Name.class, components.get(2));
        Assertions.assertInstanceOf(ValueAssignment.class, components.get(3));
        Assertions.assertInstanceOf(Character.class, components.get(4));
        Assertions.assertEquals("@Getter\nprivate\t\t final @Setter private   ", components.get(0).getFullSyntax());
        Assertions.assertEquals("Point \n\n\n", components.get(1).getFullSyntax());
        Assertions.assertEquals("pp", components.get(2).getFullSyntax());
        Assertions.assertEquals("=new    Point(1, 1)   `123`", components.get(3).getFullSyntax());
        Assertions.assertEquals(";  ", components.get(4).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
