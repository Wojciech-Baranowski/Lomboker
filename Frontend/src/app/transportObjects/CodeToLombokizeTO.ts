export class CodeToLombokizeTO {

    codeToLombokize: string;
    forceAnnotations: boolean;

    constructor(codeToLombokize: string, forceAnnotations: boolean) {
        this.codeToLombokize = codeToLombokize;
        this.forceAnnotations = forceAnnotations;
    }
}
