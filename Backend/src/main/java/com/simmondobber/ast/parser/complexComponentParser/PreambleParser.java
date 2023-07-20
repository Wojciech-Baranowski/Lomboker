package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.PreambleComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.simpleComponentParser.KeywordParser;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.ArrayList;
import java.util.List;

public class PreambleParser extends AstParser {

    public PreambleParser(Pointer pointer) {
        super(pointer);
    }

    public PreambleParser(String stringToParse) {
        super(stringToParse);
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
                preambleComponents.add(new KeywordParser(this.pointer).parse());
            } else {
                break;
            }
        }
        return new Preamble(preambleComponents);
    }
}
