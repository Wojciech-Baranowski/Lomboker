package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.*;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.MethodBody;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.MethodBodyParser;
import com.simmondobber.ast.parser.simpleComponentParser.NameParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class MethodParser extends AstParser {

    public MethodParser(Pointer pointer) {
        super(pointer);
    }

    public MethodParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Method parse() {
        Preamble methodPreamble = new PreambleParser(this.pointer).parse();
        Type type = new TypeParser(this.pointer).parse();
        Name name = null;
        if (this.pointer.lookupCharacter() != '(') {
            name = new NameParser(this.pointer).parse();
        }
        Args args = new ArgsParser(this.pointer).parse();
        Throws throws_ = null;
        if (this.pointer.lookupWord().equals("throws")) {
            throws_ = new ThrowsParser(this.pointer).parse();
        }
        MethodBody methodBody = null;
        Character semicolon = null;
        if (this.pointer.lookupCharacter() == ';') {
            semicolon = new CharacterParser(this.pointer).parse();
        } else {
            methodBody = new MethodBodyParser(this.pointer).parse();
        }
        return new Method(methodPreamble, type, name, args, throws_, methodBody, semicolon);
    }
}
