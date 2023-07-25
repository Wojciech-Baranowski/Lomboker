package com.simmondobber.lomboker.lombokize.annotationAdder;

import com.simmondobber.ast.components.complexAstComponents.Annotation;
import com.simmondobber.ast.parser.complexComponentParser.AnnotationParser;
import com.simmondobber.lomboker.common.AnnotationKeywords;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

import java.util.ArrayList;
import java.util.List;

public class AnnotationFactory {

    public List<Annotation> createClassAnnotationsBasedOnConfig(AnnotationsConfig annotationsConfig) {
        List<Annotation> annotations = new ArrayList<>();
        if (annotationsConfig.isGetter()) {
            annotations.add(createGetterAnnotation());
        }
        if (annotationsConfig.isSetter()) {
            annotations.add(createSetterAnnotation());
        }
        if (annotationsConfig.isNoArgsConstructor()) {
            annotations.add(createNoArgsConstructorAnnotation());
        }
        if (annotationsConfig.isAllArgsConstructor()) {
            annotations.add(createAllArgsConstructorAnnotation());
        }
        if (annotationsConfig.isBuilder()) {
            if (annotationsConfig.isToBuilder()) {
                annotations.add(createBuilderWithToBuilderAnnotation());
            } else {
                annotations.add(createBuilderAnnotation());
            }
        }
        if (annotationsConfig.isSuperBuilder()) {
            if (annotationsConfig.isToBuilder()) {
                annotations.add(createSuperBuilderWithToBuilderAnnotation());
            } else {
                annotations.add(createSuperBuilderAnnotation());
            }
        }
        if (annotationsConfig.isToString()) {
            if (annotationsConfig.isCallSuper()) {
                annotations.add(createToStringWithCallSuperAnnotation());
            } else {
                annotations.add(createToStringAnnotation());
            }
        }
        return annotations;
    }

    public List<Annotation> createMethodAnnotationsBasedOnConfig(AnnotationsConfig annotationsConfig) {
        List<Annotation> annotations = new ArrayList<>();
        if (!annotationsConfig.isGetter()) {
            annotations.add(createGetterAnnotation());
        }
        if (!annotationsConfig.isSetter()) {
            annotations.add(createSetterAnnotation());
        }
        return annotations;
    }

    public Annotation createGetterAnnotation() {
        String getterAnnotationCode = AnnotationKeywords.GETTER.getKeyword();
        return new AnnotationParser(getterAnnotationCode).parse();
    }

    public Annotation createSetterAnnotation() {
        String setterAnnotationCode = AnnotationKeywords.SETTER.getKeyword();
        return new AnnotationParser(setterAnnotationCode).parse();
    }

    public Annotation createNoArgsConstructorAnnotation() {
        String noArgsConstructorAnnotationCode = AnnotationKeywords.NO_ARGS_CONSTRUCTOR.getKeyword();
        return new AnnotationParser(noArgsConstructorAnnotationCode).parse();
    }

    public Annotation createAllArgsConstructorAnnotation() {
        String allArgsConstructorAnnotationCode = AnnotationKeywords.ALL_ARGS_CONSTRUCTOR.getKeyword();
        return new AnnotationParser(allArgsConstructorAnnotationCode).parse();
    }

    public Annotation createBuilderAnnotation() {
        String builderAnnotationCode = AnnotationKeywords.BUILDER.getKeyword();
        return new AnnotationParser(builderAnnotationCode).parse();
    }

    public Annotation createSuperBuilderAnnotation() {
        String superBuilderAnnotationCode = AnnotationKeywords.SUPER_BUILDER.getKeyword();
        return new AnnotationParser(superBuilderAnnotationCode).parse();
    }

    public Annotation createBuilderWithToBuilderAnnotation() {
        String builderWithToBuilderAnnotationCode = AnnotationKeywords.BUILDER.getKeyword() + AnnotationKeywords.TO_BUILDER.getKeyword();
        return new AnnotationParser(builderWithToBuilderAnnotationCode).parse();
    }

    public Annotation createSuperBuilderWithToBuilderAnnotation() {
        String superBuilderWithToBuilderAnnotationCode = AnnotationKeywords.SUPER_BUILDER.getKeyword() + AnnotationKeywords.TO_BUILDER.getKeyword();
        return new AnnotationParser(superBuilderWithToBuilderAnnotationCode).parse();
    }

    public Annotation createToStringAnnotation() {
        String toStringAnnotationCode = AnnotationKeywords.TO_STRING.getKeyword();
        return new AnnotationParser(toStringAnnotationCode).parse();
    }

    public Annotation createToStringWithCallSuperAnnotation() {
        String toStringWithCallSuperAnnotationCode = AnnotationKeywords.TO_STRING.getKeyword() + AnnotationKeywords.CALL_SUPER.getKeyword();
        return new AnnotationParser(toStringWithCallSuperAnnotationCode).parse();
    }
}
