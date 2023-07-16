package com.simmondobber.ast.parser.complexComponentParser;

import com.simmondobber.ast.components.complexAstComponents.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.simpleComponentParser.CharacterParser;
import com.simmondobber.ast.parser.utils.Pointer;

public class ClassBodyParser extends AstParser {

    public ClassBodyParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ClassBody parse() {
        Character leftCurly = new CharacterParser(this.pointer).parse();
        EnumValues enumValues = null;
        if (this.pointer.lookupCharacter() != '}' && ",;}".contains(java.lang.Character.toString(this.pointer.lookupCharacterAfterWordAndOptionalBracket()))) {
            enumValues = new EnumValuesParser(this.pointer).parse();
        }
        ClassContent classContent = null;
        if (this.pointer.lookupCharacter() != '}') {
            classContent = new ClassContentParser(this.pointer).parse();
        }
        Character rightCurly = new CharacterParser(this.pointer).parse();
        return new ClassBody(leftCurly, enumValues, classContent, rightCurly);
    }
}
