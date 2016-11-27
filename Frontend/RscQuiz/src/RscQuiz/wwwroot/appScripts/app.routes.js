"use strict";
var router_1 = require('@angular/router');
var quizes_component_1 = require('./components/quizes.component');
var login_component_1 = require('./components/login.component');
var register_component_1 = require('./components/register.component');
var home_component_1 = require('./components/home.component');
var user_component_1 = require('./components/user.component');
var facebook_login_component_1 = require('./components/facebook.login.component');
var google_login_component_1 = require('./components/google.login.component');
var new_quiz_component_1 = require('./components/new.quiz.component');
var routes = [
    { path: '', component: home_component_1.HomeComponent },
    { path: 'quizes', component: quizes_component_1.QuizesComponent },
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'register', component: register_component_1.RegisterComponent },
    { path: 'home', component: home_component_1.HomeComponent },
    { path: 'user', component: user_component_1.UserComponent },
    { path: 'facebook-login', component: facebook_login_component_1.FacebookLoginComponent },
    { path: 'google-login', component: google_login_component_1.GoogleLoginComponent },
    { path: 'new-quiz', component: new_quiz_component_1.NewQuizComponent }
];
exports.appRouterProviders = [
    router_1.provideRouter(routes)
];
//# sourceMappingURL=app.routes.js.map