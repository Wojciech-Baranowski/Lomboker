package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.classElements.CodeLine;
import com.simmondobber.lomboker.lombokize.classElements.Field;
import com.simmondobber.lomboker.lombokize.helpers.extractors.fieldExtractor.FieldNameExtractor;
import com.simmondobber.lomboker.lombokize.helpers.extractors.fieldExtractor.FieldTypeExtractor;

public class ClassFieldFactory {

    private final FieldTypeExtractor fieldTypeExtractor;
    private final FieldNameExtractor fieldNameExtractor;

    public ClassFieldFactory() {
        this.fieldTypeExtractor = new FieldTypeExtractor();
        this.fieldNameExtractor = new FieldNameExtractor();
    }

    public Field createClassField(String text) {
        CodeLine codeLine = new CodeLine(text);
        String fieldName = fieldNameExtractor.extractClassFieldNameFromCodeLine(codeLine.getLine());
        String fieldType = fieldTypeExtractor.extractClassFieldTypeFromCodeLine(codeLine.getLine());
        return new Field(fieldName, fieldType, codeLine);
    }
}
