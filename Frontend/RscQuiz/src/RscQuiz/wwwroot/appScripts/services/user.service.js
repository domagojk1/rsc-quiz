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
var core_2 = require('angular2-cookie/core');
var router_1 = require('@angular/router');
var UserService = (function () {
    function UserService(cookieService, router) {
        this.cookieService = cookieService;
        this.router = router;
        this.tokenKey = "token";
        this.userKey = "user";
        this.tokenExpiresKey = "tokenExpires";
        this.loginUsernameKey = "username";
        this.loginPasswordKey = "password";
    }
    UserService.prototype.loadUser = function () {
        this.user = this.getUserFromCookie();
        this.token = this.getTokenFromCookie();
        this.tokenExpires = this.getTokenExpiresFromCookie();
    };
    UserService.prototype.saveLogin = function (data) {
        this.user = data.userInfo;
        this.token = data.token;
        this.tokenExpires = data.tokenExpires;
        this.saveSessionCookies();
    };
    UserService.prototype.isLoggedIn = function () {
        return this.user != null;
    };
    UserService.prototype.logout = function () {
        this.token = "";
        this.user = null;
        this.removeSessionCookies();
        this.router.navigate(["login"]);
    };
    UserService.prototype.getTokenFromCookie = function () {
        return this.cookieService.get(this.tokenKey);
    };
    UserService.prototype.getTokenExpiresFromCookie = function () {
        return this.cookieService.getObject(this.tokenExpiresKey);
    };
    UserService.prototype.getUserFromCookie = function () {
        return this.cookieService.getObject(this.userKey);
    };
    UserService.prototype.saveSessionCookies = function () {
        this.cookieService.put(this.tokenKey, this.token);
        this.cookieService.putObject(this.userKey, this.user);
        this.cookieService.putObject(this.tokenExpiresKey, this.tokenExpires);
    };
    UserService.prototype.removeSessionCookies = function () {
        this.cookieService.remove(this.tokenKey);
        this.cookieService.remove(this.userKey);
        this.cookieService.remove(this.tokenExpiresKey);
    };
    UserService.prototype.getUserLoginModelFromCookie = function () {
        return {
            username: this.cookieService.get(this.loginUsernameKey),
            password: this.cookieService.get(this.loginPasswordKey)
        };
    };
    UserService.prototype.saveLoginCookies = function (userLoginModel) {
        this.cookieService.put(this.loginUsernameKey, userLoginModel.username);
        this.cookieService.put(this.loginPasswordKey, userLoginModel.password);
    };
    UserService.prototype.removeLoginCookies = function () {
        this.cookieService.remove(this.loginUsernameKey);
        this.cookieService.remove(this.loginPasswordKey);
    };
    UserService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [core_2.CookieService, router_1.Router])
    ], UserService);
    return UserService;
}());
exports.UserService = UserService;
//# sourceMappingURL=user.service.js.map