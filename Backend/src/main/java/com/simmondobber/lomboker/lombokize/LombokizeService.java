package com.simmondobber.lomboker.lombokize;

import com.simmondobber.ast.Ast;
import com.simmondobber.lomboker.lombokize.boilerplateCleaner.BoilerplateCleaner;
import com.simmondobber.lomboker.lombokize.exceptions.LombokizeException;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.springframework.stereotype.Service;

@Service
public class LombokizeService {

    private final BoilerplateCleaner boilerplateCleaner;

    public LombokizeService() {
        this.boilerplateCleaner = new BoilerplateCleaner();
    }

    public LombokizedCodeTO lombokize(CodeToLombokizeTO codeToLombokize) {
        try {
            Ast ast = new Ast(codeToLombokize.getCodeToLombokize());
            AnnotationsConfig annotationsConfig = codeToLombokize.getAnnotationsConfig();
            this.boilerplateCleaner.removeRedundantMethods(ast, annotationsConfig);
            //addAnnotations(ast, codeToLombokize.getAnnotationsConfig());
            return new LombokizedCodeTO(ast.getCode());
        } catch (Exception e) {
            throw new LombokizeException("Lombokize process has failed.", e);
        }
    }


}
