package com.simmondobber.lomboker.codeElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClassMethod {

    private String methodCode;
    private ClassField correspondingField;
    private MethodType methodType;
}
