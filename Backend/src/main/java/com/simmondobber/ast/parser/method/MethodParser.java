package com.simmondobber.ast.parser.method;

import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.complexAstComponents.method.Method;
import com.simmondobber.ast.components.complexAstComponents.preamble.Preamble;
import com.simmondobber.ast.components.complexAstComponents.type.Type;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.args.ArgsParser;
import com.simmondobber.ast.parser.preamble.PreambleParser;
import com.simmondobber.ast.parser.type.TypeParser;

public class MethodParser extends AstParser {

    public MethodParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Method parse() {
        Preamble methodPreamble = new PreambleParser(this.pointer).parse();
        Type type = null;
        if (this.pointer.lookupCharacterAfterWordAndSeparator() != '(') {
            type = new TypeParser(this.pointer).parse();
        }
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        Args args = new ArgsParser(this.pointer).parse();
        MethodBody methodBody = null;
        Character semicolon = null;
        if (this.pointer.lookupCharacter() == ';') {
            semicolon = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        } else {
            methodBody = new MethodBody(this.pointer.getUntilInclusive('}'), this.pointer.getSeparator());
        }
        return new Method(methodPreamble, type, name, args, methodBody, semicolon);
    }
}
