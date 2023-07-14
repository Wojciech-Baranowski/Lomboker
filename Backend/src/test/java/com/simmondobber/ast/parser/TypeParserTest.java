package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Generic;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Name;
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
        String parsedString = parsedType.getSyntax();
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(1, components.size());
        Assertions.assertInstanceOf(Name.class, components.get(0));
        Assertions.assertEquals("Entity ", components.get(0).getSyntax());
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
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Name.class, components.get(0));
        Assertions.assertInstanceOf(Generic.class, components.get(1));
        Assertions.assertEquals("List", components.get(0).getSyntax());
        Assertions.assertEquals("<Long>", components.get(1).getSyntax());
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
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Name.class, components.get(0));
        Assertions.assertInstanceOf(Generic.class, components.get(1));
        Assertions.assertEquals("List   ", components.get(0).getSyntax());
        Assertions.assertEquals("<\tLong > \n\n `123`\t  ", components.get(1).getSyntax());
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
        List<AstComponent> components = parsedType.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Name.class, components.get(0));
        Assertions.assertInstanceOf(Generic.class, components.get(1));
        Assertions.assertEquals("List", components.get(0).getSyntax());
        Assertions.assertEquals("<>", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
