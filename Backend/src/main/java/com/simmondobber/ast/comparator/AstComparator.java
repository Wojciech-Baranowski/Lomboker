package com.simmondobber.ast.comparator;

import com.simmondobber.lomboker.common.Trimmer;

public class AstComparator {

    public boolean areMethodsEqual(String methodSyntax1, String methodSyntax2) {
        String trimmedMethodSyntax1 = Trimmer.compressSeparators(methodSyntax1);
        String trimmedMethodSyntax2 = Trimmer.compressSeparators(methodSyntax2);
        return trimmedMethodSyntax1.equals(trimmedMethodSyntax2);
    }

    public boolean areAnnotationsEqual(String annotationSyntax1, String annotationSyntax2) {
        String trimmedAnnotationsSyntax1 = Trimmer.compressSeparators(annotationSyntax1);
        String trimmedAnnotationsSyntax2 = Trimmer.compressSeparators(annotationSyntax2);
        return trimmedAnnotationsSyntax1.equals(trimmedAnnotationsSyntax2);
    }
}
