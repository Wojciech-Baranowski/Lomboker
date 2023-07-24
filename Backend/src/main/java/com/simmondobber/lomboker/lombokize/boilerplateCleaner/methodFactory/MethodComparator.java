package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory;

import com.simmondobber.lomboker.common.Trimmer;

public class MethodComparator {

    public boolean areMethodsEqual(String methodSyntax1, String methodSyntax2) {
        String trimmedMethodSyntax1 = Trimmer.compressSeparators(methodSyntax1);
        String trimmedMethodSyntax2 = Trimmer.compressSeparators(methodSyntax2);
        return trimmedMethodSyntax1.equals(trimmedMethodSyntax2);
    }
}
