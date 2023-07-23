package com.simmondobber.lomboker.lombokize.boilerplateCleaner.setter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SetterNameFactoryTest {

    @ParameterizedTest
    @MethodSource("create_setter_name_test_data_provider")
    public void create_setter_name_test(String fieldType, String fieldName, String expectedSetterName) {
        //Given
        SetterNameFactory setterNameFactory = new SetterNameFactory(fieldType, fieldName);

        //When
        String actualSetterName = setterNameFactory.createSetterName();

        //Then
        Assertions.assertEquals(expectedSetterName, actualSetterName);
    }

    private static Stream<Arguments> create_setter_name_test_data_provider() {
        return Stream.of(
                Arguments.of("int", "point", "setPoint"),
                Arguments.of("int", "aPoint", "setAPoint"),
                Arguments.of("int", "isPoint", "setIsPoint"),
                Arguments.of("int", "isPrivate", "setIsPrivate"),
                Arguments.of("int", "isAbstract", "setIsAbstract"),
                Arguments.of("boolean", "point", "setPoint"),
                Arguments.of("boolean", "aPoint", "setAPoint"),
                Arguments.of("boolean", "isPoint", "setPoint"),
                Arguments.of("boolean", "isPrivate", "setPrivate"),
                Arguments.of("boolean", "isAbstract", "setAbstract"),
                Arguments.of("List<Integer>", "points", "setPoints"),
                Arguments.of("int[]", "points", "setPoints"),
                Arguments.of("List<Integer>[]", "points", "setPoints")
        );
    }
}
