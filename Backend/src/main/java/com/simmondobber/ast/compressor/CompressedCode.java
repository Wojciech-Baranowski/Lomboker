package com.simmondobber.ast.compressor;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CompressedCode {

    @Getter
    private String code;
    private int sequence;
    @Getter
    private final Map<String, String> dictionary;

    public CompressedCode(String code) {
        this.code = code;
        this.dictionary = new HashMap<>();
        this.sequence = 0;
    }

    public void compressFragment(int startingIndex, int endingIndex) {
        String fragment = this.code.substring(startingIndex, endingIndex + 1);
        String identifier = generateNextIdentifier();
        this.dictionary.put(identifier, fragment);
        this.code = this.code.substring(0, startingIndex) + identifier + this.code.substring(endingIndex + 1);
    }

    private String generateNextIdentifier() {
        String identifier = Integer.toString(this.sequence);
        this.sequence++;
        return "`" + identifier + '`';
    }
}
