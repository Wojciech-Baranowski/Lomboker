package com.simmondobber.ast.parser.class_;

import com.simmondobber.ast.components.complexAstComponents.class_.ClassBody;
import com.simmondobber.ast.components.complexAstComponents.class_.ClassContent;
import com.simmondobber.ast.components.complexAstComponents.enumValues.EnumValues;
import com.simmondobber.ast.components.simpleAstComponents.Character;
import com.simmondobber.ast.parser.AstParser;
import com.simmondobber.ast.parser.Pointer;
import com.simmondobber.ast.parser.enumValues.EnumValuesParser;

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
