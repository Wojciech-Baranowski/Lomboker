package com.simmondobber.ast.parser.method;

import com.simmondobber.ast.components.complexAstComponents.method.preamble.MethodPreamble;
import com.simmondobber.ast.components.complexAstComponents.method.preamble.MethodPreambleComponent;
import com.simmondobber.ast.components.simpleAstComponents.FieldKeyword;
import com.simmondobber.ast.components.simpleAstComponents.MethodKeyword;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.annotation.AnnotationParser;
import com.simmondobber.ast.parser.generic.GenericParser;

import java.util.ArrayList;
import java.util.List;

public class MethodPreambleParser extends AstParser {

    public MethodPreambleParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public MethodPreamble parse() {
        List<MethodPreambleComponent> preambleComponents = new ArrayList<>();
        while (true) {
            if (this.pointer.lookupCharacter() == '@') {
                preambleComponents.add(new AnnotationParser(this.pointer).parse());
            } else if (this.pointer.lookupCharacter() == '<') {
                preambleComponents.add(new GenericParser(this.pointer).parse());
            } else if (FieldKeyword.KEYWORDS.contains(this.pointer.lookupWord())) {
                preambleComponents.add(new MethodKeyword(this.pointer.getWord(), this.pointer.getSeparator()));
            } else {
                break;
            }
        }
        return new MethodPreamble(preambleComponents);
    }
}
