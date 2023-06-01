package com.simmondobber.lomboker.lombokize.codeElement;

import lombok.Getter;

import java.util.Set;

public class ClassHeader {

    private final CodeLine header;
    @Getter
    private final Set<String> classAnnotations;

    public ClassHeader(CodeLine header, Set<String> classAnnotations) {
        this.header = header;
        this.classAnnotations = classAnnotations;
    }

    public String getHeaderLine() {
        return this.header.getLine();
    }
}
