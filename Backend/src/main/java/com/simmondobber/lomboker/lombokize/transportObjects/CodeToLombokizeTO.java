package com.simmondobber.lomboker.lombokize.transportObjects;

import com.simmondobber.lomboker.lombokize.enums.IndentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CodeToLombokizeTO {

    private String codeToLombokize;
    private IndentType indentType;
    private boolean thisPrefix;
    private AnnotationsConfig AnnotationsConfig;
}
