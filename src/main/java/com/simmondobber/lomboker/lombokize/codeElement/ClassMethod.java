package com.simmondobber.lomboker.lombokize.codeElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClassMethod {

    private String methodCode;
    private String correspondingFieldCode;
    private MethodType methodType;
}
