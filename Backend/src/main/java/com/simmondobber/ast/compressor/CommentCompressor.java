package com.simmondobber.ast.compressor;

public class CommentCompressor {

    private final CompressedCode code;

    public CommentCompressor(CompressedCode code) {
        this.code = code;
    }

    public CompressedCode compress() {
        compressLineComments();
        compressBlockComments();
        compressDocsComments();
        return this.code;
    }

    private void compressLineComments() {
        while (this.code.getCode().contains("//")) {
            int startOfComment = this.code.getCode().indexOf("//");
            int endOfComment = this.code.getCode().indexOf("\n", startOfComment) - 1;
            this.code.compressFragment(startOfComment, endOfComment);
        }
    }

    private void compressBlockComments() {
        while (this.code.getCode().contains("/*")) {
            int startOfComment = this.code.getCode().indexOf("/*");
            int endOfComment = this.code.getCode().indexOf("*/", startOfComment) + 1;
            this.code.compressFragment(startOfComment, endOfComment);
        }
    }

    private void compressDocsComments() {
        while (this.code.getCode().contains("/**")) {
            int startOfComment = this.code.getCode().indexOf("/**");
            int endOfComment = this.code.getCode().indexOf("**/", startOfComment) + 2;
            this.code.compressFragment(startOfComment, endOfComment);
        }
    }
}
