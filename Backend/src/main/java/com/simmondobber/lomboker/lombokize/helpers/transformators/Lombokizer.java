package com.simmondobber.lomboker.lombokize.helpers.transformators;

import com.simmondobber.lomboker.lombokize.enums.IndentType;
import com.simmondobber.lomboker.lombokize.helpers.cleaners.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.helpers.extractors.classExtractor.ClassExtractor;
import com.simmondobber.lomboker.lombokize.helpers.formatters.CodeFormatter;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;

import java.util.List;

public class Lombokizer {

    private final BoilerplateCleaner boilerplateCleaner;
    private final CodeFormatter codeFormatter;
    private final ClassExtractor classExtractor;
    private final AnnotationsConfig annotationsConfig;
    private final boolean thisPrefix;
    private final IndentType indentType;

    public Lombokizer(CodeToLombokizeTO codeToLombokizeTO) {
        this.boilerplateCleaner = new BoilerplateCleaner();
        this.codeFormatter = new CodeFormatter();
        this.classExtractor = new ClassExtractor();
        this.annotationsConfig = codeToLombokizeTO.getAnnotationsConfig();
        this.thisPrefix = codeToLombokizeTO.isThisPrefix();
        this.indentType = codeToLombokizeTO.getIndentType();
    }

    public String lombokize(String codeToLombokize) {
        codeToLombokize = standardizeCodeFormat(codeToLombokize);
        codeToLombokize = clearClasses(codeToLombokize, false);
        codeToLombokize = this.codeFormatter.formatSpacesToIndents(codeToLombokize, this.indentType);
        return codeToLombokize;
    }

    private String standardizeCodeFormat(String codeToLombokize) {
        codeToLombokize = this.codeFormatter.formatIndentsToSpaces(codeToLombokize, this.indentType);
        codeToLombokize = this.codeFormatter.removeBaseIndents(codeToLombokize);
        return codeToLombokize;
    }

    public String clearClasses(String code, boolean innerClass) {
        List<String> classes = extractClasses(code, innerClass);
        for (String clazz : classes) {
            String formattedClass = indentIfInner(clazz, innerClass);
            String clearedClass = clearClass(clazz, innerClass);
            code = code.replace(formattedClass, clearedClass);
        }
        return this.boilerplateCleaner.clearClassCodeFromBoilerplateAndAddChosenAnnotations(code, this.annotationsConfig, this.thisPrefix);
    }

    private String clearClass(String code, boolean innerClass) {
        String clearedClass = clearClasses(code, true);
        return indentIfInner(clearedClass, innerClass);
    }

    private List<String> extractClasses(String code, boolean extractInnerClasses) {
        return extractInnerClasses ? this.classExtractor.extractInnerClasses(code) : this.classExtractor.extractOuterClasses(code);
    }

    private String indentIfInner(String clazz, boolean innerClass) {
        return innerClass ? this.codeFormatter.addBaseIndents(clazz) : clazz;
    }
}
