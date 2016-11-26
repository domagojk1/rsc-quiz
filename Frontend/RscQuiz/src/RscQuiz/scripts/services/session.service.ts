import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { GenericService } from './generic.service';
import { SuccessError } from '../interfaces/success.error';
import { LoginResponse } from '../interfaces/login.response';

@Injectable()
export class SessionService {

    workingInterval: number = 0;
    lastCheckedTime: Date = new Date();

    constructor(private userService: UserService) {
    }

    initialize(genericService: GenericService) {
        this.userService.loadUser();
        this.checkSession(genericService);
    }

    successError: SuccessError = {
        onSuccess: (data: LoginResponse) => {
            if (data.token != null) {
                this.userService.saveLogin(data);
                this.workingInterval = data.tokenExpires - new Date().valueOf();                        
            }
            else {
                this.logout();
            }
        },
        onError: error => {
            this.logout();
        }
    }

    private refreshToken(genericService: GenericService) {
        genericService.getObservablePost<LoginResponse>("api/users/refreshToken", null, false)
            .subscribe(this.successError.onSuccess, this.successError.onError);
    }

    public checkSession(genericService: GenericService) {
        let currentTime: Date = new Date();
        if (this.userService.isLoggedIn()) {
            if (this.workingInterval == 0 ||
                (currentTime.valueOf() - this.lastCheckedTime.valueOf() < this.workingInterval
                    && currentTime.valueOf() - this.lastCheckedTime.valueOf() >= this.workingInterval * 0.9)
            ) {
                this.refreshToken(genericService);
                this.lastCheckedTime = currentTime;
            }
            else if (currentTime.valueOf() - this.lastCheckedTime.valueOf() >= this.workingInterval) {
                this.logout();
            }
        }
    }

    public userLoggedIn() {
        this.workingInterval = this.userService.tokenExpires - new Date().valueOf();                      
    }

    private logout() {
        this.userService.logout();
    }
}