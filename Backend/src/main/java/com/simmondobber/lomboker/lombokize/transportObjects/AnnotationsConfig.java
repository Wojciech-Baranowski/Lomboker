package com.simmondobber.lomboker.lombokize.transportObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnnotationsConfig {

    private boolean getter;
    private boolean setter;
    private boolean noArgsConstructor;
    private boolean allArgsConstructor;
    private boolean builder;
    private boolean superBuilder;
    private boolean toBuilder;
    private boolean toString;
    private boolean callSuper;
    private boolean equalsAndHashCode;
}
