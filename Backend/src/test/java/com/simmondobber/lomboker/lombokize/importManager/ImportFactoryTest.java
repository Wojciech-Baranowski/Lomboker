package com.simmondobber.lomboker.lombokize.importManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImportFactoryTest {

    private final ImportFactory importFactory = new ImportFactory();

    @Test
    public void create_lombok_all_import_test() {
        //Given
        String expectedLombokAllSyntax = "import lombok.*;\n";

        //When
        String actualLombokAllSyntax = this.importFactory.createLombokAllImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedLombokAllSyntax, actualLombokAllSyntax);
    }

    @Test
    public void create_lombok_experimental_all_import_test() {
        //Given
        String expectedLombokExperimentalAllSyntax = "import lombok.experimental.*;\n";

        //When
        String actualLombokExperimentalAllSyntax = this.importFactory.createLombokExperimentalAllImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedLombokExperimentalAllSyntax, actualLombokExperimentalAllSyntax);
    }

    @Test
    public void create_getter_import_test() {
        //Given
        String expectedGetterSyntax = "import lombok.Getter;\n";

        //When
        String actualGetterSyntax = this.importFactory.createGetterImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedGetterSyntax, actualGetterSyntax);
    }

    @Test
    public void create_setter_import_test() {
        //Given
        String expectedSetterSyntax = "import lombok.Setter;\n";

        //When
        String actualSetterSyntax = this.importFactory.createSetterImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedSetterSyntax, actualSetterSyntax);
    }

    @Test
    public void create_no_args_constructor_import_test() {
        //Given
        String expectedNoArgsConstructorSyntax = "import lombok.NoArgsConstructor;\n";

        //When
        String actualNoArgsConstructorSyntax = this.importFactory.createNoArgsConstructorImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedNoArgsConstructorSyntax, actualNoArgsConstructorSyntax);
    }

    @Test
    public void create_all_args_constructor_import_test() {
        //Given
        String expectedAllArgsConstructorSyntax = "import lombok.AllArgsConstructor;\n";

        //When
        String actualAllArgsConstructorSyntax = this.importFactory.createAllArgsConstructorImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedAllArgsConstructorSyntax, actualAllArgsConstructorSyntax);
    }

    @Test
    public void create_builder_import_test() {
        //Given
        String expectedBuilderSyntax = "import lombok.Builder;\n";

        //When
        String actualBuilderSyntax = this.importFactory.createBuilderImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedBuilderSyntax, actualBuilderSyntax);
    }

    @Test
    public void create_super_builder_import_test() {
        //Given
        String expectedSuperBuilderSyntax = "import lombok.experimental.SuperBuilder;\n";

        //When
        String actualSuperBuilderSyntax = this.importFactory.createSuperBuilderImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedSuperBuilderSyntax, actualSuperBuilderSyntax);
    }

    @Test
    public void create_to_string_builder_import_test() {
        //Given
        String expectedToStringSyntax = "import lombok.ToString;\n";

        //When
        String actualToStringSyntax = this.importFactory.createToStringImport("\n").getSyntax();

        //Then
        Assertions.assertEquals(expectedToStringSyntax, actualToStringSyntax);
    }
}
