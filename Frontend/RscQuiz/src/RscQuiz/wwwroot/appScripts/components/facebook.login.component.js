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
var login_service_1 = require('../services/login.service');
var router_1 = require('@angular/router');
var session_service_1 = require('../services/session.service');
var user_service_1 = require('../services/user.service');
var error_handler_1 = require('../functions/error.handler');
var FacebookLoginComponent = (function () {
    function FacebookLoginComponent(userService, loginService, router, sessionService) {
        var _this = this;
        this.userService = userService;
        this.loginService = loginService;
        this.router = router;
        this.sessionService = sessionService;
        this.loged = false;
        this.user = { name: 'Hello' };
        this.message = "";
        this.successErrorLogin = {
            onSuccess: function (data) {
                data.userInfo = { id: _this.response.authResponse.userID, username: _this.response.authResponse.accessToken };
                _this.message = data.message;
                _this.success = data.token != null;
                if (_this.success) {
                    _this.userService.saveLogin(data);
                    _this.sessionService.userLoggedIn();
                    setTimeout(function () {
                        return _this.router.navigate(["quizes"]);
                    }, 1000);
                }
            },
            onError: function (error) { return error_handler_1.handleError(error); }
        };
    }
    FacebookLoginComponent.prototype.statusChangeCallback = function (response) {
        if (response.status === 'connected') {
            this.response = response;
            console.log('connected');
            this.login();
        }
        else {
            this.loginFB();
        }
    };
    FacebookLoginComponent.prototype.loginFB = function () {
        var _this = this;
        FB.login(function (result) {
            _this.loged = true;
            _this.response = result;
            if (result.status === 'connected') {
                _this.login();
            }
        }, { scope: 'user_friends' });
    };
    FacebookLoginComponent.prototype.ngOnInit = function () {
        var _this = this;
        FB.init({
            appId: '552017744992396',
            cookie: false,
            xfbml: true,
            version: 'v2.5' // use graph api version 2.5
        });
        FB.getLoginStatus(function (response) {
            _this.statusChangeCallback(response);
        });
    };
    FacebookLoginComponent.prototype.login = function () {
        this.loginService.login({ token: this.response.authResponse.accessToken }, this.successErrorLogin);
    };
    FacebookLoginComponent = __decorate([
        core_1.Component({
            selector: 'facebook-login',
            templateUrl: '../views/facebook.login.component.html'
        }), 
        __metadata('design:paramtypes', [user_service_1.UserService, login_service_1.LoginService, router_1.Router, session_service_1.SessionService])
    ], FacebookLoginComponent);
    return FacebookLoginComponent;
}());
exports.FacebookLoginComponent = FacebookLoginComponent;
//# sourceMappingURL=facebook.login.component.js.map