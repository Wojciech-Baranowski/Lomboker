package com.simmondobber.ast.parser.class_;

import com.simmondobber.ast.components.complexAstComponents.class_.Class;
import com.simmondobber.ast.components.complexAstComponents.class_.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.generic.Generic;
import com.simmondobber.ast.components.complexAstComponents.preamble.Preamble;
import com.simmondobber.ast.components.simpleAstComponents.Extension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.generic.GenericParser;
import com.simmondobber.ast.parser.preamble.PreambleParser;

import java.util.List;

public class ClassParser extends AstParser {

    public ClassParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Class parse() {
        Preamble preamble = new PreambleParser(this.pointer).parse();
        Keyword classType = new Keyword(this.pointer.getWord(), this.pointer.getSeparator());
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        Generic generic = null;
        if (this.pointer.lookupCharacter() == '<') {
            generic = new GenericParser(this.pointer).parse();
        }
        Extension extension = null;
        if (List.of("extends", "implements").contains(this.pointer.lookupWord())) {
            extension = new Extension(this.pointer.getUntil("{"));
        }
        ClassBody classBody = new ClassBodyParser(this.pointer).parse();
        return new Class(preamble, classType, name, generic, extension, classBody);
    }
}
