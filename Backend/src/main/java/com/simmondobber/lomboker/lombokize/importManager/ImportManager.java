package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.File;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.lomboker.common.ImportKeywords;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.List;

public class ImportManager {

    private final ImportFactory importFactory;

    public ImportManager() {
        this.importFactory = new ImportFactory();
    }

    public void addImports(Ast ast, AnnotationsConfig annotationsConfig) {
        List<Import> containedImports = ((File) ast.getAstRoot()).getImports();
        removeCurrentLombokerImports(containedImports);
        addLombokImports(containedImports, annotationsConfig);
    }

    private void removeCurrentLombokerImports(List<Import> imports) {
        imports.removeIf(import_ -> ImportKeywords.contains(import_.getPath().getFullSyntax().trim()));
    }

    private void addLombokImports(List<Import> containedImports, AnnotationsConfig annotationsConfig) {
        List<Import> lombokImports = this.importFactory.createImportsBasedOnConfig(annotationsConfig, "\n");
        containedImports.addAll(lombokImports);
    }
}
