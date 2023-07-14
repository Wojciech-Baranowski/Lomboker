package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Path;

public class ImportParser extends AstParser {

    public ImportParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Import parse() {
        Keyword importKeyword = new Keyword(this.pointer.getWord(), this.pointer.getSeparator());
        Keyword staticKeyword = null;
        if (this.pointer.lookupWord().equals("static")) {
            staticKeyword = new Keyword(this.pointer.getWord(), this.pointer.getSeparator());
        }
        Path path = new Path(this.pointer.getCompoundWord(), this.pointer.getSeparator());
        Character semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new Import(importKeyword, staticKeyword, path, semicolon);
    }
}
