package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.lomboker.common.AnnotationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class AnnotationFactoryTest {

    private final AnnotationFactory annotationFactory = new AnnotationFactory();

    @ParameterizedTest
    @MethodSource("create_annotation_provider")
    public void create_annotation_test(AnnotationData annotationData, String expectedAnnotationSyntax) {
        //When
        String actualAnnotationSyntax = this.annotationFactory.createAnnotation(annotationData, "\n").getFullSyntax();

        //Then
        Assertions.assertEquals(expectedAnnotationSyntax, actualAnnotationSyntax);
    }

    @Test
    public void create_field_annotations_based_on_config_test() {
        //Given
        List<AnnotationData> annotationsData = List.of();
        List<Annotation> expectedListOfAnnotations = List.of(
                this.annotationFactory.createAnnotation(AnnotationData.GETTER, ""),
                this.annotationFactory.createAnnotation(AnnotationData.SETTER, "")
        );

        //When
        List<Annotation> actualListOfAnnotations = this.annotationFactory.createFieldLombokAnnotations(annotationsData, "");

        //Then
        Assertions.assertEquals(expectedListOfAnnotations.size(), actualListOfAnnotations.size());
        for (int i = 0; i < expectedListOfAnnotations.size(); i++) {
            Assertions.assertEquals(expectedListOfAnnotations.get(i).getFullSyntax(), actualListOfAnnotations.get(i).getFullSyntax());
        }
    }

    @Test
    public void create_class_annotations_based_on_config_test() {
        //Given
        List<AnnotationData> annotationsData = List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.ALL_ARGS_CONSTRUCTOR, AnnotationData.BUILDER_TO_BUILDER);
        List<Annotation> expectedListOfAnnotations = List.of(
                this.annotationFactory.createAnnotation(AnnotationData.GETTER, ""),
                this.annotationFactory.createAnnotation(AnnotationData.SETTER, ""),
                this.annotationFactory.createAnnotation(AnnotationData.ALL_ARGS_CONSTRUCTOR, ""),
                this.annotationFactory.createAnnotation(AnnotationData.BUILDER_TO_BUILDER, "")
        );

        //When
        List<Annotation> actualListOfAnnotations = this.annotationFactory.createClassLombokAnnotations(annotationsData, "");

        //Then
        Assertions.assertEquals(expectedListOfAnnotations.size(), actualListOfAnnotations.size());
        for (int i = 0; i < expectedListOfAnnotations.size(); i++) {
            Assertions.assertEquals(expectedListOfAnnotations.get(i).getFullSyntax(), actualListOfAnnotations.get(i).getFullSyntax());
        }
    }

    @Test
    public void create_enum_annotations_based_on_config_test() {
        //Given
        List<AnnotationData> annotationsData = List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.ALL_ARGS_CONSTRUCTOR, AnnotationData.BUILDER_TO_BUILDER);
        List<Annotation> expectedListOfAnnotations = List.of(
                this.annotationFactory.createAnnotation(AnnotationData.GETTER, ""),
                this.annotationFactory.createAnnotation(AnnotationData.ALL_ARGS_CONSTRUCTOR, "")
        );

        //When
        List<Annotation> actualListOfAnnotations = this.annotationFactory.createEnumLombokAnnotations(annotationsData, "");

        //Then
        Assertions.assertEquals(expectedListOfAnnotations.size(), actualListOfAnnotations.size());
        for (int i = 0; i < expectedListOfAnnotations.size(); i++) {
            Assertions.assertEquals(expectedListOfAnnotations.get(i).getFullSyntax(), actualListOfAnnotations.get(i).getFullSyntax());
        }
    }

    private static Stream<Arguments> create_annotation_provider() {
        return Stream.of(
                Arguments.of(AnnotationData.GETTER, "@Getter\n"),
                Arguments.of(AnnotationData.SETTER, "@Setter\n"),
                Arguments.of(AnnotationData.NO_ARGS_CONSTRUCTOR, "@NoArgsConstructor\n"),
                Arguments.of(AnnotationData.ALL_ARGS_CONSTRUCTOR, "@AllArgsConstructor\n"),
                Arguments.of(AnnotationData.BUILDER, "@Builder\n"),
                Arguments.of(AnnotationData.BUILDER_TO_BUILDER, "@Builder(toBuilder = true)\n"),
                Arguments.of(AnnotationData.SUPER_BUILDER, "@SuperBuilder\n"),
                Arguments.of(AnnotationData.SUPER_BUILDER_TO_BUILDER, "@SuperBuilder(toBuilder = true)\n"),
                Arguments.of(AnnotationData.TO_STRING, "@ToString\n"),
                Arguments.of(AnnotationData.TO_STRING_CALL_SUPER, "@ToString(callSuper = true)\n"),
                Arguments.of(AnnotationData.EQUALS_AND_HASH_CODE, "@EqualsAndHashCode\n")
        );
    }
}
