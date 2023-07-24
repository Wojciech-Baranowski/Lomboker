package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.getter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class GetterNameFactoryTest {

    @ParameterizedTest
    @MethodSource("create_getter_name_test_data_provider")
    public void create_getter_name_test(String fieldType, String fieldName, String expectedGetterName) {
        //Given
        GetterNameFactory getterNameFactory = new GetterNameFactory(fieldType, fieldName);

        //When
        String actualGetterName = getterNameFactory.createGetterName();

        //Then
        Assertions.assertEquals(expectedGetterName, actualGetterName);
    }

    private static Stream<Arguments> create_getter_name_test_data_provider() {
        return Stream.of(
                Arguments.of("int", "point", "getPoint"),
                Arguments.of("int", "aPoint", "getAPoint"),
                Arguments.of("int", "isPoint", "getIsPoint"),
                Arguments.of("int", "isPrivate", "getIsPrivate"),
                Arguments.of("int", "isAbstract", "getIsAbstract"),
                Arguments.of("boolean", "point", "isPoint"),
                Arguments.of("boolean", "aPoint", "isAPoint"),
                Arguments.of("boolean", "isPoint", "isPoint"),
                Arguments.of("boolean", "isPrivate", "isPrivate"),
                Arguments.of("boolean", "isAbstract", "isAbstract"),
                Arguments.of("List<Integer>", "points", "getPoints"),
                Arguments.of("int[]", "points", "getPoints"),
                Arguments.of("List<Integer>[]", "points", "getPoints")
        );
    }
}
