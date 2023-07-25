package com.simmondobber.lomboker.lombokize.annotationAdder;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.parser.complexComponentParser.AnnotationParser;
import com.simmondobber.lomboker.common.AnnotationKeywords;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.List;

public class AnnotationFactory {

    public List<Annotation> createClassAnnotationsBasedOnConfig(AnnotationsConfig annotationsConfig, String separator) {
        List<Annotation> annotations = new ArrayList<>();
        if (annotationsConfig.isGetter()) {
            annotations.add(createGetterAnnotation(separator));
        }
        if (annotationsConfig.isSetter()) {
            annotations.add(createSetterAnnotation(separator));
        }
        if (annotationsConfig.isNoArgsConstructor()) {
            annotations.add(createNoArgsConstructorAnnotation(separator));
        }
        if (annotationsConfig.isAllArgsConstructor()) {
            annotations.add(createAllArgsConstructorAnnotation(separator));
        }
        if (annotationsConfig.isBuilder()) {
            if (annotationsConfig.isToBuilder()) {
                annotations.add(createBuilderWithToBuilderAnnotation(separator));
            } else {
                annotations.add(createBuilderAnnotation(separator));
            }
        }
        if (annotationsConfig.isSuperBuilder()) {
            if (annotationsConfig.isToBuilder()) {
                annotations.add(createSuperBuilderWithToBuilderAnnotation(separator));
            } else {
                annotations.add(createSuperBuilderAnnotation(separator));
            }
        }
        if (annotationsConfig.isToString()) {
            if (annotationsConfig.isCallSuper()) {
                annotations.add(createToStringWithCallSuperAnnotation(separator));
            } else {
                annotations.add(createToStringAnnotation(separator));
            }
        }
        return annotations;
    }

    public List<Annotation> createFieldAnnotationsBasedOnConfig(AnnotationsConfig annotationsConfig, String separator) {
        List<Annotation> annotations = new ArrayList<>();
        if (!annotationsConfig.isGetter()) {
            annotations.add(createGetterAnnotation(separator));
        }
        if (!annotationsConfig.isSetter()) {
            annotations.add(createSetterAnnotation(separator));
        }
        return annotations;
    }

    public Annotation createGetterAnnotation(String separator) {
        String getterAnnotationCode = AnnotationKeywords.GETTER.getKeyword() + separator;
        return new AnnotationParser(getterAnnotationCode).parse();
    }

    public Annotation createSetterAnnotation(String separator) {
        String setterAnnotationCode = AnnotationKeywords.SETTER.getKeyword() + separator;
        return new AnnotationParser(setterAnnotationCode).parse();
    }

    public Annotation createNoArgsConstructorAnnotation(String separator) {
        String noArgsConstructorAnnotationCode = AnnotationKeywords.NO_ARGS_CONSTRUCTOR.getKeyword() + separator;
        return new AnnotationParser(noArgsConstructorAnnotationCode).parse();
    }

    public Annotation createAllArgsConstructorAnnotation(String separator) {
        String allArgsConstructorAnnotationCode = AnnotationKeywords.ALL_ARGS_CONSTRUCTOR.getKeyword() + separator;
        return new AnnotationParser(allArgsConstructorAnnotationCode).parse();
    }

    public Annotation createBuilderAnnotation(String separator) {
        String builderAnnotationCode = AnnotationKeywords.BUILDER.getKeyword() + separator;
        return new AnnotationParser(builderAnnotationCode).parse();
    }

    public Annotation createSuperBuilderAnnotation(String separator) {
        String superBuilderAnnotationCode = AnnotationKeywords.SUPER_BUILDER.getKeyword() + separator;
        return new AnnotationParser(superBuilderAnnotationCode).parse();
    }

    public Annotation createBuilderWithToBuilderAnnotation(String separator) {
        String builderWithToBuilderAnnotationCode = AnnotationKeywords.BUILDER.getKeyword() + AnnotationKeywords.TO_BUILDER.getKeyword() + separator;
        return new AnnotationParser(builderWithToBuilderAnnotationCode).parse();
    }

    public Annotation createSuperBuilderWithToBuilderAnnotation(String separator) {
        String superBuilderWithToBuilderAnnotationCode = AnnotationKeywords.SUPER_BUILDER.getKeyword() + AnnotationKeywords.TO_BUILDER.getKeyword() + separator;
        return new AnnotationParser(superBuilderWithToBuilderAnnotationCode).parse();
    }

    public Annotation createToStringAnnotation(String separator) {
        String toStringAnnotationCode = AnnotationKeywords.TO_STRING.getKeyword() + "\n";
        return new AnnotationParser(toStringAnnotationCode).parse();
    }

    public Annotation createToStringWithCallSuperAnnotation(String separator) {
        String toStringWithCallSuperAnnotationCode = AnnotationKeywords.TO_STRING.getKeyword() + AnnotationKeywords.CALL_SUPER.getKeyword() + separator;
        return new AnnotationParser(toStringWithCallSuperAnnotationCode).parse();
    }
}
