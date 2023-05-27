package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import com.simmondobber.lomboker.lombokize.exceptions.BlankClassCodeException;
import com.simmondobber.lomboker.lombokize.helpers.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.helpers.extractors.MethodsExtractor;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LombokizeService {

    private final BoilerplateCleaner boilerplateCleaner;
    private final MethodsExtractor methodsExtractor;

    public LombokizeService() {
        this.boilerplateCleaner = new BoilerplateCleaner();
        this.methodsExtractor = new MethodsExtractor();
    }

    public LombokizedCodeTO lombokize(CodeToLombokizeTO codeToLombokizeTO) {
        String classCode = codeToLombokizeTO.getCodeToLombokize();
        validateClassCode(classCode);
        classCode = standardizeClassCodeWhitespaces(classCode);
        List<ClassMethod> gettersAndSetters = this.methodsExtractor.getGettersAndSetterContainedByClass(classCode);
        classCode = this.boilerplateCleaner.clearClassCodeFromBoilerPlate(classCode, gettersAndSetters);
        return new LombokizedCodeTO(classCode);
    }

    private void validateClassCode(String classCode) {
        if (!StringUtils.isNotBlank(classCode)) {
            throw new BlankClassCodeException();
        }
    }

    private String standardizeClassCodeWhitespaces(String classCode) {
        return classCode.replaceAll("\t", "    ");
    }
}
