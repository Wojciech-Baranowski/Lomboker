package com.simmondobber.ast.parser.field;

import com.simmondobber.ast.components.complexAstComponents.field.preamble.FieldPreamble;
import com.simmondobber.ast.components.complexAstComponents.field.preamble.FieldPreambleComponent;
import com.simmondobber.ast.components.simpleAstComponents.FieldKeyword;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.annotation.AnnotationParser;

import java.util.ArrayList;
import java.util.List;

public class FieldPreambleParser extends AstParser {

    public FieldPreambleParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public FieldPreamble parse() {
        List<FieldPreambleComponent> preambleComponents = new ArrayList<>();
        while (true) {
            if (this.pointer.lookupCharacter() == '@') {
                preambleComponents.add(new AnnotationParser(this.pointer).parse());
            } else if (FieldKeyword.KEYWORDS.contains(this.pointer.lookupWord())) {
                preambleComponents.add(new FieldKeyword(this.pointer.getWord(), this.pointer.getSeparator()));
            } else {
                break;
            }
        }
        return new FieldPreamble(preambleComponents);
    }
}
