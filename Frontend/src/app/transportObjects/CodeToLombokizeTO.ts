import {AnnotationsConfig} from "./AnnotationsConfig";

export class CodeToLombokizeTO {

    codeToLombokize: string;
    indentType: number
    annotationsConfig: AnnotationsConfig;


    constructor(codeToLombokize: string, indentType: number, globalGetter: boolean, globalSetter: boolean) {
        this.codeToLombokize = codeToLombokize;
        this.indentType = indentType;
        this.annotationsConfig = new AnnotationsConfig(globalGetter, globalSetter);
    }
}
