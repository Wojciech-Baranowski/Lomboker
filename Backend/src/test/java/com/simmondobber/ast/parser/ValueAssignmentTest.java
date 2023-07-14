package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.ValueAssignment;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValueAssignmentTest {

    @Test
    public void parser_should_parse_value_assignment() {
        //Given
        String stringToParse = "= 123;";
        String correctlyParsedString = "= 123";
        ValueAssignmentParser valueAssignmentParser = new ValueAssignmentParser(new Pointer(stringToParse));

        //When
        ValueAssignment parsedValueAssignment = valueAssignmentParser.parse();
        String parsedString = parsedValueAssignment.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_value_assignment_with_separators() {
        //Given
        String stringToParse = "= 123  \n\n `123`\t  ;";
        String correctlyParsedString = "= 123  \n\n `123`\t  ";
        ValueAssignmentParser valueAssignmentParser = new ValueAssignmentParser(new Pointer(stringToParse));

        //When
        ValueAssignment parsedValueAssignment = valueAssignmentParser.parse();
        String parsedString = parsedValueAssignment.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

}
