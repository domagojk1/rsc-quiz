import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { UserService } from './user.service';
import { SessionService } from './session.service';

@Injectable()
export class GenericService {

    private static get API_BASE_URL(): string { return "http://rsc2016quiz.azurewebsites.net/"; }

    constructor(
        private userService: UserService,
        private http: Http,
        private sessionService: SessionService) {
        this.sessionService.initialize(this);
    }

    getHeaders(): Headers {
        var headers: Headers = new Headers();
        headers.append('Content-Type', 'application/json');
        if (this.userService.isLoggedIn()) {
            headers.append('Authorization', 'Bearer ' + this.userService.token);
        }
        return headers;
    }

    getObservableGet<T>(path: string, checkingSession = true): Observable<T> {
        this.checkSession(checkingSession);
        return this.http.get(
            GenericService.API_BASE_URL + path,
            { headers: this.getHeaders() })
            .map((response: Response) => <T>response.json())
    }
    getObservablePost<R>(path: string, object: any, checkingSession = true): Observable<R> {
        this.checkSession(checkingSession);
        return this.http.post(
            GenericService.API_BASE_URL + path,
            JSON.stringify(object),
            { headers: this.getHeaders() })
            .map((res: Response) => <R>res.json())
    }

    checkSession(checkingSession: boolean) {
        if (checkingSession)
            this.sessionService.checkSession(this);
    }
}