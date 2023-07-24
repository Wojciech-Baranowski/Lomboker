package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory;

public class MethodComparator {

    public boolean areMethodsEqual(String methodSyntax1, String methodSyntax2) {
        methodSyntax1 = methodSyntax1.replaceAll("`.*?`", " ");
        methodSyntax2 = methodSyntax2.replaceAll("`.*?`", " ");
        methodSyntax1 = methodSyntax1.replaceAll("\\s+", " ");
        methodSyntax2 = methodSyntax2.replaceAll("\\s+", " ");
        return methodSyntax1.equals(methodSyntax2);
    }
}
