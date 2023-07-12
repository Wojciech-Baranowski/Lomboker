package com.simmondobber.ast.parser;

import lombok.Getter;
import lombok.Setter;

public class Pointer {

    @Getter
    @Setter
    private int index;

    public Pointer() {
        this.index = 0;
    }

    public void increment() {
        increment(1);
    }

    public void increment(int value) {
        this.index += value;
    }
}
