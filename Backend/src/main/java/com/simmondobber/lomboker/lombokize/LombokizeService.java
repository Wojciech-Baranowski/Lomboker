package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.lombokize.enums.IndentType;
import com.simmondobber.lomboker.lombokize.helpers.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.helpers.formatters.ClassCodeFormatter;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.springframework.stereotype.Service;

@Service
public class LombokizeService {

    private final ClassCodeFormatter classCodeFormatter;
    private final BoilerplateCleaner boilerplateCleaner;

    public LombokizeService() {
        this.classCodeFormatter = new ClassCodeFormatter();
        this.boilerplateCleaner = new BoilerplateCleaner();
    }

    public LombokizedCodeTO lombokize(CodeToLombokizeTO codeToLombokizeTO) {
        String classCode = codeToLombokizeTO.getCodeToLombokize();
        IndentType indentType = codeToLombokizeTO.getIndentType();
        classCode = this.classCodeFormatter.formatClassCodeIndentsToSpaces(classCode, indentType);
        classCode = this.boilerplateCleaner.clearClassCodeFromBoilerplate(classCode);
        classCode = this.classCodeFormatter.formatClassCodeIndentsFromSpaces(classCode, indentType);
        return new LombokizedCodeTO(classCode);
    }
}
