import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CodeToLombokizeTO} from "./transportObjects/CodeToLombokizeTO";
import {LombokizedCodeTO} from "./transportObjects/LombokizedCodeTO";

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
    globalGetter: boolean;
    globalSetter: boolean;

    constructor(private http: HttpClient) {
        this.codeToLombokize = "";
        this.lombokizedCode = "";
        this.indentType = 0;
        this.thisPrefix = false;
        this.globalGetter = false;
        this.globalSetter = false;
    }

    ngOnInit(): void {

    }

    lombokize(): void {
        let lombokizeTO: CodeToLombokizeTO = new CodeToLombokizeTO(this.codeToLombokize, this.indentType, this.thisPrefix, this.globalGetter, this.globalSetter);
        this.http.post<LombokizedCodeTO>(this.LOMBOKIZE_URL, lombokizeTO).subscribe((lombokizedCodeTO: LombokizedCodeTO) => {
            this.lombokizedCode = lombokizedCodeTO.lombokizedCode;
        }, (error) => {
            console.log(error)
        })
    }
}
