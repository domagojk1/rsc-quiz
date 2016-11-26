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
var modal_confirm_service_1 = require('../services/modal.confirm.service');
var ModalConfirmComponent = (function () {
    function ModalConfirmComponent(modalConfirmService) {
        this.modalConfirmService = modalConfirmService;
        this.forceChecked = false;
    }
    ModalConfirmComponent.prototype.modalVisible = function () {
        return this.modalConfirmService.modalVisible;
    };
    ModalConfirmComponent.prototype.hideModal = function () {
        this.modalConfirmService.modalVisible = false;
        ;
    };
    ModalConfirmComponent.prototype.yes = function () {
        this.modalConfirmService.modalConfirmData.onYes();
        this.hideModal();
    };
    ModalConfirmComponent.prototype.no = function () {
        this.modalConfirmService.modalConfirmData.onNo();
        this.hideModal();
    };
    ModalConfirmComponent = __decorate([
        core_1.Component({
            selector: 'modal-confirm-component',
            templateUrl: '../views/modal.confirm.component.html'
        }),
        core_1.Injectable(), 
        __metadata('design:paramtypes', [modal_confirm_service_1.ModalConfirmService])
    ], ModalConfirmComponent);
    return ModalConfirmComponent;
}());
exports.ModalConfirmComponent = ModalConfirmComponent;
//# sourceMappingURL=modal.confirm.component.js.map