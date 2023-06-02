package com.simmondobber.lomboker.lombokize.codeElement;

import com.simmondobber.lomboker.lombokize.enums.Annotation;
import com.simmondobber.lomboker.lombokize.helpers.extractors.AnnotationExtractor;
import lombok.Getter;

import java.util.Set;

public class ClassHeader {

    private final CodeLine header;
    @Getter
    private final Set<Annotation> classAnnotations;

    public ClassHeader(CodeLine header, String classCode) {
        this.header = header;
        this.classAnnotations = new AnnotationExtractor().getAnnotationsAboveCodeLine(classCode, header);
    }

    public String getHeaderLine() {
        return this.header.getLine();
    }

}
