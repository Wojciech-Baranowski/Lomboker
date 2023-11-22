package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.common.AnnotationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class ImportManagerTest {

    @ParameterizedTest
    @MethodSource("add_imports_provider")
    public void add_imports_test(String codeToExtend, List<AnnotationData> annotations, String codeAfterExtension) {
        //Given
        ImportManager importManager = new ImportManager();

        //When
        Ast ast = new Ast(codeToExtend);
        importManager.addAndReorganizeLombokImports(ast, annotations);

        //Then
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
                        """, List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.TO_STRING, AnnotationData.SUPER_BUILDER), """
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
                        """, List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.TO_STRING, AnnotationData.SUPER_BUILDER), """
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
                        """, List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.NO_ARGS_CONSTRUCTOR, AnnotationData.ALL_ARGS_CONSTRUCTOR), """
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
                ), Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @ToString
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """, List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.NO_ARGS_CONSTRUCTOR, AnnotationData.ALL_ARGS_CONSTRUCTOR, AnnotationData.TO_STRING), """
                        package com.simmondobber.lomboker.lombokize.annotationAdder;
                                                
                        import lombok.*;
                                                
                        @Getter
                        @Setter
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @ToString
                        public class Point {
                                                
                            private int x;
                            private int y;
                        }
                        """
                )
        );
    }
}
