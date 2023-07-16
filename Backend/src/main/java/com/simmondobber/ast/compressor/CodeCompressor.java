package com.simmondobber.ast.compressor;

public class CodeCompressor {

    private final String code;

    public CodeCompressor(String code) {
        this.code = code;
    }

    public CompressedCode compressCode() {
        CompressedCode compressedCode = new CompressedCode(this.code);
        compressedCode = new CharSequenceCompressor(compressedCode).compress();
        compressedCode = new CommentCompressor(compressedCode).compress();
        return compressedCode;
    }
}
