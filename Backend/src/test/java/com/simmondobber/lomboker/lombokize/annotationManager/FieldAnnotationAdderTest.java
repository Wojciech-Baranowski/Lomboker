package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder.FieldAnnotationAdder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class FieldAnnotationAdderTest {

    @ParameterizedTest
    @MethodSource("add_annotations_to_fields_provider")
    public void add_annotations_to_methods_test(String codeToExtend, String codeAfterExtension) {
        //Given
        FieldAnnotationAdder fieldAnnotationAdder = new FieldAnnotationAdder();

        //When
        Ast ast = new Ast(codeToExtend);
        fieldAnnotationAdder.addAnnotationsToFields(ast, List.of());

        //Then
        Assertions.assertEquals(codeAfterExtension, ast.getCode());
    }

    private static Stream<Arguments> add_annotations_to_fields_provider() {
        return Stream.of(
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                            
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                            
                        public class Point {
                                            
                            private int x;
                            private int y;
                        }
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
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
