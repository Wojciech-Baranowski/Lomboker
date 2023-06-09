package com.simmondobber.lomboker.lombokize.enums;

import lombok.Getter;

@Getter
public enum Import {

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
