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
    forceAnnotations: boolean;
    codeToLombokize: string;
    lombokizedCode: string;

    constructor(private http: HttpClient) {
        this.forceAnnotations = false;
        this.codeToLombokize = "";
        this.lombokizedCode = "";
    }

    ngOnInit(): void {

    }

    lombokize(): void {
        let lombokizeTO: CodeToLombokizeTO = new CodeToLombokizeTO(this.codeToLombokize, this.forceAnnotations);
        this.http.post<LombokizedCodeTO>(this.LOMBOKIZE_URL, lombokizeTO).subscribe((lombokizedCodeTO: LombokizedCodeTO) => {
            this.lombokizedCode = lombokizedCodeTO.lombokizedCode;
        }, (error) => {
            console.log(error)
        })
    }
}
