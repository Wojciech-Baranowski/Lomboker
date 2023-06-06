import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CodeToLombokizeTO} from "./transportObjects/CodeToLombokizeTO";
import {LombokizedCodeTO} from "./transportObjects/LombokizedCodeTO";
import {AnnotationsConfig} from "./transportObjects/AnnotationsConfig";
import {CookieService} from "ngx-cookie-service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

    private LOMBOKIZE_URL: string = 'http://localhost:3821/lombokize';
    private backgroundName: string;

    headerBackground: string;
    bodyBackground: string;
    sectionBackground: string;
    buttonBackground: string;
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

    constructor(private http: HttpClient, private cookieService: CookieService) {
        this.backgroundName = "";
        this.headerBackground = "";
        this.bodyBackground = "";
        this.sectionBackground = "";
        this.buttonBackground = ""
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

    lombokize(): void {
        let annotationsConfig: AnnotationsConfig = new AnnotationsConfig(this.getter, this.setter, this.noArgsConstructor, this.allArgsConstructor, this.builder, this.superBuilder, this.toBuilder, this.toString, this.callSuper);
        let lombokizeTO: CodeToLombokizeTO = new CodeToLombokizeTO(this.codeToLombokize, this.indentType, this.thisPrefix, annotationsConfig);
        this.http.post<LombokizedCodeTO>(this.LOMBOKIZE_URL, lombokizeTO).subscribe((lombokizedCodeTO: LombokizedCodeTO) => {
            this.lombokizedCode = lombokizedCodeTO.lombokizedCode;
        }, (error) => {
            console.log(error)
        })
    }

    ngOnInit(): void {
        this.initializeBackground();
        this.initializeOptions();
    }

    private initializeBackground(): void {
        this.backgroundName = this.cookieService.get("background");
        if (this.backgroundName == "blue") {
            this.changeBackgroundToBlue();
        } else {
            this.changeBackgroundToCreamCookie();
        }
    }

    private initializeOptions(): void {
        this.indentType = Number(this.cookieService.get("indent"));
        this.thisPrefix = Boolean(Number(this.cookieService.get("thisPrefix")));
    }

    setIndentTypeCookieToSpace(): void {
        this.cookieService.set("indent", "0");
    }

    setIndentTypeCookieToTabulator(): void {
        this.cookieService.set("indent", "1");
    }

    setThisPrefixCookie(): void {
        this.cookieService.set("thisPrefix", String(Number(!this.thisPrefix)));
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
