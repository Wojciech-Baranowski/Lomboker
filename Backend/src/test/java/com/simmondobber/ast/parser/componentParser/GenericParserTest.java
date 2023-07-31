package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.GenericExtension;
import com.simmondobber.ast.parser.complexComponentParser.GenericParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenericParserTest {

    @Test
    public void parser_should_parse_generic() {
        //Given
        String stringToParse = "<T extends BaseEntity>";
        String correctlyParsedString = "<T extends BaseEntity>";
        GenericParser genericParser = new GenericParser(new Pointer(stringToParse));

        //When
        Generic parsedGeneric = genericParser.parse();
        String parsedString = parsedGeneric.getFullSyntax();
        List<AstComponent> components = parsedGeneric.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(GenericExtension.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("<", components.get(0).getFullSyntax());
        Assertions.assertEquals("T extends BaseEntity", components.get(1).getFullSyntax());
        Assertions.assertEquals(">", components.get(2).getFullSyntax());
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
        String parsedString = parsedGeneric.getFullSyntax();
        List<AstComponent> components = parsedGeneric.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(GenericExtension.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("< \n\n `123`\t   ", components.get(0).getFullSyntax());
        Assertions.assertEquals("T extends BaseEntity\t", components.get(1).getFullSyntax());
        Assertions.assertEquals(">  ", components.get(2).getFullSyntax());
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
        String parsedString = parsedGeneric.getFullSyntax();
        List<AstComponent> components = parsedGeneric.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(GenericExtension.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("<", components.get(0).getFullSyntax());
        Assertions.assertEquals("", components.get(1).getFullSyntax());
        Assertions.assertEquals(">", components.get(2).getFullSyntax());
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
        String parsedString = parsedGeneric.getFullSyntax();
        List<AstComponent> components = parsedGeneric.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(GenericExtension.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("<", components.get(0).getFullSyntax());
        Assertions.assertEquals("T extends List<Long>", components.get(1).getFullSyntax());
        Assertions.assertEquals(">", components.get(2).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
