import { Component, Injectable } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { QuizesComponent } from './quizes.component';
import { LoginComponent } from './login.component';
import { RegisterComponent } from './register.component';
import { HomeComponent } from './home.component';
import { UserComponent } from './user.component';
import { FacebookLoginComponent } from './facebook.login.component';
import { GoogleLoginComponent } from './google.login.component';
import { UserService } from '../services/user.service';
import { CookieService } from 'angular2-cookie/core';
import { GenericService} from '../services/generic.service';
import { LoginService} from '../services/login.service';
import { SessionService } from '../services/session.service';
import { ModalConfirmService } from '../services/modal.confirm.service';
import { ModalConfirmComponent } from './modal.confirm.component';

@Component({
    selector: 'my-app',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../views/app.component.html',
    precompile: [QuizesComponent, LoginComponent, RegisterComponent, HomeComponent, UserComponent, FacebookLoginComponent, GoogleLoginComponent],
    providers: [UserService, CookieService, GenericService, LoginService, SessionService, ModalConfirmService]
})
export class AppComponent {
    constructor(private userService: UserService, private sessionService: SessionService) {
    }
}