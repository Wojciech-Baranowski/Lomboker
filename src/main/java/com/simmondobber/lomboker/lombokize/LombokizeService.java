package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.codeLine.ClassField;
import com.simmondobber.lomboker.codeLine.CodeLine;
import com.simmondobber.lomboker.codeLine.CodeLineHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LombokizeService {

    private final CodeLineHelper codeLineHelper;

    @Autowired
    public LombokizeService(CodeLineHelper codeLineHelper) {
        this.codeLineHelper = codeLineHelper;
    }

    public String lombokize(String classCode) {
        List<CodeLine> classCodeLines = this.codeLineHelper.mapClassCodeToCodeLines(classCode);
        List<ClassField> classFields = this.codeLineHelper.filterAndMapCodeLinesToClassFields(classCodeLines);
        return this.codeLineHelper.mapClassFieldsToClassCode(classFields);
    }
}
