import { Component, Inject, Injectable } from '@angular/core';
import { UserSimpleModel } from '../interfaces/user.simple.model';
import { handleError } from '../functions/error.handler';
import { UserService } from '../services/user.service';
import { LoginService } from '../services/login.service';
import { User } from '../classes/user';
import { SuccessError } from '../interfaces/success.error';
import { LoginResponse } from '../interfaces/login.response';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';
import { FacebookLoginComponent } from './facebook.login.component';
import { GoogleLoginComponent } from './google.login.component';

@Component({
    selector: 'login',
    directives: [FacebookLoginComponent, GoogleLoginComponent],
    templateUrl: '../views/login.component.html'
})
@Injectable()
export class LoginComponent {
    message: string = "";
    success: boolean;
    rememberMe: boolean = false;

    userLoginModel: UserSimpleModel = { username: "", password: "" };

    constructor(private userService: UserService,
        private loginService: LoginService,
        private router: Router,
        private sessionService: SessionService) {

//        this.userLoginModel = this.userService.getUserLoginModelFromCookie();
        //this.rememberMe = this.userLoginModel.username != null && this.userLoginModel.username != "";
    }
    /*
    successErrorLogin: SuccessError = {
        onSuccess: (data: LoginResponse) => {
            this.message = data.message;
            this.success = data.authenticated;
            if (data.authenticated) {
                this.userService.saveLogin(data);
                this.sessionService.userLoggedIn();
                setTimeout(() =>
                    this.router.navigate(["places"])
                    , 1000);
                if (this.rememberMe) {
                    this.userService.saveLoginCookies(this.userLoginModel);
                }
            }
        },
        onError: error => handleError(error)
    }
    login() {
        this.loginService.login(this.userLoginModel, this.successErrorLogin);
        if (this.rememberMe == false) {
            this.userService.removeLoginCookies();
        }
    }
    */
}