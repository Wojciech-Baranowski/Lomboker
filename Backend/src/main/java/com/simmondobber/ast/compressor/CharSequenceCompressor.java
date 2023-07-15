package com.simmondobber.ast.compressor;

public class CharSequenceCompressor implements CodeCompressor {

    private final CompressedCode code;

    public CharSequenceCompressor(CompressedCode code) {
        this.code = code;
    }

    @Override
    public CompressedCode compress() {
        compressStrings();
        compressCharacters();
        return this.code;
    }

    private void compressStrings() {
        while (this.code.getCode().contains("\"")) {
            int startOfString = this.code.getCode().indexOf("\"");
            int endOfString = this.code.getCode().indexOf("\"", startOfString + 1);
            this.code.compressFragment(startOfString, endOfString);
        }
    }

    private void compressCharacters() {
        while (this.code.getCode().contains("'")) {
            int startOfCharacter = this.code.getCode().indexOf("'");
            int endOfCharacter = this.code.getCode().indexOf("'", startOfCharacter + 1);
            this.code.compressFragment(startOfCharacter, endOfCharacter);
        }
    }
}
