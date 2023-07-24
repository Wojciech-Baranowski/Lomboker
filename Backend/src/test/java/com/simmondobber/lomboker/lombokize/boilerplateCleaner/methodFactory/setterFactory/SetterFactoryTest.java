package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setterFactory;

import com.simmondobber.ast.components.complexAstComponents.Field;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.complexComponentParser.PreambleParser;
import com.simmondobber.ast.parser.complexComponentParser.TypeParser;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.NameParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SetterFactoryTest {

    @ParameterizedTest
    @MethodSource("create_setter_test_data_provider")
    public void create_setter_test(String fieldType, String fieldName, String expectedMethodSyntax, String expectedMethodWithThisSyntax) {
        //Given
        Field field = create_test_field(fieldType, fieldName);
        SetterFactory setterFactory = new SetterFactory(field);

        //When
        Method setterMethod = setterFactory.createSetter();
        Method setterMethodWithThis = setterFactory.createSetterWithThis();

        //Then
        Assertions.assertEquals(expectedMethodSyntax, setterMethod.getSyntax());
        Assertions.assertEquals(expectedMethodWithThisSyntax, setterMethodWithThis.getSyntax());
    }

    private static Stream<Arguments> create_setter_test_data_provider() {
        return Stream.of(
                Arguments.of("int", "point", "public void setPoint(int point) {\n\tpoint = point;\n}\n", "public void setPoint(int point) {\n\tthis.point = point;\n}\n"),
                Arguments.of("int", "aPoint", "public void setAPoint(int aPoint) {\n\taPoint = aPoint;\n}\n", "public void setAPoint(int aPoint) {\n\tthis.aPoint = aPoint;\n}\n"),
                Arguments.of("int", "isPoint", "public void setIsPoint(int isPoint) {\n\tisPoint = isPoint;\n}\n", "public void setIsPoint(int isPoint) {\n\tthis.isPoint = isPoint;\n}\n"),
                Arguments.of("int", "isPrivate", "public void setIsPrivate(int isPrivate) {\n\tisPrivate = isPrivate;\n}\n", "public void setIsPrivate(int isPrivate) {\n\tthis.isPrivate = isPrivate;\n}\n"),
                Arguments.of("int", "isAbstract", "public void setIsAbstract(int isAbstract) {\n\tisAbstract = isAbstract;\n}\n", "public void setIsAbstract(int isAbstract) {\n\tthis.isAbstract = isAbstract;\n}\n"),
                Arguments.of("boolean", "point", "public void setPoint(boolean point) {\n\tpoint = point;\n}\n", "public void setPoint(boolean point) {\n\tthis.point = point;\n}\n"),
                Arguments.of("boolean", "aPoint", "public void setAPoint(boolean aPoint) {\n\taPoint = aPoint;\n}\n", "public void setAPoint(boolean aPoint) {\n\tthis.aPoint = aPoint;\n}\n"),
                Arguments.of("boolean", "isPoint", "public void setPoint(boolean point) {\n\tisPoint = point;\n}\n", "public void setPoint(boolean point) {\n\tthis.isPoint = point;\n}\n"),
                Arguments.of("boolean", "isPrivate", "public void setPrivate(boolean aPrivate) {\n\tisPrivate = aPrivate;\n}\n", "public void setPrivate(boolean aPrivate) {\n\tthis.isPrivate = aPrivate;\n}\n"),
                Arguments.of("boolean", "isAbstract", "public void setAbstract(boolean anAbstract) {\n\tisAbstract = anAbstract;\n}\n", "public void setAbstract(boolean anAbstract) {\n\tthis.isAbstract = anAbstract;\n}\n"),
                Arguments.of("List<Integer>", "points", "public void setPoints(List<Integer> points) {\n\tpoints = points;\n}\n", "public void setPoints(List<Integer> points) {\n\tthis.points = points;\n}\n"),
                Arguments.of("int[]", "points", "public void setPoints(int[] points) {\n\tpoints = points;\n}\n", "public void setPoints(int[] points) {\n\tthis.points = points;\n}\n"),
                Arguments.of("List<Integer>[]", "points", "public void setPoints(List<Integer>[] points) {\n\tpoints = points;\n}\n", "public void setPoints(List<Integer>[] points) {\n\tthis.points = points;\n}\n")
        );
    }

    private Field create_test_field(String fieldType, String fieldName) {
        Preamble preamble = new PreambleParser("public").parse();
        Type type = new TypeParser(fieldType).parse();
        Name name = new NameParser(fieldName).parse();
        Character semicolon = new CharacterParser(";").parse();
        return new Field(preamble, type, name, null, semicolon);
    }
}
