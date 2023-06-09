package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.enums.Import;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.StringJoiner;

public class ImportFactory {

    public String createLombokImports(String classCode, AnnotationsConfig annotationsConfig) {
        StringJoiner lombokImports = new StringJoiner("\n");
        if(!classCode.contains(Import.LOMBOK_ALL.getKeyword())) {
            if(annotationsConfig.isGetter() && !classCode.contains(Import.GETTER.getKeyword())) {
                lombokImports.add(Import.GETTER.getKeyword());
            }
            if(annotationsConfig.isSetter() && !classCode.contains(Import.SETTER.getKeyword())) {
                lombokImports.add(Import.SETTER.getKeyword());
            }
            if(annotationsConfig.isNoArgsConstructor() && !classCode.contains(Import.NO_ARGS_CONSTRUCTOR.getKeyword())) {
                lombokImports.add(Import.NO_ARGS_CONSTRUCTOR.getKeyword());
            }
            if(annotationsConfig.isAllArgsConstructor() && !classCode.contains(Import.ALL_ARGS_CONSTRUCTOR.getKeyword())) {
                lombokImports.add(Import.ALL_ARGS_CONSTRUCTOR.getKeyword());
            }
            if(annotationsConfig.isBuilder() && !classCode.contains(Import.BUILDER.getKeyword())) {
                lombokImports.add(Import.BUILDER.getKeyword());
            }
            if(annotationsConfig.isToString() && !classCode.contains(Import.TO_STRING.getKeyword())) {
                lombokImports.add(Import.TO_STRING.getKeyword());
            }
        }
        if(!classCode.contains(Import.LOMBOK_EXPERIMENTAL_ALL.getKeyword())) {
            if(annotationsConfig.isSuperBuilder() && !classCode.contains(Import.SUPER_BUILDER.getKeyword())) {
                lombokImports.add(Import.SUPER_BUILDER.getKeyword());
            }
        }
        return lombokImports.toString();
    }
}
