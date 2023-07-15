package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Listing;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ArgsParserTest {

    @Test
    public void parser_should_parse_args() {
        //Given
        String stringToParse = "(int x, Point p)";
        String correctlyParsedString = "(int x, Point p)";
        ArgsParser argsParser = new ArgsParser(new Pointer(stringToParse));

        //When
        Args parsedArgs = argsParser.parse();
        String parsedString = parsedArgs.getSyntax();
        List<AstComponent> components = parsedArgs.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Listing.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("(", components.get(0).getSyntax());
        Assertions.assertEquals("int x, Point p", components.get(1).getSyntax());
        Assertions.assertEquals(")", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_args_with_separators() {
        //Given
        String stringToParse = "(   int x, Point p )  \n\n `123`\t  next";
        String correctlyParsedString = "(   int x, Point p )  \n\n `123`\t  ";
        ArgsParser argsParser = new ArgsParser(new Pointer(stringToParse));

        //When
        Args parsedArgs = argsParser.parse();
        String parsedString = parsedArgs.getSyntax();
        List<AstComponent> components = parsedArgs.getChildAstComponents();

        //Then
        Assertions.assertEquals(3, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Listing.class, components.get(1));
        Assertions.assertInstanceOf(Character.class, components.get(2));
        Assertions.assertEquals("(   ", components.get(0).getSyntax());
        Assertions.assertEquals("int x, Point p ", components.get(1).getSyntax());
        Assertions.assertEquals(")  \n\n `123`\t  ", components.get(2).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_empty_args() {
        //Given
        String stringToParse = "()";
        String correctlyParsedString = "()";
        ArgsParser argsParser = new ArgsParser(new Pointer(stringToParse));

        //When
        Args parsedArgs = argsParser.parse();
        String parsedString = parsedArgs.getSyntax();
        List<AstComponent> components = parsedArgs.getChildAstComponents();

        //Then
        Assertions.assertEquals(2, components.size());
        Assertions.assertInstanceOf(Character.class, components.get(0));
        Assertions.assertInstanceOf(Character.class, components.get(1));
        Assertions.assertEquals("(", components.get(0).getSyntax());
        Assertions.assertEquals(")", components.get(1).getSyntax());
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
