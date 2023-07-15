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
            int startOfComment = this.code.getCode().indexOf("\"");
            int endOfComment = this.code.getCode().indexOf("\"", startOfComment + 1);
            this.code.compressFragment(startOfComment, endOfComment);
        }
    }

    private void compressCharacters() {
        while (this.code.getCode().contains("'")) {
            int startOfComment = this.code.getCode().indexOf("'");
            int endOfComment = this.code.getCode().indexOf("'", startOfComment + 1);
            this.code.compressFragment(startOfComment, endOfComment);
        }
    }
}
