package com.simmondobber.lomboker.lombokize.annotationAdder;

public class Point {

    public class CoordinateX {

        private int x;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    public class CoordinateY {

        private int y;

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    private CoordinateX x;
    private CoordinateY y;

    public CoordinateX getX() {
        return x;
    }

    public void setX(CoordinateX x) {
        this.x = x;
    }
}