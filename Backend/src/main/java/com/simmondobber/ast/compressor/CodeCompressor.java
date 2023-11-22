package com.simmondobber.ast.compressor;

import java.util.Comparator;
import java.util.List;

public class CodeCompressor {

    private static final List<String> OPENINGS = List.of("\\\"", "\"", "'", "//", "/*", "/**");
    private final String code;

    public CodeCompressor(String code) {
        this.code = code;
    }

    public CompressedCode compressCode() {
        CompressedCode compressedCode = new CompressedCode(this.code);
        String openingOfNextBlockToCompress = getOpeningOfNextBlockToCompress(compressedCode);
        while (openingOfNextBlockToCompress != null) {
            compressBlock(compressedCode, openingOfNextBlockToCompress);
            openingOfNextBlockToCompress = getOpeningOfNextBlockToCompress(compressedCode);
        }
        return compressedCode;
    }

    private String getOpeningOfNextBlockToCompress(CompressedCode code) {
        return OPENINGS.stream()
                .filter(opening -> code.getCode().contains(opening))
                .min(Comparator.comparing(opening -> code.getCode().indexOf(opening)))
                .orElse(null);
    }

    private void compressBlock(CompressedCode code, String openingOfNextBlockToCompress) {
        switch (openingOfNextBlockToCompress) {
            case "\\\"" -> compressQuotes(code);
            case "\"" -> compressStrings(code);
            case "'" -> compressCharacters(code);
            case "//" -> compressLineComments(code);
            case "/*" -> compressBlockComments(code);
            case "/**" -> compressDocsComments(code);
        }
    }

    private void compressQuotes(CompressedCode code) {
        int startOfString = code.getCode().indexOf("\\\"");
        int endOfString = startOfString + 1;
        code.compressFragment(startOfString, endOfString);
    }

    private void compressStrings(CompressedCode code) {
        int startOfString = code.getCode().indexOf("\"");
        int endOfString = code.getCode().indexOf("\"", startOfString + 1);
        code.compressFragment(startOfString, endOfString);
    }

    private void compressCharacters(CompressedCode code) {
        int startOfCharacter = code.getCode().indexOf("'");
        int endOfCharacter = code.getCode().indexOf("'", startOfCharacter + 1);
        if (endOfCharacter == -1 || endOfCharacter - startOfCharacter > 2) {
            endOfCharacter = startOfCharacter;
        }
        code.compressFragment(startOfCharacter, endOfCharacter);
    }

    private void compressLineComments(CompressedCode code) {
        int startOfComment = code.getCode().indexOf("//");
        int endOfComment = code.getCode().indexOf("\n", startOfComment) - 1;
        code.compressFragment(startOfComment, endOfComment);
    }

    private void compressBlockComments(CompressedCode code) {
        int startOfComment = code.getCode().indexOf("/*");
        int endOfComment = code.getCode().indexOf("*/", startOfComment) + 1;
        code.compressFragment(startOfComment, endOfComment);
    }

    private void compressDocsComments(CompressedCode code) {
        int startOfComment = code.getCode().indexOf("/**");
        int endOfComment = code.getCode().indexOf("**/", startOfComment) + 2;
        code.compressFragment(startOfComment, endOfComment);
    }
}
