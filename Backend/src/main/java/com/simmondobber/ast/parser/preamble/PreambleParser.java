package com.simmondobber.ast.parser.preamble;

import com.simmondobber.ast.components.complexAstComponents.preamble.Preamble;
import com.simmondobber.ast.components.complexAstComponents.preamble.PreambleComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.annotation.AnnotationParser;
import com.simmondobber.ast.parser.generic.GenericParser;

import java.util.ArrayList;
import java.util.List;

public class PreambleParser extends AstParser {

    public PreambleParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Preamble parse() {
        List<PreambleComponent> preambleComponents = new ArrayList<>();
        while (true) {
            if (this.pointer.lookupCharacter() == '@') {
                preambleComponents.add(new AnnotationParser(this.pointer).parse());
            } else if (this.pointer.lookupCharacter() == '<') {
                preambleComponents.add(new GenericParser(this.pointer).parse());
            } else if (Keyword.PREAMBLE_KEYWORDS.contains(this.pointer.lookupWord())) {
                preambleComponents.add(new Keyword(this.pointer.getWord(), this.pointer.getSeparator()));
            } else {
                break;
            }
        }
        return new Preamble(preambleComponents);
    }
}
