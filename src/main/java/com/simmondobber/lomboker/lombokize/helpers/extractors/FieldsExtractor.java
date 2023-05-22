package com.simmondobber.lomboker.lombokize.helpers.extractors;

import com.simmondobber.lomboker.lombokize.codeElement.ClassField;
import com.simmondobber.lomboker.lombokize.codeElement.CodeLine;
import com.simmondobber.lomboker.lombokize.helpers.factories.ClassFieldFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static com.simmondobber.lomboker.common.Keywords.STATIC;

public class FieldsExtractor {

    private final ClassFieldFactory classFieldFactory;

    public FieldsExtractor() {
        this.classFieldFactory = new ClassFieldFactory();
    }

    public List<ClassField> getClassFieldsFromClassCode(String classCode) {
        List<CodeLine> classCodeLines = mapClassCodeToCodeLines(classCode);
        List<CodeLine> classFieldsCodeLines = filterNonStaticClassFieldsFromCodeLines(classCodeLines);
        return createClassFieldsFromCodeLines(classFieldsCodeLines);
    }

    private List<CodeLine> mapClassCodeToCodeLines(String classCode) {
        return Arrays.stream(classCode.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(CodeLine::new)
                .toList();
    }

    private List<CodeLine> filterNonStaticClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return codeLines.stream()
                .filter(CodeLine::isClassField)
                .filter(this::isClassFieldNonStatic)
                .toList();
    }

    private List<ClassField> createClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return codeLines.stream()
                .map(CodeLine::getLine)
                .map(this.classFieldFactory::createClassField)
                .toList();
    }

    private boolean isClassFieldNonStatic(CodeLine classField) {
        return !classField.getLine().contains(" " + STATIC.getKeyword() + " ");
    }
}
