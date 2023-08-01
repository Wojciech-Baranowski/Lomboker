package com.simmondobber.lomboker.lombokize.importManager;

import com.simmondobber.ast.components.complexAstComponents.Import;
import com.simmondobber.ast.parser.complexComponentParser.ImportParser;
import com.simmondobber.lomboker.common.ImportKeywords;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.List;

public class ImportFactory {

    public List<Import> createImportsBasedOnConfig(AnnotationsConfig annotationsConfig, String separator) {
        List<Import> imports = new ArrayList<>();
        if (areMoreThanFourAnnotations(annotationsConfig)) {
            imports.add(createLombokAllImport(separator));
        } else {
            if (annotationsConfig.isGetter()) {
                imports.add(createGetterImport(separator));
            }
            if (annotationsConfig.isSetter()) {
                imports.add(createSetterImport(separator));
            }
            if (annotationsConfig.isNoArgsConstructor()) {
                imports.add(createNoArgsConstructorImport(separator));
            }
            if (annotationsConfig.isAllArgsConstructor()) {
                imports.add(createAllArgsConstructorImport(separator));
            }
            if (annotationsConfig.isBuilder()) {
                imports.add(createBuilderImport(separator));
            }
            if (annotationsConfig.isToString()) {
                imports.add(createToStringImport(separator));
            }
        }
        if (annotationsConfig.isSuperBuilder()) {
            imports.add(createSuperBuilderImport(separator));
        }
        imports.get(imports.size() - 1).getSemicolon().setBackSeparator("\n");
        addNewlineToTheLastImport(imports);
        return imports;
    }

    public Import createLombokAllImport(String separator) {
        String lombokAllImportCode = "import " + ImportKeywords.LOMBOK_ALL.getPath() + ";" + separator;
        return new ImportParser(lombokAllImportCode).parse();
    }

    public Import createLombokExperimentalAllImport(String separator) {
        String lombokExperimentalAllImportCode = "import " + ImportKeywords.LOMBOK_EXPERIMENTAL_ALL.getPath() + ";" + separator;
        return new ImportParser(lombokExperimentalAllImportCode).parse();
    }

    public Import createGetterImport(String separator) {
        String getterImportCode = "import " + ImportKeywords.GETTER.getPath() + ";" + separator;
        return new ImportParser(getterImportCode).parse();
    }

    public Import createSetterImport(String separator) {
        String setterImportCode = "import " + ImportKeywords.SETTER.getPath() + ";" + separator;
        return new ImportParser(setterImportCode).parse();
    }

    public Import createNoArgsConstructorImport(String separator) {
        String noArgsConstructorImportCode = "import " + ImportKeywords.NO_ARGS_CONSTRUCTOR.getPath() + ";" + separator;
        return new ImportParser(noArgsConstructorImportCode).parse();
    }

    public Import createAllArgsConstructorImport(String separator) {
        String allArgsConstructorImportCode = "import " + ImportKeywords.ALL_ARGS_CONSTRUCTOR.getPath() + ";" + separator;
        return new ImportParser(allArgsConstructorImportCode).parse();
    }

    public Import createBuilderImport(String separator) {
        String builderImportCode = "import " + ImportKeywords.BUILDER.getPath() + ";" + separator;
        return new ImportParser(builderImportCode).parse();
    }

    public Import createSuperBuilderImport(String separator) {
        String superBuilderImportCode = "import " + ImportKeywords.SUPER_BUILDER.getPath() + ";" + separator;
        return new ImportParser(superBuilderImportCode).parse();
    }

    public Import createToStringImport(String separator) {
        String toStringImportCode = "import " + ImportKeywords.TO_STRING.getPath() + ";" + separator;
        return new ImportParser(toStringImportCode).parse();
    }

    private boolean areMoreThanFourAnnotations(AnnotationsConfig annotationsConfig) {
        int numberOfAnnotations = 0;
        if (annotationsConfig.isGetter()) {
            numberOfAnnotations++;
        }
        if (annotationsConfig.isSetter()) {
            numberOfAnnotations++;
        }
        if (annotationsConfig.isNoArgsConstructor()) {
            numberOfAnnotations++;
        }
        if (annotationsConfig.isAllArgsConstructor()) {
            numberOfAnnotations++;
        }
        if (annotationsConfig.isBuilder()) {
            numberOfAnnotations++;
        }
        if (annotationsConfig.isToString()) {
            numberOfAnnotations++;
        }
        return numberOfAnnotations > 4;
    }

    private void addNewlineToTheLastImport(List<Import> imports) {
        if (!imports.isEmpty()) {
            Import lastImport = imports.get(imports.size() - 1);
            lastImport.setBackSeparator(lastImport.getBackSeparator() + "\n");
        }
    }
}
