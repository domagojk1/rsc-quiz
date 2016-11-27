import { provideRouter, RouterConfig } from '@angular/router';
import { QuizesComponent } from './components/quizes.component';
import { LoginComponent } from './components/login.component';
import { RegisterComponent } from './components/register.component';
import { HomeComponent } from './components/home.component';
import { UserComponent } from './components/user.component';
import { FacebookLoginComponent } from './components/facebook.login.component';
import { GoogleLoginComponent } from './components/google.login.component';
import { NewQuizComponent } from './components/new.quiz.component';

const routes: RouterConfig = [
    { path: '', component: HomeComponent },
    { path: 'quizes', component: QuizesComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'home', component: HomeComponent },
    { path: 'user', component: UserComponent },
    { path: 'facebook-login', component: FacebookLoginComponent },
    { path: 'google-login', component: GoogleLoginComponent },
    { path: 'new-quiz', component: NewQuizComponent }
    
];

export const appRouterProviders = [
    provideRouter(routes)
];