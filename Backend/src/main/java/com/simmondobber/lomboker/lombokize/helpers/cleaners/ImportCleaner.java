package com.simmondobber.lomboker.lombokize.helpers.cleaners;

import com.simmondobber.lomboker.lombokize.helpers.factories.ImportFactory;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;
import org.apache.commons.lang3.StringUtils;

public class ImportCleaner {

    private static final String LOMBOK_IMPORT_PREFIX = "import lombok";
    private final ImportFactory importFactory;

    public ImportCleaner() {
        this.importFactory = new ImportFactory();
    }

    public String addLombokImports(String classCode, AnnotationsConfig annotationsConfig) {
        String imports = this.importFactory.createLombokImports(classCode, annotationsConfig);
        if(StringUtils.isNotEmpty(imports)) {
            classCode = mergeImportsIntoClassCode(classCode, imports);
        }
        return classCode;
    }

    private String mergeImportsIntoClassCode(String classCode, String imports) {
        int lombokerImportsIndexInCode = getLombokerImportsIndexInCode(classCode);
        String partBeforeImports = classCode.substring(0, lombokerImportsIndexInCode);
        String optionalEmptyLineBeforeImports = classCode.charAt(lombokerImportsIndexInCode) == '\n' ? "\n" : "";
        String partAfterImports = classCode.substring(Math.max(lombokerImportsIndexInCode - 1, 0));
        return partBeforeImports + optionalEmptyLineBeforeImports + imports + partAfterImports;
    }

    private int getLombokerImportsIndexInCode(String classCode) {
        int lombokerImportsIndexInCode = classCode.indexOf(LOMBOK_IMPORT_PREFIX);
        int allImportsIndexInCode = classCode.indexOf("\n") + 1;
        return Math.max(lombokerImportsIndexInCode, allImportsIndexInCode);
    }
}
