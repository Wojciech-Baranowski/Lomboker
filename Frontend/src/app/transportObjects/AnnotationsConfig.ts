import {AnnotationDataTO} from "./AnnotationDataTO";

export class AnnotationsConfig {

    annotationsData: AnnotationDataTO[]

    constructor(annotationsData: AnnotationDataTO[]) {
        this.annotationsData = annotationsData;
    }
}
