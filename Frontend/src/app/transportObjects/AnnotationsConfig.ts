export class AnnotationsConfig {

    globalGetter: boolean;
    globalSetter: boolean;

    constructor(globalGetter: boolean, globalSetter: boolean) {
        this.globalGetter = globalGetter;
        this.globalSetter = globalSetter;
    }
}
