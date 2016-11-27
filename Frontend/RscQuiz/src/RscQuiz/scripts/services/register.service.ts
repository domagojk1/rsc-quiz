import { Injectable } from '@angular/core';
import { GenericService} from './generic.service';
import { UserSimpleModel } from '../interfaces/user.simple.model';
import { SuccessError } from '../interfaces/success.error';
import { GenericResponse } from '../interfaces/generic.response';

@Injectable()
export class RegisterService {

    constructor(private genericService: GenericService) { }

    register(userSimpleModel: UserSimpleModel, successError: SuccessError) {
        this.genericService.getObservablePost<GenericResponse>("api/users/register/", userSimpleModel)
            .subscribe(successError.onSuccess, successError.onError);
    }
}