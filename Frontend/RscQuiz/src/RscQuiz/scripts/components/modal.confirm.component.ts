import { Component, Inject, Injectable } from '@angular/core';
import { ModalConfirmService } from '../services/modal.confirm.service';

@Component({
    selector: 'modal-confirm-component',
    templateUrl: '../views/modal.confirm.component.html'
})
@Injectable()
export class ModalConfirmComponent {

    forceChecked: boolean = false;

    constructor(private modalConfirmService: ModalConfirmService) { }

    modalVisible(): boolean {
        return this.modalConfirmService.modalVisible;
    }

    hideModal() {
        this.modalConfirmService.modalVisible = false;;
    }

    yes() {
        this.modalConfirmService.modalConfirmData.onYes();
        this.hideModal();
    }

    no() {
        this.modalConfirmService.modalConfirmData.onNo();
        this.hideModal();
    }
}