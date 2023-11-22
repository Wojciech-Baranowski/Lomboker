package com.simmondobber.lomboker.common;

import lombok.Getter;

@Getter
public enum AnnotationData {

    GETTER("@Getter", ImportKeywordData.GETTER),
    SETTER("@Setter", ImportKeywordData.SETTER),
    NO_ARGS_CONSTRUCTOR("@NoArgsConstructor", ImportKeywordData.NO_ARGS_CONSTRUCTOR),
    REQUIRED_ARGS_CONSTRUCTOR("@RequiredArgsConstructor", ImportKeywordData.REQUIRED_ARGS_CONSTRUCTOR),
    ALL_ARGS_CONSTRUCTOR("@AllArgsConstructor", ImportKeywordData.ALL_ARGS_CONSTRUCTOR),
    BUILDER("@Builder", ImportKeywordData.BUILDER),
    BUILDER_TO_BUILDER("@Builder(toBuilder = true)", ImportKeywordData.BUILDER),
    SUPER_BUILDER("@SuperBuilder", ImportKeywordData.SUPER_BUILDER),
    SUPER_BUILDER_TO_BUILDER("@SuperBuilder(toBuilder = true)", ImportKeywordData.SUPER_BUILDER),
    TO_STRING("@ToString", ImportKeywordData.TO_STRING),
    TO_STRING_CALL_SUPER("@ToString(callSuper = true)", ImportKeywordData.TO_STRING),
    EQUALS_AND_HASH_CODE("@EqualsAndHashCode", ImportKeywordData.EQUALS_AND_HASH_CODE);

    private final String keyword;
    private final ImportKeywordData importKeywordData;

    AnnotationData(String keyword, ImportKeywordData importKeywordData) {
        this.keyword = keyword;
        this.importKeywordData = importKeywordData;
    }
}