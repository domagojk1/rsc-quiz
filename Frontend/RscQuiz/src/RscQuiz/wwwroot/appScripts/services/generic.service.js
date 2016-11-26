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
var http_1 = require('@angular/http');
require('rxjs/add/operator/map');
var user_service_1 = require('./user.service');
var session_service_1 = require('./session.service');
var GenericService = (function () {
    function GenericService(userService, http, sessionService) {
        this.userService = userService;
        this.http = http;
        this.sessionService = sessionService;
        this.sessionService.initialize(this);
    }
    Object.defineProperty(GenericService, "API_BASE_URL", {
        get: function () { return "http://rsc2016quiz.azurewebsites.net/"; },
        enumerable: true,
        configurable: true
    });
    GenericService.prototype.getHeaders = function () {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        if (this.userService.isLoggedIn()) {
            headers.append('Authorization', 'Bearer ' + this.userService.token);
        }
        return headers;
    };
    GenericService.prototype.getObservableGet = function (path, checkingSession) {
        if (checkingSession === void 0) { checkingSession = true; }
        this.checkSession(checkingSession);
        return this.http.get(GenericService.API_BASE_URL + path, { headers: this.getHeaders() })
            .map(function (response) { return response.json(); });
    };
    GenericService.prototype.getObservablePost = function (path, object, checkingSession) {
        if (checkingSession === void 0) { checkingSession = true; }
        this.checkSession(checkingSession);
        return this.http.post(GenericService.API_BASE_URL + path, JSON.stringify(object), { headers: this.getHeaders() })
            .map(function (res) { return res.json(); });
    };
    GenericService.prototype.checkSession = function (checkingSession) {
        if (checkingSession)
            this.sessionService.checkSession(this);
    };
    GenericService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [user_service_1.UserService, http_1.Http, session_service_1.SessionService])
    ], GenericService);
    return GenericService;
}());
exports.GenericService = GenericService;
//# sourceMappingURL=generic.service.js.map