package com.simmondobber.ast;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.compressor.CodeCompressor;
import com.simmondobber.ast.compressor.CodeDecompressor;
import com.simmondobber.ast.compressor.CompressedCode;
import com.simmondobber.ast.parser.complexComponentParser.FileParser;
import com.simmondobber.ast.parser.utils.Pointer;
import lombok.Getter;

import java.util.Map;

public class Ast {

    @Getter
    private final AstComponent ast;
    private final Map<String, String> dictionary;

    public Ast(String code) {
        CompressedCode codeToCompress = compressCode(code + "$");
        Pointer pointer = new Pointer(codeToCompress.getCode());
        this.dictionary = codeToCompress.getDictionary();
        this.ast = new FileParser(pointer).parse();
    }

    public String getCode() {
        String astSyntax = this.ast.getSyntax();
        return decompress(astSyntax);
    }

    private CompressedCode compressCode(String code) {
        return new CodeCompressor(code).compressCode();
    }

    private String decompress(String code) {
        return new CodeDecompressor(code, this.dictionary).decompress();
    }
}
