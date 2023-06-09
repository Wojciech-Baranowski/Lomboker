package com.simmondobber.lomboker.lombokize.helpers.transformators;

import com.simmondobber.lomboker.lombokize.enums.IndentType;
import com.simmondobber.lomboker.lombokize.helpers.cleaners.AnnotationCleaner;
import com.simmondobber.lomboker.lombokize.helpers.cleaners.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.helpers.cleaners.ImportCleaner;
import com.simmondobber.lomboker.lombokize.helpers.extractors.classExtractor.ClassExtractor;
import com.simmondobber.lomboker.lombokize.helpers.formatters.CodeFormatter;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;

import java.util.List;

public class Lombokizer {


    private final CodeFormatter codeFormatter;
    private final ClassExtractor classExtractor;
    private final BoilerplateCleaner boilerplateCleaner;
    private final ImportCleaner importCleaner;
    private final AnnotationCleaner annotationCleaner;
    private final AnnotationsConfig annotationsConfig;
    private final IndentType indentType;
    private final boolean thisPrefix;


    public Lombokizer(CodeToLombokizeTO codeToLombokizeTO) {
        this.codeFormatter = new CodeFormatter();
        this.classExtractor = new ClassExtractor();
        this.boilerplateCleaner = new BoilerplateCleaner();
        this.importCleaner = new ImportCleaner();
        this.annotationCleaner = new AnnotationCleaner();
        this.annotationsConfig = codeToLombokizeTO.getAnnotationsConfig();
        this.indentType = codeToLombokizeTO.getIndentType();
        this.thisPrefix = codeToLombokizeTO.isThisPrefix();
    }

    public String lombokize(String code) {
        code = this.codeFormatter.formatIndentsToSpaces(code, this.indentType);
        code = this.codeFormatter.removeBaseIndents(code);
        code = this.annotationCleaner.removeRedundantAnnotations(code, this.annotationsConfig);
        code = this.importCleaner.addLombokImports(code, this.annotationsConfig);
        code = clearClasses(code, false);
        code = this.codeFormatter.formatSpacesToIndents(code, this.indentType);
        return code;
    }

    public String clearClasses(String code, boolean innerClass) {
        List<String> classes = extractClasses(code, innerClass);
        for (String clazz : classes) {
            String formattedClass = indentClassIfInner(clazz, innerClass);
            String clearedClass = clearClass(clazz, innerClass);
            code = code.replace(formattedClass, clearedClass);
        }
        return clearClassIfInner(code, innerClass);
    }

    private String clearClass(String code, boolean innerClass) {
        String clearedClass = clearClasses(code, true);
        return indentClassIfInner(clearedClass, innerClass);
    }

    private List<String> extractClasses(String code, boolean extractInnerClasses) {
        return extractInnerClasses ? this.classExtractor.extractInnerClasses(code) : this.classExtractor.extractOuterClasses(code);
    }

    private String indentClassIfInner(String clazz, boolean innerClass) {
        return innerClass ? this.codeFormatter.addBaseIndents(clazz) : clazz;
    }

    private String clearClassIfInner(String code, boolean innerClass) {
        return innerClass ? this.boilerplateCleaner.clearClassCodeFromBoilerplateAndAddChosenAnnotations(code, this.annotationsConfig, this.thisPrefix) : code;
    }
}
