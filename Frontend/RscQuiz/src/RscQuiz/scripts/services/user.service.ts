import { Injectable } from '@angular/core';
import { User } from '../classes/user';
import { CookieService } from 'angular2-cookie/core';
import { LoginResponse } from '../interfaces/login.response';
import { Router } from '@angular/router';
import { UserSimpleModel } from '../interfaces/user.simple.model';

@Injectable()
export class UserService {
    token: string;
    user: User;
    tokenExpires: number;

    private tokenKey: string = "token";
    private userKey: string = "user";
    private tokenExpiresKey: string = "tokenExpires";

    private loginUsernameKey: string = "username";
    private loginPasswordKey: string = "password";

    constructor(private cookieService: CookieService, private router: Router) {
    }

    loadUser() {
        this.user = this.getUserFromCookie();
        this.token = this.getTokenFromCookie();
        this.tokenExpires = this.getTokenExpiresFromCookie();
    }

    saveLogin(data: LoginResponse) {
        this.user = data.userInfo;
        this.token = data.token;
        this.tokenExpires = data.tokenExpires;

        this.saveSessionCookies();
    }

    isLoggedIn(): boolean {
        return this.user != null;
    }

    logout() {
        this.token = "";
        this.user = null;

        this.removeSessionCookies();
        this.router.navigate(["login"]);
    }

    getTokenFromCookie(): string {
        return this.cookieService.get(this.tokenKey);
    }

    getTokenExpiresFromCookie(): number {
        return this.cookieService.getObject(this.tokenExpiresKey) as number;
    }

    getUserFromCookie(): User {
        return this.cookieService.getObject(this.userKey) as User;
    }

    saveSessionCookies() {
        this.cookieService.put(this.tokenKey, this.token);
        this.cookieService.putObject(this.userKey, this.user);
        this.cookieService.putObject(this.tokenExpiresKey, this.tokenExpires);
    }

    removeSessionCookies() {
        this.cookieService.remove(this.tokenKey);
        this.cookieService.remove(this.userKey);
        this.cookieService.remove(this.tokenExpiresKey);
    }
    
    getUserLoginModelFromCookie(): UserSimpleModel {
        return {
            username: this.cookieService.get(this.loginUsernameKey),
            password: this.cookieService.get(this.loginPasswordKey)
        };
    }

    saveLoginCookies(userLoginModel: UserSimpleModel) {
        this.cookieService.put(this.loginUsernameKey, userLoginModel.username);
        this.cookieService.put(this.loginPasswordKey, userLoginModel.password);
    }

    removeLoginCookies() {
        this.cookieService.remove(this.loginUsernameKey);
        this.cookieService.remove(this.loginPasswordKey);
    }
}