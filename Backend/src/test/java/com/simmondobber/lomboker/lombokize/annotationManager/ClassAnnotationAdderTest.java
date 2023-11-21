package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder.ClassAnnotationAdder;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ClassAnnotationAdderTest {

    @ParameterizedTest
    @MethodSource("add_annotations_to_classes_provider")
    public void add_annotations_to_classes_test(String codeToExtend, AnnotationsConfig annotationsConfig, String codeAfterExtension) {
        //Given
        ClassAnnotationAdder classAnnotationAdder = new ClassAnnotationAdder();

        //When
        Ast ast = new Ast(codeToExtend);
        classAnnotationAdder.addAnnotationsToClasses(ast, annotationsConfig);

        //Then
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
                        """, new AnnotationsConfig(true, true, true, true, false, true, true, true, true, false), """
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
                        """, new AnnotationsConfig(true, true, false, false, false, false, false, false, false, false), """
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
                        package com.simmondobber.lomboker.lombokize.importManager;
                                                
                        import lombok.*;
                                                
                        public enum Point {
                            X, Y
                        }
                        """, new AnnotationsConfig(true, true, true, true, true, false, false, true, false, false), """
                        package com.simmondobber.lomboker.lombokize.importManager;
                                                
                        import lombok.*;
                                                
                        @Getter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @ToString
                        public enum Point {
                            X, Y
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.importManager;
                                                
                        public interface Point {
                                                
                            int getX();
                            int getY();
                        }
                        """, new AnnotationsConfig(true, true, true, true, true, false, false, true, false, false), """
                        package com.simmondobber.lomboker.lombokize.importManager;
                                                
                        public interface Point {
                                                
                            int getX();
                            int getY();
                        }
                        """
                )
        );
    }
}
