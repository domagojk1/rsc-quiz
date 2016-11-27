export interface ModalConfirmData {
    title: string;
    message: string;
    needSpecialConfirmation: boolean;
    specialConfirmationMessage?: string;
    onYes();
    onNo();
}