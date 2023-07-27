package com.simmondobber.lomboker.lombokize.annotationAdder;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.File;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.components.complexAstComponents.PreambleComponent;
import com.simmondobber.ast.parser.complexComponentParser.PreambleParser;
import com.simmondobber.lomboker.common.Trimmer;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class AnnotationAdderTest {

    @ParameterizedTest
    @MethodSource("add_annotations_to_classes_provider")
    public void add_annotations_to_classes_test(String codeToExtend, AnnotationsConfig annotationsConfig, String preambleAnnotationsCode, String codeAfterExtension) {
        //Given
        AnnotationAdder annotationAdder = new AnnotationAdder();
        List<PreambleComponent> expectedPreambleComponents = new PreambleParser(preambleAnnotationsCode).parse().getPreambleComponents().stream()
                .sorted(Comparator.comparing(PreambleComponent::getSyntax))
                .toList();

        //When
        Ast ast = new Ast(codeToExtend);
        annotationAdder.addAnnotations(ast, annotationsConfig);

        //Then
        List<Class> classes = ((File) (ast.getAstRoot())).getClasses();
        for (Class clazz : classes) {
            List<PreambleComponent> actualPreambleComponents = clazz.getPreamble().getPreambleComponents().stream()
                    .sorted(Comparator.comparing(PreambleComponent::getSyntax))
                    .toList();

            Assertions.assertEquals(expectedPreambleComponents.size(), actualPreambleComponents.size());
            for (int i = 0; i < expectedPreambleComponents.size(); i++) {
                Assertions.assertEquals(Trimmer.compressSeparators(expectedPreambleComponents.get(i).getSyntax()), Trimmer.compressSeparators(actualPreambleComponents.get(i).getSyntax()));
            }
        }
        Assertions.assertEquals(codeAfterExtension, ast.getCode());
    }

    @ParameterizedTest
    @MethodSource("add_annotations_to_methods_provider")
    public void add_annotations_to_methods_test(String codeToExtend, AnnotationsConfig annotationsConfig, String preambleAnnotationsCode, String codeAfterExtension) {
        //Given
        AnnotationAdder annotationAdder = new AnnotationAdder();
        List<PreambleComponent> expectedPreambleComponents = new PreambleParser(preambleAnnotationsCode).parse().getPreambleComponents().stream()
                .sorted(Comparator.comparing(PreambleComponent::getSyntax))
                .toList();

        //When
        Ast ast = new Ast(codeToExtend);
        annotationAdder.addAnnotations(ast, annotationsConfig);
        List<Method> methods = ((File) (ast.getAstRoot())).getClasses().stream()
                .map(clazz -> clazz.getClassBody().getClassContent().getMethods())
                .flatMap(Collection::stream)
                .toList();
        for (Method method : methods) {
            List<PreambleComponent> actualPreambleComponents = method.getPreamble().getPreambleComponents().stream()
                    .sorted(Comparator.comparing(PreambleComponent::getSyntax))
                    .toList();
            //Then
            Assertions.assertEquals(expectedPreambleComponents.size(), actualPreambleComponents.size());
            for (int i = 0; i < expectedPreambleComponents.size(); i++) {
                Assertions.assertEquals(Trimmer.compressSeparators(expectedPreambleComponents.get(i).getSyntax()), Trimmer.compressSeparators(actualPreambleComponents.get(i).getSyntax()));
            }
        }
        Assertions.assertEquals(codeAfterExtension, ast.getCode());
    }

    private static Stream<Arguments> add_annotations_to_classes_provider() {
        return Stream.of(
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                            
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                            
                        public class Point {
                                            
                            private int x;
                            private int y;
                        }
                        """, new AnnotationsConfig(true, true, true, true, false, true, true, true, true), """
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @SuperBuilder(toBuilder = true)
                        @ToString(callSuper = true)
                        public
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @SuperBuilder(toBuilder = true)
                        @ToString(callSuper = true)
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        public class Point {
                                                
                            public class CoordinateX {
                            
                                private int x;
                            }
                                                
                            public class CoordinateY {
                                          
                                private int y;
                            }
                            
                            private CoordinateX x;
                            private CoordinateY y;
                        }
                        """, new AnnotationsConfig(true, true, false, false, false, false, false, false, false), """
                        @Getter
                        @Setter
                        public
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        @Getter
                        @Setter
                        public class Point {
                                                
                            @Getter
                            @Setter
                            public class CoordinateX {
                                                
                                private int x;
                            }
                                                
                            @Getter
                            @Setter
                            public class CoordinateY {
                                                
                                private int y;
                            }
                                                
                            private CoordinateX x;
                            private CoordinateY y;
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                                                
                        @Getter
                        @Setter
                        public class Point {
                                                
                            @Getter
                            private int x;
                            @Getter
                            private int y;
                        }
                        """, new AnnotationsConfig(true, true, true, true, false, false, false, false, false), """
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        public
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                                                
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

    private static Stream<Arguments> add_annotations_to_methods_provider() {
        return Stream.of(
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                            
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                            
                        public class Point {
                                            
                            private int x;
                            private int y;
                        }
                        """, new AnnotationsConfig(false, false, true, true, false, true, true, true, true), """
                        @Getter
                        @Setter
                        public
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @SuperBuilder(toBuilder = true)
                        @ToString(callSuper = true)
                        public class Point {
                                                
                            @Getter
                            @Setter
                            private int x;
                            @Getter
                            @Setter
                            private int y;
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        public class Point {
                                                
                            public class CoordinateX {
                            
                                private int x;
                            }
                                                
                            public class CoordinateY {
                                          
                                private int y;
                            }
                            
                            private CoordinateX x;
                            private CoordinateY y;
                        }
                        """, new AnnotationsConfig(false, false, false, false, false, false, false, false, false), """
                        @Getter
                        @Setter
                        public
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        public class Point {
                                                
                            public class CoordinateX {
                                                
                                @Getter
                                @Setter
                                private int x;
                            }
                                                
                            public class CoordinateY {
                                                
                                @Getter
                                @Setter
                                private int y;
                            }
                                                
                            @Getter
                            @Setter
                            private CoordinateX x;
                            @Getter
                            @Setter
                            private CoordinateY y;
                        }
                        """
                )
        );
    }
}
