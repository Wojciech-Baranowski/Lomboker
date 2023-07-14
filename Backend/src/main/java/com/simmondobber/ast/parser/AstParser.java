package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.AstComponent;
import com.simmondobber.ast.parser.utils.Pointer;

public abstract class AstParser {

    protected Pointer pointer;

    public AstParser(String stringToParse) {
        this.pointer = new Pointer(stringToParse);
    }

    public AstParser(Pointer pointer) {
        this.pointer = pointer;
    }

    public abstract AstComponent parse();
}
