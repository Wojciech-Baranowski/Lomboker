package com.simmondobber.lomboker.lombokize.helpers.extractors.classExtractor;

import com.simmondobber.lomboker.lombokize.classElements.CodeLine;
import com.simmondobber.lomboker.lombokize.helpers.formatters.CodeFormatter;

import java.util.List;

public class ClassExtractor {

    private final ClassHeaderExtractor classHeaderExtractor;
    private final CodeFormatter codeFormatter;

    public ClassExtractor() {
        this.classHeaderExtractor = new ClassHeaderExtractor();
        this.codeFormatter = new CodeFormatter();
    }

    public List<String> extractOuterClasses(String code) {
        String formattedCode = this.codeFormatter.removeBaseIndents(code);
        return getClassStartIndices(formattedCode).stream()
                .map(startIndex -> extractClassFromCode(startIndex, formattedCode))
                .toList();
    }

    private List<Integer> getClassStartIndices(String code) {
        return this.classHeaderExtractor.getOuterClassHeaders(code).stream()
                .map(CodeLine::getLine)
                .map(code::indexOf)
                .toList();
    }

    private String extractClassFromCode(int classStartIndex, String code) {
        int classEndIndex = code.indexOf("\n}", classStartIndex) + 2;
        return code.substring(classStartIndex, classEndIndex);
    }

    public List<String> extractInnerClasses(String classCode) {
        String codeStripedOfOuterClass = stripClassCodeOfOuterClass(classCode);
        return extractOuterClasses(codeStripedOfOuterClass);
    }

    private String stripClassCodeOfOuterClass(String classCode) {
        int secondLineIndex = classCode.indexOf("\n") + 1;
        int secondToLastLineIndex = classCode.lastIndexOf("}\n") + 1;
        return classCode.substring(secondLineIndex, secondToLastLineIndex);
    }
}
