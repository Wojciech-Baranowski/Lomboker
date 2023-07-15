package com.simmondobber.ast.parser.componentParser;

import com.simmondobber.ast.components.complexAstComponents.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.utils.Pointer;

public class ClassBodyParser extends AstParser {

    public ClassBodyParser(Pointer pointer) {
        super(pointer);
    }

    @Override
    public ClassBody parse() {
        Character leftCurly = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        EnumValues enumValues = null;
        if (",;}".contains(java.lang.Character.toString(this.pointer.lookupCharacterAfterWordAndSeparator()))) {
            enumValues = new EnumValuesParser(this.pointer).parse();
        }
        ClassContent classContent = new ClassContentParser(this.pointer).parse();
        Character rightCurly = new Character(this.pointer.getCharacter(), this.pointer.getSeparator());
        return new ClassBody(leftCurly, enumValues, classContent, rightCurly);
    }
}
