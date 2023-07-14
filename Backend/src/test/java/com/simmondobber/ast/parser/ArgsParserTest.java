package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        //Then
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

        //Then
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

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
