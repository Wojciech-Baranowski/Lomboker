package com.simmondobber.ast.compressor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CodeDecompressor {

    private final String code;
    private final Map<String, String> dictionary;

    public CodeDecompressor(String code, Map<String, String> dictionary) {
        this.code = code;
        this.dictionary = dictionary;
    }

    public String decompress() {
        String decompressedCode = this.code;
        List<String> identifiers = getOrderedIdentifiers();
        for (String identifier : identifiers) {
            decompressedCode = decompressedCode.replace(identifier, this.dictionary.get(identifier));
        }
        return decompressedCode;
    }

    private List<String> getOrderedIdentifiers() {
        return new ArrayList<>(this.dictionary.keySet().stream()
                .sorted(this::compareIdentifiers)
                .toList());
    }

    private int compareIdentifiers(String id1, String id2) {
        int intId1 = Integer.decode(id1.substring(1, id1.length() - 1));
        int intId2 = Integer.decode(id2.substring(1, id2.length() - 1));
        return Integer.compare(intId2, intId1);
    }
}
