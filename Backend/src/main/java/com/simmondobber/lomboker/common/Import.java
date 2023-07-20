package com.simmondobber.lomboker.common;

import lombok.Getter;

@Getter
public enum Import {

    LOMBOK_ALL("import lombok.*"),
    LOMBOK_EXPERIMENTAL_ALL("import lombok.experimental.*"),
    GETTER("import lombok.Getter;"),
    SETTER("import lombok.Setter;"),
    NO_ARGS_CONSTRUCTOR("import lombok.NoArgsConstructor;"),
    ALL_ARGS_CONSTRUCTOR("import lombok.AllArgsConstructor;"),
    BUILDER("import lombok.Builder;"),
    SUPER_BUILDER("import lombok.experimental.SuperBuilder;"),
    TO_STRING("import lombok.ToString;");

    private final String keyword;

    Import(String keyword) {
        this.keyword = keyword;
    }
}
