package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.ClassContentComponent;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.utils.Pointer;

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
                } else if (this.pointer.lookupIndexOf(';') == -1 || (this.pointer.lookupIndexOf('(') < this.pointer.lookupIndexOf(';')) && (this.pointer.lookupIndexOf('=') == -1 || this.pointer.lookupIndexOf('(') < this.pointer.lookupIndexOf('='))) {
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
