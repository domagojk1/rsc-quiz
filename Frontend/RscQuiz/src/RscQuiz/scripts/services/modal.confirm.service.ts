import { Injectable } from '@angular/core';
import { ModalConfirmData } from '../interfaces/modal.confirm.data';

@Injectable()
export class ModalConfirmService {

    modalVisible: boolean = false;
    needSpecialConfirmation: boolean;

    title: string;
    message: string;
    specialConfirmationMessage: string;

    modalConfirmData : ModalConfirmData;

    constructor() {
    }

    showModal(modalConfirmData: ModalConfirmData) {
        this.modalConfirmData = modalConfirmData;
        this.title = modalConfirmData.title;
        this.message = modalConfirmData.message;
        this.needSpecialConfirmation = modalConfirmData.needSpecialConfirmation;
        this.specialConfirmationMessage = modalConfirmData.specialConfirmationMessage;
        this.modalVisible = true;
    }
}