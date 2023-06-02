import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CodeToLombokizeTO} from "./transportObjects/CodeToLombokizeTO";
import {LombokizedCodeTO} from "./transportObjects/LombokizedCodeTO";
import {AnnotationsConfig} from "./transportObjects/AnnotationsConfig";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    private LOMBOKIZE_URL = 'http://localhost:8080/lombokize';
    codeToLombokize: string;
    lombokizedCode: string;
    indentType: number;
    thisPrefix: boolean;
    getter: boolean;
    setter: boolean;
    noArgsConstructor: boolean;
    allArgsConstructor: boolean;
    builder: boolean;
    superBuilder: boolean;
    toBuilder: boolean;
    toString: boolean;
    callSuper: boolean;

    constructor(private http: HttpClient) {
        this.codeToLombokize = "";
        this.lombokizedCode = "";
        this.indentType = 0;
        this.thisPrefix = false;
        this.getter = false;
        this.setter = false;
        this.noArgsConstructor = false;
        this.allArgsConstructor = false;
        this.builder = false;
        this.superBuilder = false;
        this.toBuilder = false;
        this.toString = false;
        this.callSuper = false;
    }

    ngOnInit(): void {

    }

    lombokize(): void {
        let annotationsConfig: AnnotationsConfig = new AnnotationsConfig(this.getter, this.setter, this.noArgsConstructor, this.allArgsConstructor, this.builder, this.superBuilder, this.toBuilder, this.toString, this.callSuper);
        let lombokizeTO: CodeToLombokizeTO = new CodeToLombokizeTO(this.codeToLombokize, this.indentType, this.thisPrefix, annotationsConfig);
        this.http.post<LombokizedCodeTO>(this.LOMBOKIZE_URL, lombokizeTO).subscribe((lombokizedCodeTO: LombokizedCodeTO) => {
            this.lombokizedCode = lombokizedCodeTO.lombokizedCode;
        }, (error) => {
            console.log(error)
        })
    }

    excludeBuilder(): void {
        this.builder = false;
        if (this.superBuilder) {
            this.toBuilder = false;
        }
    }

    excludeSuperBuilder(): void {
        this.superBuilder = false;
        if (this.builder) {
            this.toBuilder = false;
        }
    }

    includeBuilderIfNoneChosen(): void {
        if (!this.builder && !this.superBuilder) {
            this.builder = true;
        }
    }

    excludeCallSuperIfToStringNotChosen(): void {
        if (this.toString) {
            this.callSuper = false;
        }
    }

    includeToStringIfNotChosen(): void {
        if (!this.toString) {
            this.toString = true;
        }
    }
}
