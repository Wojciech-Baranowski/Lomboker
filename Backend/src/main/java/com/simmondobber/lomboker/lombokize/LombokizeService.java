package com.simmondobber.lomboker.lombokize;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.common.AnnotationData;
import com.simmondobber.lomboker.lombokize.annotationManager.AnnotationManager;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.exceptions.LombokizeException;
import com.simmondobber.lomboker.lombokize.importManager.ImportManager;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LombokizeService {

    private final BoilerplateCleaner boilerplateCleaner;
    private final AnnotationManager annotationManager;
    private final ImportManager importManager;

    public LombokizeService() {
        this.boilerplateCleaner = new BoilerplateCleaner();
        this.annotationManager = new AnnotationManager();
        this.importManager = new ImportManager();
    }

    public LombokizedCodeTO lombokize(CodeToLombokizeTO codeToLombokize) {
        try {
            Ast ast = new Ast(codeToLombokize.getCodeToLombokize());
            List<AnnotationData> annotationsData = codeToLombokize.getAnnotationsConfig().getAnnotationsData();
            this.importManager.addAndReorganizeLombokImports(ast, annotationsData);
            this.annotationManager.cleanAndAddRequiredLombokAnnotations(ast, annotationsData);
            this.boilerplateCleaner.removeDefaultMethodsFromAst(ast);
            return new LombokizedCodeTO(ast.getCode());
        } catch (Exception e) {
            throw new LombokizeException("Lombokize process has failed.", e);
        }
    }
}
