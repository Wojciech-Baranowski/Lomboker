package com.simmondobber.lomboker.codeLine;

import com.simmondobber.lomboker.codeFactory.GetterFactory;
import com.simmondobber.lomboker.codeFactory.SetterFactory;
import lombok.Getter;

import static com.simmondobber.lomboker.Keywords.BOOLEAN;

@Getter
public class ClassField extends CodeLine {

    private static final GetterFactory getterFactory = new GetterFactory();
    private static final SetterFactory setterFactory = new SetterFactory();

    private final String fieldName;
    private final String fieldType;
    private final String getter;
    private final String setter;
    private final boolean isBoolean;
    private final boolean hasDefaultDeclaration;

    public ClassField(String text) {
        super(text);
        this.isBoolean = isClassFieldContainingBooleanKeyword();
        this.hasDefaultDeclaration = isHasClassFieldDefaultDeclaration();
        this.fieldName = getClassFieldName();
        this.fieldType = getClassFieldType();
        this.getter = getterFactory.createGetterCode(this);
        this.setter = setterFactory.createSetterCode(this);
    }

    private boolean isClassFieldContainingBooleanKeyword() {
        return this.line.contains(" " + BOOLEAN.getKeyword() + " ");
    }

    private boolean isHasClassFieldDefaultDeclaration() {
        return this.line.contains("=");
    }

    private String getClassFieldName() {
        String trimmedCodeLine = getCodeLineTrimmedOnNameEnd();
        int indexOfLastSpaceCharacter = trimmedCodeLine.lastIndexOf(' ');
        return trimmedCodeLine.substring(indexOfLastSpaceCharacter + 1);
    }

    private String getClassFieldType() {
        String trimmedCodeLine = getCodeLineTrimmedOnNameEnd();
        int indexOfLastSpaceCharacter = trimmedCodeLine.lastIndexOf(' ');
        int indexOfSecondToLastSpaceCharacter = trimmedCodeLine.lastIndexOf(' ', trimmedCodeLine.lastIndexOf(' ') - 1);
        return trimmedCodeLine.substring(indexOfSecondToLastSpaceCharacter + 1, indexOfLastSpaceCharacter);
    }

    private String getCodeLineTrimmedOnNameEnd() {
        int delimiterCharacterIndex = (this.line.indexOf('=') != -1 ? this.line.indexOf('=') : this.line.indexOf(';'));
        return this.line.substring(0, delimiterCharacterIndex).trim();
    }
}
