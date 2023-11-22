package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.lomboker.common.AnnotationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ImportFactoryTest {

    private final ImportFactory importFactory = new ImportFactory();

    @Test
    public void create_all_lombok_import_test() {
        //Given
        String expectedImportSyntax = "import lombok.*;\n";

        //When
        Import _import = importFactory.createLombokAllImport("\n");
        String actualImportSyntax = _import.getFullSyntax();

        //Then
        Assertions.assertEquals(expectedImportSyntax, actualImportSyntax);
    }

    @ParameterizedTest
    @MethodSource("create_annotation_provider")
    public void create_imports_based_on_annotation_test(AnnotationData annotationData, String expectedImportSyntax) {
        //When
        Import _import = importFactory.createImportForAnnotation(annotationData, "\n");
        String actualImportSyntax = _import.getFullSyntax();

        //Then
        Assertions.assertEquals(expectedImportSyntax, actualImportSyntax);
    }

    private static Stream<Arguments> create_annotation_provider() {
        return Stream.of(
                Arguments.of(AnnotationData.GETTER, "import lombok.Getter;\n"),
                Arguments.of(AnnotationData.SETTER, "import lombok.Setter;\n"),
                Arguments.of(AnnotationData.NO_ARGS_CONSTRUCTOR, "import lombok.NoArgsConstructor;\n"),
                Arguments.of(AnnotationData.ALL_ARGS_CONSTRUCTOR, "import lombok.AllArgsConstructor;\n"),
                Arguments.of(AnnotationData.BUILDER, "import lombok.Builder;\n"),
                Arguments.of(AnnotationData.SUPER_BUILDER, "import lombok.experimental.SuperBuilder;\n"),
                Arguments.of(AnnotationData.TO_STRING, "import lombok.ToString;\n"),
                Arguments.of(AnnotationData.EQUALS_AND_HASH_CODE, "import lombok.EqualsAndHashCode;\n")
        );
    }
}
