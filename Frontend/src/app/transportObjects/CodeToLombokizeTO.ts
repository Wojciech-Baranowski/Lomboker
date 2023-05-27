export class CodeToLombokizeTO {

    codeToLombokize: string;
    forceAnnotations: boolean;
    indentType: number

    constructor(codeToLombokize: string, forceAnnotations: boolean, indents: number) {
        this.codeToLombokize = codeToLombokize;
        this.forceAnnotations = forceAnnotations;
        this.indentType = indents;
    }
}
