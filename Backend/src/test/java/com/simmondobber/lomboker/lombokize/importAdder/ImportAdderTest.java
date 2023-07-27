package com.simmondobber.lomboker.lombokize.importAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.File;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.parser.complexComponentParser.FileParser;
import com.simmondobber.lomboker.common.Trimmer;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ImportAdderTest {

    @ParameterizedTest
    @MethodSource("add_imports_provider")
    public void add_imports_test(String codeToExtend, AnnotationsConfig annotationsConfig, String importsCode, String codeAfterExtension) {
        //Given
        ImportAdder importAdder = new ImportAdder();
        List<Import> expectedImports = new FileParser("package dummy;\n" + importsCode + "\n class Dummy dummy {}").parse().getImports().stream()
                .sorted(Comparator.comparing(Import::getSyntax))
                .toList();

        //When
        Ast ast = new Ast(codeToExtend);
        importAdder.addImports(ast, annotationsConfig);

        //Then
        List<Import> actualImports = ((File) ast.getAstRoot()).getImports().stream()
                .sorted(Comparator.comparing(Import::getSyntax))
                .toList();
        Assertions.assertEquals(expectedImports.size(), actualImports.size());
        for (int i = 0; i < expectedImports.size(); i++) {
            Assertions.assertEquals(Trimmer.compressSeparators(expectedImports.get(i).getSyntax()), Trimmer.compressSeparators((actualImports.get(i).getSyntax())));
        }
        Assertions.assertEquals(codeAfterExtension, ast.getCode());
    }

    private static Stream<Arguments> add_imports_provider() {
        return Stream.of(
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import java.util.List;
                                            
                        public class Point {
                                            
                            private int x;
                            private int y;
                        }
                        """, new AnnotationsConfig(true, true, false, false, false, true, false, true, false), """
                        import java.util.List;
                        import lombok.Getter;
                        import lombok.Setter;
                        import lombok.ToString;
                        import lombok.experimental.SuperBuilder;
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import java.util.List;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                        import lombok.ToString;
                        import lombok.experimental.SuperBuilder;
                                                
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                                                
                        @Getter
                        @Setter
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """, new AnnotationsConfig(true, true, false, false, false, true, false, true, false), """
                        import lombok.Getter;
                        import lombok.Setter;
                        import lombok.ToString;
                        import lombok.experimental.SuperBuilder;
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                        import lombok.ToString;
                        import lombok.experimental.SuperBuilder;
                                                
                        @Getter
                        @Setter
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                        import lombok.ToString;
                        import lombok.experimental.SuperBuilder;
                                                
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """, new AnnotationsConfig(true, true, true, true, false, false, false, false, false), """
                        import lombok.AllArgsConstructor;
                        import lombok.Getter;
                        import lombok.NoArgsConstructor;
                        import lombok.Setter;
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                        import lombok.NoArgsConstructor;
                        import lombok.AllArgsConstructor;
                                                
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """
                )
        );
    }
}
