package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.utils.Pointer;

public class MethodParser extends AstParser {

    public MethodParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Method parse() {
        Preamble methodPreamble = new PreambleParser(this.pointer).parse();
        Type type = new TypeParser(this.pointer).parse();
        Name name = null;
        if (this.pointer.lookupCharacter() != '(') {
            name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        }
        Args args = new ArgsParser(this.pointer).parse();
        Throws throws_ = null;
        if (this.pointer.lookupWord().equals("throws")) {
            throws_ = new ThrowsParser(this.pointer).parse();
        }
        MethodBody methodBody = null;
        Character semicolon = null;
        if (this.pointer.lookupCharacter() == ';') {
            semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        } else {
            methodBody = new MethodBody(this.pointer.getInsideInclusive('}'), this.pointer.getSeparator());
        }
        return new Method(methodPreamble, type, name, args, throws_, methodBody, semicolon);
    }
}
