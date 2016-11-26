import { Component, Inject, Injectable } from '@angular/core';
import { UserSimpleModel } from '../interfaces/user.simple.model';
import { handleError } from '../functions/error.handler'
import { SuccessError } from '../interfaces/success.error';
import { GenericResponse } from '../interfaces/generic.response';
import { RegisterService } from '../services/register.service';
@Component({
    selector: 'register',
    templateUrl: '../views/register.component.html',
    providers: [RegisterService]
})
@Injectable()
export class RegisterComponent {
    message: string = "";
    success: boolean;
    userRegisterModel: UserSimpleModel = { username: "", password: "", email: "", passwordRepeated: "" };

    constructor(private registerService: RegisterService) {
    }

    successErrorRegister: SuccessError = {
        onSuccess: (data: GenericResponse) => {
            this.message = data.message;
            this.success = data.status;
        },
        onError: error => handleError(error)
    }

    register() {
        if (this.userRegisterModel.password == this.userRegisterModel.passwordRepeated) {
            this.registerService.register(this.userRegisterModel, this.successErrorRegister);
        }
        else {
            this.message = "Passwords do not match!";
            this.success = false;
        }
    }
}