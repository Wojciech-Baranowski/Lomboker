package com.simmondobber.ast.parser.annotation;

import com.simmondobber.ast.components.complexAstComponents.annotation.Annotation;
import com.simmondobber.ast.components.complexAstComponents.args.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Name;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.args.ArgsParser;

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
