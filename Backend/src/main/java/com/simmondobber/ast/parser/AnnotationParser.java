package com.simmondobber.ast.parser;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.utils.Pointer;

public class AnnotationParser extends AstParser {

    public AnnotationParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Annotation parse() {
        Character at = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        Name name = new Name(this.pointer.getWord(), this.pointer.getSeparator());
        Args args = null;
        if (this.pointer.lookupCharacter() == '(') {
            args = new ArgsParser(this.pointer).parse();
        }
        return new Annotation(at, name, args);
    }
}
