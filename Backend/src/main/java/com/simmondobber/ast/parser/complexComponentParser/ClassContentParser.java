package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.ClassContentComponent;
import com.simmondobber.ast.components.simpleAstComponents.ClassTypeKeyword;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.ArrayList;
import java.util.List;

public class ClassContentParser extends AstParser {

    public ClassContentParser(Pointer pointer) {
        super(pointer);
    }

    public ClassContentParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public ClassContent parse() {
        List<ClassContentComponent> classComponents = new ArrayList<>();
        while (true) {
            if (this.pointer.lookupCharacter() == '}' || this.pointer.lookupCharacter() == '$') {
                break;
            } else {
                this.pointer.cacheCurrentState();
                new PreambleParser(this.pointer).parse();
                if (ClassTypeKeyword.CLASS_TYPE_KEYWORDS.contains(this.pointer.lookupWord())) {
                    this.pointer.restoreCurrentState();
                    classComponents.add(new ClassParser(this.pointer).parse());
                } else if ((this.pointer.lookupIndexOf('(') < this.pointer.lookupIndexOf(';')) && (this.pointer.lookupIndexOf('(') < this.pointer.lookupIndexOf('='))) {
                    this.pointer.restoreCurrentState();
                    classComponents.add(new MethodParser(this.pointer).parse());
                } else {
                    this.pointer.restoreCurrentState();
                    classComponents.add(new FieldParser(this.pointer).parse());
                }
            }
        }
        return new ClassContent(classComponents);
    }
}
