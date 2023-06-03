package com.simmondobber.lomboker.lombokize.classElements;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.simmondobber.lomboker.common.Keywords.BOOLEAN;

@Getter
@AllArgsConstructor
public class Field {

    private final String fieldName;
    private final String fieldType;
    private final CodeLine codeLine;

    public int getNesting() {
        return this.codeLine.getNestingLevel();
    }

    public String getLine() {
        return this.codeLine.getLine();
    }

    public boolean isBooleanType() {
        return this.fieldType.equals(BOOLEAN.getKeyword());
    }
}
