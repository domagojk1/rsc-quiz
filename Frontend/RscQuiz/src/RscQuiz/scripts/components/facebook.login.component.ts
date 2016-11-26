import { Component, OnInit } from '@angular/core';
import { FacebookResponse } from '../classes/facebook.response';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';
import { UserService } from '../services/user.service';
import { SuccessError } from '../interfaces/success.error';
import { LoginResponse } from '../interfaces/login.response';
import { handleError } from '../functions/error.handler';

declare const FB: any;

@Component({
    selector: 'facebook-login',
    templateUrl: '../views/facebook.login.component.html'
})
export class FacebookLoginComponent implements OnInit {
    response: FacebookResponse;
    loged: boolean = false;
    user = { name: 'Hello' };
    message: string = "";
    success: boolean;

    constructor(private userService: UserService,
        private loginService: LoginService,
        private router: Router,
        private sessionService: SessionService) {
    }

    statusChangeCallback(response: FacebookResponse) {
        if (response.status === 'connected') {
            this.response = response;
            console.log('connected');
            this.login();
        } else {
            this.loginFB();
        }
    }

    loginFB() {
        FB.login((result: FacebookResponse ) => {
            this.loged = true;
            this.response = result;
            if (result.status === 'connected') {
                this.login();
            }
        }, { scope: 'user_friends' });
    }

    ngOnInit() {
        FB.init({
            appId: '552017744992396',
            cookie: false,
            xfbml: true,  // parse social plugins on this page
            version: 'v2.5' // use graph api version 2.5
        });

        FB.getLoginStatus(response => {
            this.statusChangeCallback(response);
        });
    }

    successErrorLogin: SuccessError = {
        onSuccess: (data: LoginResponse) => {
            data.userInfo = { id: this.response.authResponse.userID, username: this.response.authResponse.accessToken }
            this.message = data.message;
            this.success = data.token != null;
            if (this.success) {
                this.userService.saveLogin(data);
                this.sessionService.userLoggedIn();
                setTimeout(() =>
                    this.router.navigate(["quizes"])
                    , 1000);
            }
        },
        onError: error => handleError(error)
    }

    login() {
        this.loginService.login({ token: this.response.authResponse.accessToken }, this.successErrorLogin);
    }
}