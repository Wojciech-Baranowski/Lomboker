package com.simmondobber.lomboker.lombokize.helpers.factories;

import com.simmondobber.lomboker.lombokize.enums.Annotations;
import com.simmondobber.lomboker.lombokize.transportObjects.AnnotationsConfig;

public class AnnotationFactory {

    public String createAnnotations(AnnotationsConfig annotationsConfig) {
        StringBuilder annotations = new StringBuilder();
        if (annotationsConfig.isGlobalGetter()) {
            annotations.append(createGlobalGetter());
        }
        if (annotationsConfig.isGlobalSetter()) {
            annotations.append(createGlobalSetter());
        }
        return annotations.toString();
    }

    private String createGlobalGetter() {
        return Annotations.GETTER.getKeyword() + "\n";
    }

    private String createGlobalSetter() {
        return Annotations.SETTER.getKeyword() + "\n";
    }

}
