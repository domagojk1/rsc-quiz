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
var user_service_1 = require('./user.service');
var SessionService = (function () {
    function SessionService(userService) {
        var _this = this;
        this.userService = userService;
        this.workingInterval = 0;
        this.lastCheckedTime = new Date();
        this.successError = {
            onSuccess: function (data) {
                if (data.token != null) {
                    _this.userService.saveLogin(data);
                    _this.workingInterval = data.tokenExpires - new Date().valueOf();
                }
                else {
                    _this.logout();
                }
            },
            onError: function (error) {
                _this.logout();
            }
        };
    }
    SessionService.prototype.initialize = function (genericService) {
        this.userService.loadUser();
        this.checkSession(genericService);
    };
    SessionService.prototype.refreshToken = function (genericService) {
        genericService.getObservablePost("api/users/refreshToken", null, false)
            .subscribe(this.successError.onSuccess, this.successError.onError);
    };
    SessionService.prototype.checkSession = function (genericService) {
        var currentTime = new Date();
        if (this.userService.isLoggedIn()) {
            if (this.workingInterval == 0 ||
                (currentTime.valueOf() - this.lastCheckedTime.valueOf() < this.workingInterval
                    && currentTime.valueOf() - this.lastCheckedTime.valueOf() >= this.workingInterval * 0.9)) {
                this.refreshToken(genericService);
                this.lastCheckedTime = currentTime;
            }
            else if (currentTime.valueOf() - this.lastCheckedTime.valueOf() >= this.workingInterval) {
                this.logout();
            }
        }
    };
    SessionService.prototype.userLoggedIn = function () {
        this.workingInterval = this.userService.tokenExpires - new Date().valueOf();
    };
    SessionService.prototype.logout = function () {
        this.userService.logout();
    };
    SessionService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [user_service_1.UserService])
    ], SessionService);
    return SessionService;
}());
exports.SessionService = SessionService;
//# sourceMappingURL=session.service.js.map