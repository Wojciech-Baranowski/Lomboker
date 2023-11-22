import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CodeToLombokizeTO} from "./transportObjects/CodeToLombokizeTO";
import {LombokizedCodeTO} from "./transportObjects/LombokizedCodeTO";
import {AnnotationsConfig} from "./transportObjects/AnnotationsConfig";
import {CookieService} from "ngx-cookie-service";
import {AnnotationDataTO} from "./transportObjects/AnnotationDataTO";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

    private LOMBOKIZE_URL: string = '/api/lombokize';
    private backgroundName: string;

    headerBackground: string;
    bodyBackground: string;
    sectionBackground: string;
    buttonBackground: string;
    codeToLombokize: string;
    lombokizedCode: string;

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

    constructor(private http: HttpClient, private cookieService: CookieService) {
        this.backgroundName = "";
        this.headerBackground = "";
        this.bodyBackground = "";
        this.sectionBackground = "";
        this.buttonBackground = ""
        this.codeToLombokize = "";
        this.lombokizedCode = "";

        this.getter = false;
        this.setter = false;
        this.noArgsConstructor = false;
        this.allArgsConstructor = false;
        this.builder = false;
        this.superBuilder = false;
        this.toBuilder = false;
        this.toString = false;
        this.callSuper = false;
        this.equalsAndHashCode = false;
    }

    lombokize(): void {
        let annotationsData: AnnotationDataTO[] = this.parseCheckboxesToEnumValues();
        let annotationsConfig: AnnotationsConfig = new AnnotationsConfig(annotationsData);
        let lombokizeTO: CodeToLombokizeTO = new CodeToLombokizeTO(this.codeToLombokize, annotationsConfig);
        this.http.post<LombokizedCodeTO>(this.LOMBOKIZE_URL, lombokizeTO).subscribe((lombokizedCodeTO: LombokizedCodeTO) => {
            this.lombokizedCode = lombokizedCodeTO.lombokizedCode;
        }, (error) => {
            console.log(error)
        })
    }

    ngOnInit(): void {
        this.initializeBackground();
    }

    private parseCheckboxesToEnumValues(): AnnotationDataTO[] {
        let annotationsData: AnnotationDataTO[] = [];
        if (this.getter) annotationsData.push(AnnotationDataTO.GETTER);
        if (this.setter) annotationsData.push(AnnotationDataTO.SETTER);
        if (this.noArgsConstructor) annotationsData.push(AnnotationDataTO.NO_ARGS_CONSTRUCTOR);
        if (this.allArgsConstructor) annotationsData.push(AnnotationDataTO.ALL_ARGS_CONSTRUCTOR);
        if (this.builder && !this.toBuilder) annotationsData.push(AnnotationDataTO.BUILDER);
        if (this.builder && this.toBuilder) annotationsData.push(AnnotationDataTO.BUILDER_TO_BUILDER);
        if (this.superBuilder && !this.toBuilder) annotationsData.push(AnnotationDataTO.SUPER_BUILDER);
        if (this.superBuilder && this.toBuilder) annotationsData.push(AnnotationDataTO.SUPER_BUILDER_TO_BUILDER);
        if (this.toString && !this.callSuper) annotationsData.push(AnnotationDataTO.TO_STRING);
        if (this.toString && this.callSuper) annotationsData.push(AnnotationDataTO.TO_STRING_CALL_SUPER);
        if (this.equalsAndHashCode) annotationsData.push(AnnotationDataTO.EQUALS_AND_HASH_CODE);
        return annotationsData;
    }

    private initializeBackground(): void {
        this.backgroundName = this.cookieService.get("background");
        if (this.backgroundName == "blue") {
            this.changeBackgroundToBlue();
        } else {
            this.changeBackgroundToCreamCookie();
        }
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

    changeBackgroundToCreamCookie(): void {
        this.cookieService.set("background", "creamCookie");
        this.headerBackground = "creamCookieHeaderBackground";
        this.bodyBackground = "creamCookieBodyBackground";
        this.sectionBackground = "creamCookieSectionBackground";
        this.buttonBackground = "creamCookieButtonBackground"
    }

    changeBackgroundToBlue(): void {
        this.cookieService.set("background", "blue");
        this.headerBackground = "blueHeaderBackground";
        this.bodyBackground = "blueBodyBackground";
        this.sectionBackground = "blueSectionBackground";
        this.buttonBackground = "blueButtonBackground"
    }
}
