package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.parser.utils.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnnotationParserTest {

    @Test
    public void parser_should_parse_annotation() {
        //Given
        String stringToParse = "@Getter(value = AccessLevel.NONE)";
        String correctlyParsedString = "@Getter(value = AccessLevel.NONE)";
        AnnotationParser annotationParser = new AnnotationParser(new Pointer(stringToParse));

        //When
        Annotation parsedAnnotation = annotationParser.parse();
        String parsedString = parsedAnnotation.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_annotation_with_separators() {
        //Given
        String stringToParse = "@\n\t Getter  \n\n `123`\t(value = AccessLevel.NONE)  \t next";
        String correctlyParsedString = "@\n\t Getter  \n\n `123`\t(value = AccessLevel.NONE)  \t ";
        AnnotationParser annotationParser = new AnnotationParser(new Pointer(stringToParse));

        //When
        Annotation parsedAnnotation = annotationParser.parse();
        String parsedString = parsedAnnotation.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }

    @Test
    public void parser_should_parse_annotation_without_args() {
        //Given
        String stringToParse = "@Getter()";
        String correctlyParsedString = "@Getter()";
        AnnotationParser annotationParser = new AnnotationParser(new Pointer(stringToParse));

        //When
        Annotation parsedAnnotation = annotationParser.parse();
        String parsedString = parsedAnnotation.getSyntax();

        //Then
        Assertions.assertEquals(correctlyParsedString, parsedString);
    }
}
