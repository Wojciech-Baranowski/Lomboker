package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.List;

public class ClassParser extends AstParser {

    public ClassParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Class parse() {
        Preamble preamble = new PreambleParser(this.pointer).parse();
        Keyword classType = new Keyword(this.pointer.getWord(), this.pointer.getSeparator());
        Type type = new TypeParser(this.pointer).parse();
        Extension extension = null;
        if (List.of("extends", "implements").contains(this.pointer.lookupWord())) {
            extension = new Extension(this.pointer.getUntil("{"));
        }
        ClassBody classBody = new ClassBodyParser(this.pointer).parse();
        return new Class(preamble, classType, type, extension, classBody);
    }
}
