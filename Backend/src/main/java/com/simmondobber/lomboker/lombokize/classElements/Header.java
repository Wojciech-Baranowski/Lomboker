package com.simmondobber.lomboker.lombokize.classElements;

import com.simmondobber.lomboker.lombokize.enums.Annotation;
import com.simmondobber.lomboker.lombokize.helpers.extractors.AnnotationExtractor;
import lombok.Getter;

import java.util.Set;

public class Header {

    private final CodeLine header;
    @Getter
    private final Set<Annotation> classAnnotations;

    public Header(CodeLine header, String classCode) {
        this.header = header;
        this.classAnnotations = new AnnotationExtractor().getAnnotationsAboveCodeLine(classCode, header);
    }

    public String getLine() {
        return this.header.getLine();
    }

}
