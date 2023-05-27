package com.simmondobber.lomboker.lombokize.transportObjects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
public class CodeToLombokizeTO {

    private String codeToLombokize;
    private boolean forceAnnotations;
}
