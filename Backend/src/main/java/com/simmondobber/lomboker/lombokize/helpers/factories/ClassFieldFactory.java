package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.codeElement.ClassField;
import com.simmondobber.lomboker.lombokize.codeElement.CodeLine;
import com.simmondobber.lomboker.lombokize.helpers.extractors.NameExtractor;
import com.simmondobber.lomboker.lombokize.helpers.extractors.TypeExtractor;

public class ClassFieldFactory {

    private final TypeExtractor typeExtractor;
    private final NameExtractor nameExtractor;

    public ClassFieldFactory() {
        this.typeExtractor = new TypeExtractor();
        this.nameExtractor = new NameExtractor();
    }

    public ClassField createClassField(String text) {
        CodeLine codeLine = new CodeLine(text);
        String fieldName = nameExtractor.extractClassFieldNameFromCodeLine(codeLine.getLine());
        String fieldType = typeExtractor.extractClassFieldTypeFromCodeLine(codeLine.getLine());
        return new ClassField(fieldName, fieldType, codeLine);
    }
}
