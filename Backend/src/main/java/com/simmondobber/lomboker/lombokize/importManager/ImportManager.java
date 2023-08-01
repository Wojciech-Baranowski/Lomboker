package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.lomboker.common.ImportKeywords;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.List;

public class ImportManager {

    private final ImportFactory importFactory;

    public ImportManager() {
        this.importFactory = new ImportFactory();
    }

    public void addAndReorganizeLombokImports(Ast ast, AnnotationsConfig annotationsConfig) {
        List<Import> importsContainedByFile = ast.getAstRoot().getImports();
        List<Import> lombokImportsBasedOnConfig = this.importFactory.createImportsBasedOnConfig(annotationsConfig, "\n");
        importsContainedByFile.removeIf(import_ -> ImportKeywords.contains(import_.getPath().getSyntax()));
        importsContainedByFile.addAll(lombokImportsBasedOnConfig);
    }
}
