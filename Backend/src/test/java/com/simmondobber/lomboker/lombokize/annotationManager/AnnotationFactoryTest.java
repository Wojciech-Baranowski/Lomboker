package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnnotationFactoryTest {

    private final AnnotationFactory annotationFactory = new AnnotationFactory();

    @Test
    public void create_getter_annotation_test() {
        //Given
        String expectedGetterSyntax = "@Getter\n";

        //When
        String actualGetterSyntax = this.annotationFactory.createGetterAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedGetterSyntax, actualGetterSyntax);
    }

    @Test
    public void create_setter_annotation_test() {
        //Given
        String expectedSetterSyntax = "@Setter\n";

        //When
        String actualSetterSyntax = this.annotationFactory.createSetterAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedSetterSyntax, actualSetterSyntax);
    }

    @Test
    public void create_no_args_constructor_annotation_test() {
        //Given
        String expectedNoArgsConstructorSyntax = "@NoArgsConstructor\n";

        //When
        String actualNoArgsConstructorSyntax = this.annotationFactory.createNoArgsConstructorAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedNoArgsConstructorSyntax, actualNoArgsConstructorSyntax);
    }

    @Test
    public void create_all_args_constructor_annotation_test() {
        //Given
        String expectedAllArgsConstructorSyntax = "@AllArgsConstructor\n";

        //When
        String actualAllArgsConstructorSyntax = this.annotationFactory.createAllArgsConstructorAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedAllArgsConstructorSyntax, actualAllArgsConstructorSyntax);
    }

    @Test
    public void create_builder_annotation_test() {
        //Given
        String expectedBuilderSyntax = "@Builder\n";

        //When
        String actualBuilderSyntax = this.annotationFactory.createBuilderAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedBuilderSyntax, actualBuilderSyntax);
    }

    @Test
    public void create_super_builder_annotation_test() {
        //Given
        String expectedSuperBuilderSyntax = "@SuperBuilder\n";

        //When
        String actualSuperBuilderSyntax = this.annotationFactory.createSuperBuilderAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedSuperBuilderSyntax, actualSuperBuilderSyntax);
    }

    @Test
    public void create_builder_with_to_builder_annotation_test() {
        //Given
        String expectedBuilderWithToBuilderSyntax = "@Builder(toBuilder = true)\n";

        //When
        String actualBuilderWithToBuilderSyntax = this.annotationFactory.createBuilderWithToBuilderAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedBuilderWithToBuilderSyntax, actualBuilderWithToBuilderSyntax);
    }

    @Test
    public void create_super_builder_with_to_builder_annotation_test() {
        //Given
        String expectedSuperBuilderWithToBuilderSyntax = "@SuperBuilder(toBuilder = true)\n";

        //When
        String actualSuperBuilderWithToBuilderSyntax = this.annotationFactory.createSuperBuilderWithToBuilderAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedSuperBuilderWithToBuilderSyntax, actualSuperBuilderWithToBuilderSyntax);
    }

    @Test
    public void create_to_string_annotation_test() {
        //Given
        String expectedToStringSyntax = "@ToString\n";

        //When
        String actualToStringSyntax = this.annotationFactory.createToStringAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedToStringSyntax, actualToStringSyntax);
    }

    @Test
    public void create_to_string_with_call_super_annotation_test() {
        //Given
        String expectedToStringWithCallSuperSyntax = "@ToString(callSuper = true)\n";

        //When
        String actualToStringWithCallSuperSyntax = this.annotationFactory.createToStringWithCallSuperAnnotation("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedToStringWithCallSuperSyntax, actualToStringWithCallSuperSyntax);
    }

    @Test
    public void create_field_annotations_based_on_config_test() {
        //Given
        AnnotationsConfig annotationsConfig = new AnnotationsConfig(false, false, false, true, true, false, true, false, true);
        List<Annotation> expectedListOfAnnotations = List.of(
                this.annotationFactory.createGetterAnnotation(""),
                this.annotationFactory.createSetterAnnotation("")
        );

        //When
        List<Annotation> actualListOfAnnotations = this.annotationFactory.createFieldAnnotationsBasedOnConfig(annotationsConfig, "");

        //Then
        Assertions.assertEquals(expectedListOfAnnotations.size(), actualListOfAnnotations.size());
        for (int i = 0; i < expectedListOfAnnotations.size(); i++) {
            Assertions.assertEquals(expectedListOfAnnotations.get(i).getSyntax(), actualListOfAnnotations.get(i).getSyntax());
        }
    }

    @Test
    public void create_class_annotations_based_on_config_test() {
        //Given
        AnnotationsConfig annotationsConfig = new AnnotationsConfig(true, true, false, true, true, false, true, false, true);
        List<Annotation> expectedListOfAnnotations = List.of(
                this.annotationFactory.createGetterAnnotation(""),
                this.annotationFactory.createSetterAnnotation(""),
                this.annotationFactory.createAllArgsConstructorAnnotation(""),
                this.annotationFactory.createBuilderWithToBuilderAnnotation("")
        );

        //When
        List<Annotation> actualListOfAnnotations = this.annotationFactory.createClassAnnotationsBasedOnConfig(annotationsConfig, "");

        //Then
        Assertions.assertEquals(expectedListOfAnnotations.size(), actualListOfAnnotations.size());
        for (int i = 0; i < expectedListOfAnnotations.size(); i++) {
            Assertions.assertEquals(expectedListOfAnnotations.get(i).getSyntax(), actualListOfAnnotations.get(i).getSyntax());
        }
    }

    @Test
    public void create_enum_annotations_based_on_config_test() {
        //Given
        AnnotationsConfig annotationsConfig = new AnnotationsConfig(true, true, false, true, true, false, true, false, true);
        List<Annotation> expectedListOfAnnotations = List.of(
                this.annotationFactory.createGetterAnnotation(""),
                this.annotationFactory.createAllArgsConstructorAnnotation("")
        );

        //When
        List<Annotation> actualListOfAnnotations = this.annotationFactory.createEnumAnnotationsBasedOnConfig(annotationsConfig, "");

        //Then
        Assertions.assertEquals(expectedListOfAnnotations.size(), actualListOfAnnotations.size());
        for (int i = 0; i < expectedListOfAnnotations.size(); i++) {
            Assertions.assertEquals(expectedListOfAnnotations.get(i).getSyntax(), actualListOfAnnotations.get(i).getSyntax());
        }
    }
}
