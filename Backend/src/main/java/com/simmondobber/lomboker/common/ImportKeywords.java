package com.simmondobber.lomboker.common;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ImportKeywords {

    LOMBOK_ALL("lombok.*"),
    LOMBOK_EXPERIMENTAL_ALL("lombok.experimental.*"),
    GETTER("lombok.Getter"),
    SETTER("lombok.Setter"),
    NO_ARGS_CONSTRUCTOR("lombok.NoArgsConstructor"),
    ALL_ARGS_CONSTRUCTOR("lombok.AllArgsConstructor"),
    BUILDER("lombok.Builder"),
    SUPER_BUILDER("lombok.experimental.SuperBuilder"),
    TO_STRING("lombok.ToString"),
    EQUALS_AND_HASH_CODE("lombok.EqualsAndHashCode");

    private final String path;

    ImportKeywords(String path) {
        this.path = path;
    }

    public static boolean contains(String importCandidate) {
        return Arrays.stream(ImportKeywords.values())
                .map(ImportKeywords::getPath)
                .anyMatch(path -> path.equals(importCandidate));
    }
}
