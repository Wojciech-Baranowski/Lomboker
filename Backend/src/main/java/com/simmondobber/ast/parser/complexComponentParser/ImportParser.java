package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.KeywordParser;
import com.simmondobber.ast.parser.simpleComponentParser.PathParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ImportParser extends AstParser {

    public ImportParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Import parse() {
        Keyword importKeyword = new KeywordParser(this.pointer).parse();
        Keyword staticKeyword = null;
        if (this.pointer.lookupWord().equals("static")) {
            staticKeyword = new KeywordParser(this.pointer).parse();
        }
        Path path = new PathParser(this.pointer).parse();
        Character semicolon = new CharacterParser(this.pointer).parse();
        return new Import(importKeyword, staticKeyword, path, semicolon);
    }
}
