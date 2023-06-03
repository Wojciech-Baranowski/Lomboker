package com.simmondobber.lomboker.lombokize.helpers.extractors.fieldExtractor;

public class FieldNameExtractor {

    public String extractClassFieldNameFromCodeLine(String line) {
        String trimmedCodeLine = trimCodeLineAtEndOfName(line);
        int indexOfLastSpaceCharacter = trimmedCodeLine.lastIndexOf(' ');
        return trimmedCodeLine.substring(indexOfLastSpaceCharacter + 1);
    }

    private String trimCodeLineAtEndOfName(String line) {
        int delimiterCharacterIndex = (line.indexOf('=') != -1 ? line.indexOf('=') : line.indexOf(';'));
        return line.substring(0, delimiterCharacterIndex).trim();
    }
}
