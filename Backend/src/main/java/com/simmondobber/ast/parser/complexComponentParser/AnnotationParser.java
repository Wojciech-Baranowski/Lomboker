package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.components.complexAstComponents.Args;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.components.simpleAstComponents.Path;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.simpleComponentParser.PathParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class AnnotationParser extends AstParser {

    public AnnotationParser(Pointer pointer) {
        super(pointer);
    }

    public AnnotationParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public Annotation parse() {
        Character at = new CharacterParser(this.pointer).parse();
        Path path = new PathParser(this.pointer).parse();
        Args args = null;
        if (this.pointer.lookupCharacter() == '(') {
            args = new ArgsParser(this.pointer).parse();
        }
        return new Annotation(at, path, args);
    }
}
