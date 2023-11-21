package com.simmondobber.lomboker.common;

import lombok.Getter;

@Getter
public enum AnnotationKeywords {

    GETTER("@Getter"),
    SETTER("@Setter"),
    NO_ARGS_CONSTRUCTOR("@NoArgsConstructor"),
    ALL_ARGS_CONSTRUCTOR("@AllArgsConstructor"),
    BUILDER("@Builder"),
    SUPER_BUILDER("@SuperBuilder"),
    TO_STRING("@ToString"),
    TO_BUILDER("(toBuilder = true)"),
    CALL_SUPER("(callSuper = true)"),
    EQUALS_AND_HASH_CODE("@EqualsAndHashCode");

    private final String keyword;

    AnnotationKeywords(String keyword) {
        this.keyword = keyword;
    }
}