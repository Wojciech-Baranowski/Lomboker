export class AnnotationsConfig {

    getter: boolean;
    setter: boolean;
    noArgsConstructor: boolean;
    allArgsConstructor: boolean;
    builder: boolean;
    superBuilder: boolean;
    toBuilder: boolean;
    toString: boolean;
    callSuper: boolean;
    equalsAndHashCode: boolean;

    constructor(getter: boolean, setter: boolean, noArgsConstructor: boolean, allArgsConstructor: boolean, builder: boolean, superBuilder: boolean, toBuilder: boolean, toString: boolean, callSuper: boolean, equalsAndHashCode: boolean) {
        this.getter = getter;
        this.setter = setter;
        this.noArgsConstructor = noArgsConstructor;
        this.allArgsConstructor = allArgsConstructor;
        this.builder = builder;
        this.superBuilder = superBuilder;
        this.toBuilder = toBuilder;
        this.toString = toString;
        this.callSuper = callSuper;
        this.equalsAndHashCode = equalsAndHashCode;
    }
}
