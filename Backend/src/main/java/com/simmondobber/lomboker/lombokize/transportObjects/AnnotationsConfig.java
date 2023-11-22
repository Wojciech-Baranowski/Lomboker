package com.simmondobber.lomboker.lombokize.transportObjects;

import com.simmondobber.lomboker.common.AnnotationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationsConfig {

    private List<AnnotationData> annotationsData;

    public List<AnnotationData> parse() {
        return this.annotationsData.stream()
                .map(annotationTO -> AnnotationData.valueOf(annotationTO.name()))
                .toList();
    }
}
