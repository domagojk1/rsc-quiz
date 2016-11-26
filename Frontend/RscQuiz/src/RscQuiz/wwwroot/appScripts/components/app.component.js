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
var router_1 = require('@angular/router');
var quizes_component_1 = require('./quizes.component');
var login_component_1 = require('./login.component');
var register_component_1 = require('./register.component');
var home_component_1 = require('./home.component');
var user_component_1 = require('./user.component');
var facebook_login_component_1 = require('./facebook.login.component');
var google_login_component_1 = require('./google.login.component');
var user_service_1 = require('../services/user.service');
var core_2 = require('angular2-cookie/core');
var generic_service_1 = require('../services/generic.service');
var login_service_1 = require('../services/login.service');
var session_service_1 = require('../services/session.service');
var modal_confirm_service_1 = require('../services/modal.confirm.service');
var AppComponent = (function () {
    function AppComponent(userService, sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }
    AppComponent = __decorate([
        core_1.Component({
            selector: 'my-app',
            directives: [router_1.ROUTER_DIRECTIVES],
            templateUrl: '../views/app.component.html',
            precompile: [quizes_component_1.QuizesComponent, login_component_1.LoginComponent, register_component_1.RegisterComponent, home_component_1.HomeComponent, user_component_1.UserComponent, facebook_login_component_1.FacebookLoginComponent, google_login_component_1.GoogleLoginComponent],
            providers: [user_service_1.UserService, core_2.CookieService, generic_service_1.GenericService, login_service_1.LoginService, session_service_1.SessionService, modal_confirm_service_1.ModalConfirmService]
        }), 
        __metadata('design:paramtypes', [user_service_1.UserService, session_service_1.SessionService])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map