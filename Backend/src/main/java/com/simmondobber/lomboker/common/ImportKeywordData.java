package com.simmondobber.lomboker.common;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ImportKeywordData {

    LOMBOK_ALL("lombok.*"),
    GETTER("lombok.Getter"),
    SETTER("lombok.Setter"),
    NO_ARGS_CONSTRUCTOR("lombok.NoArgsConstructor"),
    REQUIRED_ARGS_CONSTRUCTOR("lombok.RequiredArgsConstructor"),
    ALL_ARGS_CONSTRUCTOR("lombok.AllArgsConstructor"),
    BUILDER("lombok.Builder"),
    SUPER_BUILDER("lombok.experimental.SuperBuilder"),
    TO_STRING("lombok.ToString"),
    EQUALS_AND_HASH_CODE("lombok.EqualsAndHashCode");

    private final String path;

    ImportKeywordData(String path) {
        this.path = path;
    }

    public static boolean contains(String importCandidate) {
        return Arrays.stream(ImportKeywordData.values())
                .map(ImportKeywordData::getPath)
                .anyMatch(path -> path.equals(importCandidate));
    }
}
