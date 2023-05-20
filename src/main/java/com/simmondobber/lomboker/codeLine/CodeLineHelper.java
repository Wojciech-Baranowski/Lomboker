package com.simmondobber.lomboker.codeLine;

import com.simmondobber.lomboker.codeFactory.SetterFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeLineHelper {

    public List<CodeLine> mapClassCodeToCodeLines(String classCode) {
        return Arrays.stream(classCode.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(CodeLine::new)
                .toList();
    }

    public String mapClassFieldsToClassCode(List<ClassField> classFields) {
        SetterFactory setterFactory = new SetterFactory();
        return classFields.stream()
                .map(setterFactory::createSetterCode)
                .collect(Collectors.joining("\n"));
    }

    public List<ClassField> filterAndMapCodeLinesToClassFields(List<CodeLine> codeLines) {
        List<CodeLine> classFieldsCodeLines = filterClassFieldsFromCodeLines(codeLines);
        return createClassFieldsFromCodeLines(classFieldsCodeLines);
    }

    private List<CodeLine> filterClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return codeLines.stream()
                .filter(CodeLine::isClassField)
                .toList();
    }

    private List<ClassField> createClassFieldsFromCodeLines(List<CodeLine> codeLines) {
        return getCodeLinesAsStrings(codeLines).stream()
                .map(ClassField::new)
                .toList();
    }

    private List<String> getCodeLinesAsStrings(List<CodeLine> codeLines) {
        return codeLines.stream()
                .map(CodeLine::getLine)
                .toList();
    }
}
