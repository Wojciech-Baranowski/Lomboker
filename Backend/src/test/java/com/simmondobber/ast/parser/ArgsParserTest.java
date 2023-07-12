package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.parser.args.ArgsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgsParserTest {

    @Test
    public void parser_should_parse_args() {
        //Given
        String stringToParse = "(int x, Point p)";
        ArgsParser argsParser = new ArgsParser(stringToParse, new Pointer());

        //When
        Args parsedArgs = (Args) argsParser.parse();
        String parsedSyntax = parsedArgs.getSyntax();

        //Then
        Assertions.assertEquals(stringToParse, parsedSyntax);
    }

    @Test
    public void parser_should_parse_args_with_separators() {
        //Given
        String stringToParse = "(   int x, Point p ) \n\n $123!\t  next";
        String correctlyParsedString = "(   int x, Point p ) \n\n $123!\t  ";
        ArgsParser argsParser = new ArgsParser(stringToParse, new Pointer());

        //When
        Args parsedArgs = (Args) argsParser.parse();
        String parsedSyntax = parsedArgs.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedSyntax);
    }

    @Test
    public void parser_should_parse_empty_args() {
        //Given
        String stringToParse = "()";
        ArgsParser argsParser = new ArgsParser(stringToParse, new Pointer());

        //When
        Args parsedArgs = (Args) argsParser.parse();
        String parsedSyntax = parsedArgs.getSyntax();

        //Then
        Assertions.assertEquals(stringToParse, parsedSyntax);
    }
}
