"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var user_service_1 = require('../services/user.service');
var login_service_1 = require('../services/login.service');
var router_1 = require('@angular/router');
var session_service_1 = require('../services/session.service');
var facebook_login_component_1 = require('./facebook.login.component');
var google_login_component_1 = require('./google.login.component');
var LoginComponent = (function () {
    function LoginComponent(userService, loginService, router, sessionService) {
        this.userService = userService;
        this.loginService = loginService;
        this.router = router;
        this.sessionService = sessionService;
        this.message = "";
        this.rememberMe = false;
        this.userLoginModel = { username: "", password: "" };
        //        this.userLoginModel = this.userService.getUserLoginModelFromCookie();
        //this.rememberMe = this.userLoginModel.username != null && this.userLoginModel.username != "";
    }
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'login',
            directives: [facebook_login_component_1.FacebookLoginComponent, google_login_component_1.GoogleLoginComponent],
            templateUrl: '../views/login.component.html'
        }),
        core_1.Injectable(), 
        __metadata('design:paramtypes', [user_service_1.UserService, login_service_1.LoginService, router_1.Router, session_service_1.SessionService])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map