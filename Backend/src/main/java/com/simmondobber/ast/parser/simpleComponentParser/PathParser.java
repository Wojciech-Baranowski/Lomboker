package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class PathParser extends AstParser {

    public PathParser(Pointer pointer) {
        super(pointer);
    }

    public PathParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Path parse() {
        return new Path(this.pointer.getCompoundWord(), this.pointer.getSeparator());
    }
}
