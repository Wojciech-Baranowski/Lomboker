package com.simmondobber.lomboker.lombokize.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Annotation {

    GETTER("@Getter"),
    SETTER("@Setter"),
    NO_ARGS_CONSTRUCTOR("@NoArgsConstructor"),
    ALL_ARGS_CONSTRUCTOR("@AllArgsConstructor"),
    BUILDER("@Builder"),
    BUILDER_TO_BUILDER("@Builder(toBuilder = true)"),
    SUPER_BUILDER("@SuperBuilder"),
    SUPER_BUILDER_TO_BUILDER("@SuperBuilder(toBuilder = true)"),
    TO_STRING("@ToString"),
    TO_STRING_CALL_SUPER("@ToString(callSuper = true)");

    private final String symbol;

    Annotation(String symbol) {
        this.symbol = symbol;
    }

    public static Annotation getBySymbol(String symbol) {
        return Arrays.stream(values())
                .filter(annotation -> annotation.getSymbol().equals(symbol))
                .findFirst()
                .orElse(null);
    }
}
