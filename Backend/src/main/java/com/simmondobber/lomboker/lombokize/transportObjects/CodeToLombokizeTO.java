package com.simmondobber.lomboker.lombokize.transportObjects;

import com.simmondobber.lomboker.lombokize.enums.IndentType;
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
    private IndentType indentType;
}
