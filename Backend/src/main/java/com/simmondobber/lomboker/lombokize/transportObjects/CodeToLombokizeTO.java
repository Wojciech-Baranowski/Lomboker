package com.simmondobber.lomboker.lombokize.transportObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CodeToLombokizeTO {

    private String codeToLombokize;
    private AnnotationsConfig AnnotationsConfig;
}
