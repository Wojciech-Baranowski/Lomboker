import {AnnotationsConfig} from "./AnnotationsConfig";

export class CodeToLombokizeTO {

    codeToLombokize: string;
    annotationsConfig: AnnotationsConfig;


    constructor(codeToLombokize: string, annotationsConfig: AnnotationsConfig) {
        this.codeToLombokize = codeToLombokize;
        this.annotationsConfig = annotationsConfig;
    }
}
