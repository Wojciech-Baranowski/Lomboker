package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.Class;
import com.simmondobber.ast.components.complexAstComponents.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.Preamble;
import com.simmondobber.ast.components.complexAstComponents.Type;
import com.simmondobber.ast.components.simpleAstComponents.ClassExtension;
import com.simmondobber.ast.components.simpleAstComponents.Keyword;
import com.simmondobber.ast.parser.simpleComponentParser.ClassExtensionParser;
import com.simmondobber.ast.parser.simpleComponentParser.KeywordParser;
import com.simmondobber.ast.parser.utils.Pointer;

import java.util.List;

public class ClassParser extends AstParser {

    public ClassParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public Class parse() {
        Preamble preamble = new PreambleParser(this.pointer).parse();
        Keyword classType = new KeywordParser(this.pointer).parse();
        Type type = new TypeParser(this.pointer).parse();
        ClassExtension classExtension = null;
        if (List.of("extends", "implements").contains(this.pointer.lookupWord())) {
            classExtension = new ClassExtensionParser(this.pointer).parse();
        }
        ClassBody classBody = new ClassBodyParser(this.pointer).parse();
        return new Class(preamble, classType, type, classExtension, classBody);
    }
}
