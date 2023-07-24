package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setterFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SetterArgsFactoryTest {

    @ParameterizedTest
    @MethodSource("create_setter_Args_test_data_provider")
    public void create_setter_args_test(String fieldType, String fieldArgs, String expectedSetterArgs) {
        //Given
        SetterArgsFactory setterArgsFactory = new SetterArgsFactory(fieldType, fieldArgs);

        //When
        String actualSetterArgs = setterArgsFactory.createSetterArgs();

        //Then
        Assertions.assertEquals(expectedSetterArgs, actualSetterArgs);
    }

    private static Stream<Arguments> create_setter_Args_test_data_provider() {
        return Stream.of(
                Arguments.of("int", "point", "(int point)"),
                Arguments.of("int", "aPoint", "(int aPoint)"),
                Arguments.of("int", "isPoint", "(int isPoint)"),
                Arguments.of("int", "isPrivate", "(int isPrivate)"),
                Arguments.of("int", "isAbstract", "(int isAbstract)"),
                Arguments.of("boolean", "point", "(boolean point)"),
                Arguments.of("boolean", "aPoint", "(boolean aPoint)"),
                Arguments.of("boolean", "isPoint", "(boolean point)"),
                Arguments.of("boolean", "isPrivate", "(boolean aPrivate)"),
                Arguments.of("boolean", "isAbstract", "(boolean anAbstract)"),
                Arguments.of("List<Integer>", "points", "(List<Integer> points)"),
                Arguments.of("int[]", "points", "(int[] points)"),
                Arguments.of("List<Integer>[]", "points", "(List<Integer>[] points)")
        );
    }
}
