package com.simmondobber.lomboker.lombokize;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.lombokize.annotationAdder.AnnotationAdder;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.exceptions.LombokizeException;
import com.simmondobber.lomboker.lombokize.importAdder.ImportAdder;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.springframework.stereotype.Service;

@Service
public class LombokizeService {

    private final BoilerplateCleaner boilerplateCleaner;
    private final AnnotationAdder annotationAdder;
    private final ImportAdder importAdder;

    public LombokizeService() {
        this.boilerplateCleaner = new BoilerplateCleaner();
        this.annotationAdder = new AnnotationAdder();
        this.importAdder = new ImportAdder();
    }

    public LombokizedCodeTO lombokize(CodeToLombokizeTO codeToLombokize) {
        try {
            Ast ast = new Ast(codeToLombokize.getCodeToLombokize());
            AnnotationsConfig annotationsConfig = codeToLombokize.getAnnotationsConfig();
            this.annotationAdder.addAnnotations(ast, annotationsConfig);
            this.importAdder.addImports(ast, annotationsConfig);
            this.boilerplateCleaner.removeRedundantMethods(ast);
            return new LombokizedCodeTO(ast.getCode());
        } catch (Exception e) {
            throw new LombokizeException("Lombokize process has failed.", e);
        }
    }
}
