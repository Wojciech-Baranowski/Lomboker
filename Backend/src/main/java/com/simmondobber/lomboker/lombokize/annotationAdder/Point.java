package com.simmondobber.lomboker.lombokize.annotationAdder;

import lombok.Getter;
import lombok.Setter;

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