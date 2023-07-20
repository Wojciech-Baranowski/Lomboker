package com.simmondobber.ast.parser.simpleComponentParser;

import com.simmondobber.ast.components.simpleAstComponents.ClassExtension;
import com.simmondobber.ast.parser.complexComponentParser.AstParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ClassExtensionParser extends AstParser {

    public ClassExtensionParser(Pointer pointer) {
        super(pointer);
    }

    public ClassExtensionParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public ClassExtension parse() {
        return new ClassExtension(this.pointer.getUntil("{"));
    }
}
