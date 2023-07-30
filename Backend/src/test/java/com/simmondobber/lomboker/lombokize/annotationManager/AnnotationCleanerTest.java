package com.simmondobber.lomboker.lombokize.annotationManager;

import com.simmondobber.ast.Ast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AnnotationCleanerTest {

    @ParameterizedTest
    @MethodSource("remove_annotations_from_classes_provider")
    public void remove_annotations_from_classes_test(String codeToClean, String codeAfterCleaning) {
        //Given
        AnnotationCleaner annotationCleaner = new AnnotationCleaner();

        //When
        Ast ast = new Ast(codeToClean);
        annotationCleaner.removeAnnotations(ast);

        //Then
        Assertions.assertEquals(codeAfterCleaning, ast.getCode());
    }

    private static Stream<Arguments> remove_annotations_from_classes_provider() {
        return Stream.of(
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @SuperBuilder
                        @ToString
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;
                                                
                        import lombok.*;
                        import lombok.experimental.SuperBuilder;
                                                
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                                                
                        public class Point {
                                                
                            @Getter
                            @Setter
                            private int x;
                            @Getter
                            @Setter
                            private int y;
                        }
                        """, """
                        package com.simmondobber.lomboker.lombokize.annotationManager.annotationAdder;
                                                
                        import lombok.Getter;
                        import lombok.Setter;
                                                
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                         """
                )
        );
    }
}
