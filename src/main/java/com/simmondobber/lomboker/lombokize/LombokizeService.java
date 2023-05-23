package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.lombokize.codeElement.ClassMethod;
import com.simmondobber.lomboker.lombokize.exceptions.BlankClassCodeException;
import com.simmondobber.lomboker.lombokize.helpers.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.helpers.extractors.MethodsExtractor;
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

    public String lombokize(String classCode) {
        validateClassCode(classCode);
        classCode = standardizeClassCodeWhitespaces(classCode);
        List<ClassMethod> gettersAndSetters = this.methodsExtractor.getGettersAndSetterContainedByClass(classCode);
        return this.boilerplateCleaner.clearClassCodeFromBoilerPlate(classCode, gettersAndSetters);
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
