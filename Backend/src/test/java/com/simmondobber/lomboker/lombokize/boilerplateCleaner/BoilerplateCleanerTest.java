package com.simmondobber.lomboker.lombokize.boilerplateCleaner;

import com.simmondobber.ast.Ast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BoilerplateCleanerTest {

    @ParameterizedTest
    @MethodSource("remove_redundant_methods_provider")
    public void remove_redundant_methods_test(String codeToClean, boolean actOnInnerClasses, String correctlyCleanedCode) {
        //Given
        BoilerplateCleaner boilerplateCleaner = new BoilerplateCleaner();

        //When
        Ast ast = new Ast(codeToClean);
        boilerplateCleaner.removeDefaultMethodsFromAst(ast, actOnInnerClasses, false);
        String cleanedCode = ast.getCode();

        //Then
        Assertions.assertEquals(correctlyCleanedCode, cleanedCode);
    }

    private static Stream<Arguments> remove_redundant_methods_provider() {
        return Stream.of(
                Arguments.of("""
                        package com.simmondobber.Point;
                                                        
                        import java.util.ArrayList;
                                                        
                        public class Point {
                                                        
                            private List<Point> points;
                            private int x;
                            private int y;
                                                        
                            public Test(int x, int y) {
                                points = new ArrayList<>();
                                this.x = x;
                                this.y = y;
                            }
                                                        
                            public List<Point> getPoints() {
                                return points;
                            }
                                                        
                            public void setPoints(List<Point> points) {
                                this.points = points;
                            }
                                                        
                            public int getX() {
                                return x;
                            }
                                                        
                            public void setX(int x) {
                                this.x = x;
                            }
                                                        
                            public int getY() {
                                return y;
                            }
                                                        
                            public void setY(int y) {
                                this.y = y;
                            }
                        }
                        """, false, """
                        package com.simmondobber.Point;
                                                        
                        import java.util.ArrayList;
                                                        
                        public class Point {
                                                        
                            private List<Point> points;
                            private int x;
                            private int y;
                                                        
                            public Test(int x, int y) {
                                points = new ArrayList<>();
                                this.x = x;
                                this.y = y;
                            }
                        }
                        """
                ),
                Arguments.of("""
                        package com.simmondobber.lomboker.common;
                                                
                        import java.util.List;
                                                
                        public class Test {
                                                
                            private int point;
                            private int isPoint;
                            private int isPrivate;
                            private int isAbstract;
                            private boolean pointBoolean;
                            private boolean isPointBoolean;
                            private boolean isPublic;
                            private boolean isElse;
                            private List<Integer> listPoints;
                            private int[] arrayPoints;
                            private List<Integer>[] points;
                                                
                            public int getPoint() {
                                return point;
                            }
                                                
                            public void setPoint(int point) {
                                this.point = point;
                            }
                                                
                            public int getIsPoint() {
                                return isPoint;
                            }
                                                
                            public void setIsPoint(int isPoint) {
                                this.isPoint = isPoint;
                            }
                                                
                            public int getIsPrivate() {
                                return isPrivate;
                            }
                                                
                            public void setIsPrivate(int isPrivate) {
                                this.isPrivate = isPrivate;
                            }
                                                
                            public int getIsAbstract() {
                                return isAbstract;
                            }
                                                
                            public void setIsAbstract(int isAbstract) {
                                this.isAbstract = isAbstract;
                            }
                                                
                            public boolean isPointBoolean() {
                                return pointBoolean;
                            }
                                                
                            public void setPointBoolean(boolean pointBoolean) {
                                this.pointBoolean = pointBoolean;
                            }
                                                
                            public boolean isPublic() {
                                return isPublic;
                            }
                                                
                            public void setPublic(boolean aPublic) {
                                isPublic = aPublic;
                            }
                                                
                            public boolean isElse() {
                                return isElse;
                            }
                                                
                            public void setElse(boolean anElse) {
                                isElse = anElse;
                            }
                                                
                            public List<Integer> getListPoints() {
                                return listPoints;
                            }
                                                
                            public void setListPoints(List<Integer> listPoints) {
                                this.listPoints = listPoints;
                            }
                                                
                            public int[] getArrayPoints() {
                                return arrayPoints;
                            }
                                                
                            public void setArrayPoints(int[] arrayPoints) {
                                this.arrayPoints = arrayPoints;
                            }
                                                
                            public List<Integer>[] getPoints() {
                                return points;
                            }
                                                
                            public void setPoints(List<Integer>[] points) {
                                this.points = points;
                            }
                        }
                        """, false, """
                        package com.simmondobber.lomboker.common;
                                                
                        import java.util.List;
                                                
                        public class Test {
                                                
                            private int point;
                            private int isPoint;
                            private int isPrivate;
                            private int isAbstract;
                            private boolean pointBoolean;
                            private boolean isPointBoolean;
                            private boolean isPublic;
                            private boolean isElse;
                            private List<Integer> listPoints;
                            private int[] arrayPoints;
                            private List<Integer>[] points;
                        }
                        """
                ),
                Arguments.of("""
                        package com.simmondobber.lomboker.common;
                                                
                        import java.util.ArrayList;
                        import java.util.Arrays;
                        import java.util.List;
                        import java.util.Objects;
                                                
                        public class Test {
                                                
                            private int point;
                            private int isPoint;
                            private int isPrivate;
                            private int isAbstract;
                            private boolean pointBoolean;
                            private boolean isPointBoolean;
                            private boolean isPublic;
                            private boolean isElse;
                            private List<Integer> listPoints;
                            private int[] arrayPoints;
                            private List<Integer>[] points;
                                                
                            @Override
                            public boolean equals(Object o) {
                                if (this == o) return true;
                                if (!(o instanceof Test test)) return false;
                                return getPoint() == test.getPoint() && getIsPoint() == test.getIsPoint() && getIsPrivate() == test.getIsPrivate() && getIsAbstract() == test.getIsAbstract() && isPointBoolean() == test.isPointBoolean() && isPointBoolean() == test.isPointBoolean() && isPublic() == test.isPublic() && isElse() == test.isElse() && Objects.equals(getListPoints(), test.getListPoints()) && Arrays.equals(getArrayPoints(), test.getArrayPoints()) && Arrays.equals(getPoints(), test.getPoints());
                            }
                                                
                            @Override
                            public int hashCode() {
                                int result = Objects.hash(getPoint(), getIsPoint(), getIsPrivate(), getIsAbstract(), isPointBoolean(), isPointBoolean(), isPublic(), isElse(), getListPoints());
                                result = 31 * result + Arrays.hashCode(getArrayPoints());
                                result = 31 * result + Arrays.hashCode(getPoints());
                                return result;
                            }
                                                
                            public List<Integer> getListPoints() {
                                if(this.listPoints == null) {
                                    this.listPoints = new ArrayList<>();
                                }
                                return this.listPoints;
                            }
                                                
                            public int[] getArrayPoints() {
                                if(this.arrayPoints == null) {
                                    this.arrayPoints = new int[2137];
                                }
                                return this.arrayPoints;
                            }
                                                
                            public List<Integer>[] getPoints() {
                                int x = 5;
                                return this.points;
                            }
                                                
                            public int getPoint() {
                                return point;
                            }
                                                
                            public void setPoint(int point) {
                                this.point = point;
                            }
                                                
                            public int getIsPoint() {
                                return isPoint;
                            }
                                                
                            public void setIsPoint(int isPoint) {
                                this.isPoint = isPoint;
                            }
                                                
                            public int getIsPrivate() {
                                return isPrivate;
                            }
                                                
                            public void setIsPrivate(int isPrivate) {
                                this.isPrivate = isPrivate;
                            }
                                                
                            public int getIsAbstract() {
                                return isAbstract;
                            }
                                                
                            public void setIsAbstract(int isAbstract) {
                                this.isAbstract = isAbstract;
                            }
                                                
                            public boolean isPointBoolean() {
                                return pointBoolean;
                            }
                                                
                            public void setPointBoolean(boolean pointBoolean) {
                                this.pointBoolean = pointBoolean;
                            }
                                                
                            public boolean isPublic() {
                                return isPublic;
                            }
                                                
                            public void setPublic(boolean aPublic) {
                                isPublic = aPublic;
                            }
                                                
                            public boolean isElse() {
                                return isElse;
                            }
                                                
                            public void setElse(boolean anElse) {
                                isElse = anElse;
                            }
                        }
                        """, false, """
                        package com.simmondobber.lomboker.common;
                                                
                        import java.util.ArrayList;
                        import java.util.Arrays;
                        import java.util.List;
                        import java.util.Objects;
                                                
                        public class Test {
                                                
                            private int point;
                            private int isPoint;
                            private int isPrivate;
                            private int isAbstract;
                            private boolean pointBoolean;
                            private boolean isPointBoolean;
                            private boolean isPublic;
                            private boolean isElse;
                            private List<Integer> listPoints;
                            private int[] arrayPoints;
                            private List<Integer>[] points;
                                                
                            @Override
                            public boolean equals(Object o) {
                                if (this == o) return true;
                                if (!(o instanceof Test test)) return false;
                                return getPoint() == test.getPoint() && getIsPoint() == test.getIsPoint() && getIsPrivate() == test.getIsPrivate() && getIsAbstract() == test.getIsAbstract() && isPointBoolean() == test.isPointBoolean() && isPointBoolean() == test.isPointBoolean() && isPublic() == test.isPublic() && isElse() == test.isElse() && Objects.equals(getListPoints(), test.getListPoints()) && Arrays.equals(getArrayPoints(), test.getArrayPoints()) && Arrays.equals(getPoints(), test.getPoints());
                            }
                                                
                            @Override
                            public int hashCode() {
                                int result = Objects.hash(getPoint(), getIsPoint(), getIsPrivate(), getIsAbstract(), isPointBoolean(), isPointBoolean(), isPublic(), isElse(), getListPoints());
                                result = 31 * result + Arrays.hashCode(getArrayPoints());
                                result = 31 * result + Arrays.hashCode(getPoints());
                                return result;
                            }
                                                
                            public List<Integer> getListPoints() {
                                if(this.listPoints == null) {
                                    this.listPoints = new ArrayList<>();
                                }
                                return this.listPoints;
                            }
                                                
                            public int[] getArrayPoints() {
                                if(this.arrayPoints == null) {
                                    this.arrayPoints = new int[2137];
                                }
                                return this.arrayPoints;
                            }
                                                
                            public List<Integer>[] getPoints() {
                                int x = 5;
                                return this.points;
                            }
                        }
                        """
                ),
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.transportObjects;
                                                
                        import com.simmondobber.lomboker.lombokize.enums.IndentType;
                                                
                        public class Class1 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public String getCodeToLombokize() {
                                return codeToLombokize;
                            }
                                                
                            public void setCodeToLombokize(String codeToLombokize) {
                                this.codeToLombokize = codeToLombokize;
                            }
                                                
                            public IndentType getIndentType() {
                                return indentType;
                            }
                                                
                            public void setIndentType(IndentType indentType) {
                                this.indentType = indentType;
                            }
                                                
                            public AnnotationsConfig getAnnotationsConfig() {
                                return annotationsConfig;
                            }
                                                
                            public void setAnnotationsConfig(AnnotationsConfig annotationsConfig) {
                                this.annotationsConfig = annotationsConfig;
                            }
                                                
                            public class Class2 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public class Class3 {
                                                
                                    private String codeToLombokize2;
                                    private IndentType indentType2;
                                                
                                    public String getCodeToLombokize2() {
                                        return codeToLombokize2;
                                    }
                                }
                                                
                                public String getCodeToLombokize2() {
                                    return codeToLombokize2;
                                }
                                                
                                public void setCodeToLombokize2(String codeToLombokize2) {
                                    this.codeToLombokize2 = codeToLombokize2;
                                }
                                                
                                public IndentType getIndentType2() {
                                    return indentType2;
                                }
                                                
                                public void setIndentType2(IndentType indentType2) {
                                    this.indentType2 = indentType2;
                                }
                            }
                        }
                                                
                        public class Class4 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public String getCodeToLombokize() {
                                return codeToLombokize;
                            }
                                                
                            public void setCodeToLombokize(String codeToLombokize) {
                                this.codeToLombokize = codeToLombokize;
                            }
                                                
                            public IndentType getIndentType() {
                                return indentType;
                            }
                                                
                            public void setIndentType(IndentType indentType) {
                                this.indentType = indentType;
                            }
                                                
                            public AnnotationsConfig getAnnotationsConfig() {
                                return annotationsConfig;
                            }
                                                
                            public void setAnnotationsConfig(AnnotationsConfig annotationsConfig) {
                                this.annotationsConfig = annotationsConfig;
                            }
                                                
                            public class Class5 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public String getCodeToLombokize2() {
                                    return codeToLombokize2;
                                }
                                                
                                public void setCodeToLombokize2(String codeToLombokize2) {
                                    this.codeToLombokize2 = codeToLombokize2;
                                }
                                                
                                public IndentType getIndentType2() {
                                    return indentType2;
                                }
                                                
                                public void setIndentType2(IndentType indentType2) {
                                    this.indentType2 = indentType2;
                                }
                            }
                        }
                        """, true, """
                        package com.simmondobber.lomboker.lombokize.transportObjects;
                                                
                        import com.simmondobber.lomboker.lombokize.enums.IndentType;
                                                
                        public class Class1 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public class Class2 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public class Class3 {
                                                
                                    private String codeToLombokize2;
                                    private IndentType indentType2;
                                }
                            }
                        }
                                                
                        public class Class4 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public class Class5 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                            }
                        }
                        """
                ),
                Arguments.of("""
                        package com.simmondobber.lomboker.lombokize.transportObjects;
                                                
                        import com.simmondobber.lomboker.lombokize.enums.IndentType;
                                                
                        public class Class1 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public String getCodeToLombokize() {
                                return codeToLombokize;
                            }
                                                
                            public void setCodeToLombokize(String codeToLombokize) {
                                this.codeToLombokize = codeToLombokize;
                            }
                                                
                            public IndentType getIndentType() {
                                return indentType;
                            }
                                                
                            public void setIndentType(IndentType indentType) {
                                this.indentType = indentType;
                            }
                                                
                            public AnnotationsConfig getAnnotationsConfig() {
                                return annotationsConfig;
                            }
                                                
                            public void setAnnotationsConfig(AnnotationsConfig annotationsConfig) {
                                this.annotationsConfig = annotationsConfig;
                            }
                                                
                            public class Class2 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public class Class3 {
                                                
                                    private String codeToLombokize2;
                                    private IndentType indentType2;
                                                
                                    public String getCodeToLombokize2() {
                                        return codeToLombokize2;
                                    }
                                }
                                                
                                public String getCodeToLombokize2() {
                                    return codeToLombokize2;
                                }
                                                
                                public void setCodeToLombokize2(String codeToLombokize2) {
                                    this.codeToLombokize2 = codeToLombokize2;
                                }
                                                
                                public IndentType getIndentType2() {
                                    return indentType2;
                                }
                                                
                                public void setIndentType2(IndentType indentType2) {
                                    this.indentType2 = indentType2;
                                }
                            }
                        }
                                                
                        public class Class4 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public String getCodeToLombokize() {
                                return codeToLombokize;
                            }
                                                
                            public void setCodeToLombokize(String codeToLombokize) {
                                this.codeToLombokize = codeToLombokize;
                            }
                                                
                            public IndentType getIndentType() {
                                return indentType;
                            }
                                                
                            public void setIndentType(IndentType indentType) {
                                this.indentType = indentType;
                            }
                                                
                            public AnnotationsConfig getAnnotationsConfig() {
                                return annotationsConfig;
                            }
                                                
                            public void setAnnotationsConfig(AnnotationsConfig annotationsConfig) {
                                this.annotationsConfig = annotationsConfig;
                            }
                                                
                            public class Class5 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public String getCodeToLombokize2() {
                                    return codeToLombokize2;
                                }
                                                
                                public void setCodeToLombokize2(String codeToLombokize2) {
                                    this.codeToLombokize2 = codeToLombokize2;
                                }
                                                
                                public IndentType getIndentType2() {
                                    return indentType2;
                                }
                                                
                                public void setIndentType2(IndentType indentType2) {
                                    this.indentType2 = indentType2;
                                }
                            }
                        }
                        """, false, """
                        package com.simmondobber.lomboker.lombokize.transportObjects;
                                                
                        import com.simmondobber.lomboker.lombokize.enums.IndentType;
                                                
                        public class Class1 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public class Class2 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public class Class3 {
                                                
                                    private String codeToLombokize2;
                                    private IndentType indentType2;
                                                
                                    public String getCodeToLombokize2() {
                                        return codeToLombokize2;
                                    }
                                }
                                                
                                public String getCodeToLombokize2() {
                                    return codeToLombokize2;
                                }
                                                
                                public void setCodeToLombokize2(String codeToLombokize2) {
                                    this.codeToLombokize2 = codeToLombokize2;
                                }
                                                
                                public IndentType getIndentType2() {
                                    return indentType2;
                                }
                                                
                                public void setIndentType2(IndentType indentType2) {
                                    this.indentType2 = indentType2;
                                }
                            }
                        }
                                                
                        public class Class4 {
                                                
                            private String codeToLombokize;
                            private IndentType indentType;
                            private AnnotationsConfig annotationsConfig;
                                                
                            public class Class5 {
                                                
                                private String codeToLombokize2;
                                private IndentType indentType2;
                                                
                                public String getCodeToLombokize2() {
                                    return codeToLombokize2;
                                }
                                                
                                public void setCodeToLombokize2(String codeToLombokize2) {
                                    this.codeToLombokize2 = codeToLombokize2;
                                }
                                                
                                public IndentType getIndentType2() {
                                    return indentType2;
                                }
                                                
                                public void setIndentType2(IndentType indentType2) {
                                    this.indentType2 = indentType2;
                                }
                            }
                        }
                        """
                )
        );
    }
}
