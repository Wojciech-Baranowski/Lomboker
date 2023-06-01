import {AnnotationsConfig} from "./AnnotationsConfig";

export class CodeToLombokizeTO {

    codeToLombokize: string;
    indentType: number
    thisPrefix: boolean
    annotationsConfig: AnnotationsConfig;


    constructor(codeToLombokize: string, indentType: number, thisPrefix: boolean, globalGetter: boolean, globalSetter: boolean) {
        this.codeToLombokize = codeToLombokize;
        this.indentType = indentType;
        this.thisPrefix = thisPrefix;
        this.annotationsConfig = new AnnotationsConfig(globalGetter, globalSetter);
    }
}
