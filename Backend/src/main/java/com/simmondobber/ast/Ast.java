package com.simmondobber.ast;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.compressor.CharSequenceCompressor;
import com.simmondobber.ast.compressor.CommentCompressor;
import com.simmondobber.ast.compressor.CompressedCode;
import com.simmondobber.ast.parser.componentParser.FileParser;
import com.simmondobber.ast.parser.utils.Pointer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
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
        CompressedCode compressedCode = new CompressedCode(code);
        compressedCode = new CharSequenceCompressor(compressedCode).compress();
        compressedCode = new CommentCompressor(compressedCode).compress();
        return compressedCode;
    }

    private String decompress(String code) {
        String decompressedCode = code;
        List<String> identifiers = new ArrayList<>(this.dictionary.keySet().stream()
                .sorted(this::compareIdentifiers)
                .toList());
        for (String identifier : identifiers) {
            decompressedCode = decompressedCode.replace(identifier, this.dictionary.get(identifier));
        }
        return decompressedCode;
    }

    private int compareIdentifiers(String id1, String id2) {
        int intId1 = Integer.decode(id1.substring(1, id1.length() - 1));
        int intId2 = Integer.decode(id2.substring(1, id2.length() - 1));
        return Integer.compare(intId2, intId1);
    }
}
