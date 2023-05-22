package com.simmondobber.lomboker.codeElement;

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
    private final ClassMethod correspondingGetter;
    private final ClassMethod correspondingSetter;

    public ClassField(String text) {
        super(text);
        this.fieldName = getClassFieldName();
        this.fieldType = getClassFieldType();
        this.correspondingGetter = getterFactory.createGetter(this);
        this.correspondingSetter = setterFactory.createSetter(this);
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

    public boolean isBoolean() {
        return this.fieldType.equals(BOOLEAN.getKeyword());
    }
}
