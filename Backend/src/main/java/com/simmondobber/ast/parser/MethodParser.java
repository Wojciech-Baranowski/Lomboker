package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.complexAstComponents.Method;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
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
        MethodBody methodBody = null;
        Character semicolon = null;
        if (this.pointer.lookupCharacter() == ';') {
            semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        } else {
            methodBody = new MethodBody(this.pointer.getInside('}'), this.pointer.getSeparator());
        }
        return new Method(methodPreamble, type, name, args, methodBody, semicolon);
    }
}
