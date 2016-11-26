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
var error_handler_1 = require('../functions/error.handler');
var register_service_1 = require('../services/register.service');
var RegisterComponent = (function () {
    function RegisterComponent(registerService) {
        var _this = this;
        this.registerService = registerService;
        this.message = "";
        this.userRegisterModel = { username: "", password: "", email: "", passwordRepeated: "" };
        this.successErrorRegister = {
            onSuccess: function (data) {
                _this.message = data.message;
                _this.success = data.status;
            },
            onError: function (error) { return error_handler_1.handleError(error); }
        };
    }
    RegisterComponent.prototype.register = function () {
        if (this.userRegisterModel.password == this.userRegisterModel.passwordRepeated) {
            this.registerService.register(this.userRegisterModel, this.successErrorRegister);
        }
        else {
            this.message = "Passwords do not match!";
            this.success = false;
        }
    };
    RegisterComponent = __decorate([
        core_1.Component({
            selector: 'register',
            templateUrl: '../views/register.component.html',
            providers: [register_service_1.RegisterService]
        }),
        core_1.Injectable(), 
        __metadata('design:paramtypes', [register_service_1.RegisterService])
    ], RegisterComponent);
    return RegisterComponent;
}());
exports.RegisterComponent = RegisterComponent;
//# sourceMappingURL=register.component.js.map