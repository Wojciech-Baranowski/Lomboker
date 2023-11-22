import {AnnotationDataTO} from "./AnnotationDataTO";

export class AnnotationsConfig {

    annotationsData: AnnotationDataTO[];
    actOnInnerClasses: boolean;

    constructor(annotationsData: AnnotationDataTO[], actOnInnerClasses: boolean) {
        this.annotationsData = annotationsData;
        this.actOnInnerClasses = actOnInnerClasses;
    }
}
