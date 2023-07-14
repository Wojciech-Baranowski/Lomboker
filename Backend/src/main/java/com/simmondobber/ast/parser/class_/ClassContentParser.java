package com.simmondobber.ast.parser.class_;

import com.simmondobber.ast.components.complexAstComponents.class_.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.class_.ClassContentComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.field.FieldParser;
import com.simmondobber.ast.parser.method.MethodParser;
import com.simmondobber.ast.parser.preamble.PreambleParser;

import java.util.ArrayList;
import java.util.List;

public class ClassContentParser extends AstParser {

    public ClassContentParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ClassContent parse() {
        List<ClassContentComponent> classComponents = new ArrayList<>();
        while (true) {
            if (this.pointer.lookupCharacter() == '}') {
                break;
            } else {
                int beforePreamble = this.pointer.getCurrentPosition();
                new PreambleParser(this.pointer).parse();
                if (Keyword.CLASS_TYPE_KEYWORDS.contains(this.pointer.lookupWord())) {
                    this.pointer.setCurrentPosition(beforePreamble);
                    classComponents.add(new ClassParser(this.pointer).parse());
                } else if (this.pointer.lookupIndexOf('(') < this.pointer.lookupIndexOf(';') && this.pointer.lookupIndexOf('(') < this.pointer.lookupIndexOf('=')) {
                    this.pointer.setCurrentPosition(beforePreamble);
                    classComponents.add(new MethodParser(this.pointer).parse());
                } else {
                    this.pointer.setCurrentPosition(beforePreamble);
                    classComponents.add(new FieldParser(this.pointer).parse());
                }
            }
        }
        return new ClassContent(classComponents);
    }
}
