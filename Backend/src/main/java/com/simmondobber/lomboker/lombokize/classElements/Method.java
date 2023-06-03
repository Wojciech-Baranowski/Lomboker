package com.simmondobber.lomboker.lombokize.classElements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Method {

    private String methodCode;
    private String correspondingFieldCode;
    private MethodType methodType;
}
