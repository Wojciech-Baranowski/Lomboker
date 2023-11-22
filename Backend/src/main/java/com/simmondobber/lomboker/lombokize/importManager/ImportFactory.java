package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.parser.complexComponentParser.ImportParser;
import com.simmondobber.lomboker.common.AnnotationData;
import com.simmondobber.lomboker.common.ImportKeywordData;

import java.util.ArrayList;
import java.util.List;

public class ImportFactory {

    private static final List<AnnotationData> annotationsToIgnoreDuringStandardProcessing = List.of(AnnotationData.GETTER, AnnotationData.SETTER, AnnotationData.SUPER_BUILDER, AnnotationData.SUPER_BUILDER_TO_BUILDER);
    private static final List<AnnotationData> annotationsToIgnoreDuringCounting = List.of(AnnotationData.SUPER_BUILDER, AnnotationData.SUPER_BUILDER_TO_BUILDER);

    public List<Import> createImportsBasedOnAnnotationsData(List<AnnotationData> annotationsData, String separator) {
        List<Import> imports = new ArrayList<>();
        if (areMoreThanFourAnnotations(annotationsData)) {
            imports.add(createLombokAllImport(separator));
        } else {
            imports.add(createImportForAnnotation(AnnotationData.GETTER, separator));
            imports.add(createImportForAnnotation(AnnotationData.SETTER, separator));
            annotationsData.stream()
                    .filter(annotationData -> !annotationsToIgnoreDuringStandardProcessing.contains(annotationData))
                    .forEach(annotation -> imports.add(createImportForAnnotation(annotation, separator)));
        }
        if (annotationsData.contains(AnnotationData.SUPER_BUILDER) || annotationsData.contains(AnnotationData.SUPER_BUILDER_TO_BUILDER)) {
            imports.add(createImportForAnnotation(AnnotationData.SUPER_BUILDER, separator));
        }
        imports.get(imports.size() - 1).getSemicolon().setBackSeparator("\n");
        addNewlineToTheLastImport(imports);
        return imports;
    }

    public Import createLombokAllImport(String separator) {
        String lombokAllImportCode = "import " + ImportKeywordData.LOMBOK_ALL.getPath() + ";" + separator;
        return new ImportParser(lombokAllImportCode).parse();
    }

    public Import createImportForAnnotation(AnnotationData annotationData, String separator) {
        String getterImportCode = "import " + annotationData.getImportKeywordData().getPath() + ";" + separator;
        return new ImportParser(getterImportCode).parse();
    }

    private boolean areMoreThanFourAnnotations(List<AnnotationData> annotationsData) {
        int numberOfAnnotations = (int) annotationsData.stream()
                .filter(annotation -> !annotationsToIgnoreDuringCounting.contains(annotation))
                .count();
        return numberOfAnnotations > 4;
    }

    private void addNewlineToTheLastImport(List<Import> imports) {
        if (!imports.isEmpty()) {
            Import lastImport = imports.get(imports.size() - 1);
            lastImport.setBackSeparator(lastImport.getBackSeparator() + "\n");
        }
    }
}
