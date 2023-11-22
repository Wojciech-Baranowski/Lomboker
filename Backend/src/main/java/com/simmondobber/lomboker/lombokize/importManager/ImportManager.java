package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.Ast;
import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.lomboker.common.AnnotationData;
import com.simmondobber.lomboker.common.ImportKeywordData;

import java.util.List;

public class ImportManager {

    private final ImportFactory importFactory;

    public ImportManager() {
        this.importFactory = new ImportFactory();
    }

    public void addAndReorganizeLombokImports(Ast ast, List<AnnotationData> annotationsData) {
        List<Import> importsContainedByFile = ast.getAstRoot().getImports();
        List<Import> lombokImportsBasedOnConfig = this.importFactory.createImportsBasedOnAnnotationsData(annotationsData, "\n");
        importsContainedByFile.removeIf(import_ -> ImportKeywordData.contains(import_.getPath().getSyntax()));
        importsContainedByFile.addAll(lombokImportsBasedOnConfig);
    }
}
