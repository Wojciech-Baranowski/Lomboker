import {AnnotationDataTO} from "./AnnotationDataTO";

export class AnnotationsConfig {

    annotationsData: AnnotationDataTO[];
    actOnInnerClasses: boolean;
    ignoreMethodAnnotations: boolean;

    constructor(annotationsData: AnnotationDataTO[], actOnInnerClasses: boolean, ignoreMethodAnnotations: boolean) {
        this.annotationsData = annotationsData;
        this.actOnInnerClasses = actOnInnerClasses;
        this.ignoreMethodAnnotations = ignoreMethodAnnotations;
    }
}
