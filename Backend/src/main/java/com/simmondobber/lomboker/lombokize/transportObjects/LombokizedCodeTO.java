package com.simmondobber.lomboker.lombokize.transportObjects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
public class LombokizedCodeTO {

    private String lombokizedCode;
}
