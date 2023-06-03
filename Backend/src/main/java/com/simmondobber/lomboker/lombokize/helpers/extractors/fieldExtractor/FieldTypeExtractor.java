package com.simmondobber.lomboker.lombokize.helpers.extractors.fieldExtractor;

public class FieldTypeExtractor {

    public String extractClassFieldTypeFromCodeLine(String line) {
        String trimmedCodeLine = trimCodeLineAtEndOfName(line);
        int indexOfLastSpace = getIndexOfLastSpace(trimmedCodeLine);
        int indexOfSecondToLastSpace = getIndexOfSecondToLastSpace(trimmedCodeLine);
        return trimmedCodeLine.substring(indexOfSecondToLastSpace + 1, indexOfLastSpace);
    }

    private String trimCodeLineAtEndOfName(String line) {
        int delimiterCharacterIndex = (line.indexOf('=') != -1 ? line.indexOf('=') : line.indexOf(';'));
        return line.substring(0, delimiterCharacterIndex).trim();
    }

    private int getIndexOfLastSpace(String line) {
        return line.lastIndexOf(' ');
    }

    private int getIndexOfSecondToLastSpace(String line) {
        return line.lastIndexOf(' ', getIndexOfLastSpace(line) - 1);
    }
}
