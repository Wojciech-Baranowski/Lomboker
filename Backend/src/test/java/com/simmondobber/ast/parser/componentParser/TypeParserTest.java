package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.ArrayBrackets;
import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.complexComponentParser.TypeParser;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TypeParserTest {

    @Test
    public void parser_should_parse_type() {
        //Given
        String stringToParse = "Entity next";
        String correctlyParsedString = "Entity ";
        TypeParser typeParser = new TypeParser(new Pointer(stringToParse));

        //When
        Type parsedType = typeParser.parse();
        String parsedString = parsedType.getFullSyntax();
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(1, components.size());
        Assertions.assertInstanceOf(Path.class, components.get(0));
        Assertions.assertEquals("Entity ", components.get(0).getFullSyntax());
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
        String parsedString = parsedType.getFullSyntax();
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Path.class, components.get(0));
        Assertions.assertInstanceOf(Generic.class, components.get(1));
        Assertions.assertEquals("List", components.get(0).getFullSyntax());
        Assertions.assertEquals("<Long>", components.get(1).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_type_with_array_brackets() {
        //Given
        String stringToParse = "int[10][]";
        String correctlyParsedString = "int[10][]";
        TypeParser typeParser = new TypeParser(new Pointer(stringToParse));

        //When
        Type parsedType = typeParser.parse();
        String parsedString = parsedType.getFullSyntax();
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Path.class, components.get(0));
        Assertions.assertInstanceOf(ArrayBrackets.class, components.get(1));
        Assertions.assertInstanceOf(ArrayBrackets.class, components.get(2));
        Assertions.assertEquals("int", components.get(0).getFullSyntax());
        Assertions.assertEquals("[10]", components.get(1).getFullSyntax());
        Assertions.assertEquals("[]", components.get(2).getFullSyntax());
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
        String parsedString = parsedType.getFullSyntax();
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Path.class, components.get(0));
        Assertions.assertInstanceOf(Generic.class, components.get(1));
        Assertions.assertEquals("List", components.get(0).getFullSyntax());
        Assertions.assertEquals("<>", components.get(1).getFullSyntax());
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
        String parsedString = parsedType.getFullSyntax();
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Path.class, components.get(0));
        Assertions.assertInstanceOf(Generic.class, components.get(1));
        Assertions.assertEquals("List   ", components.get(0).getFullSyntax());
        Assertions.assertEquals("<\tLong > \n\n `123`\t  ", components.get(1).getFullSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
