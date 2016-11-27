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
require('rxjs/add/operator/map');
var generic_service_1 = require('./generic.service');
var modal_confirm_service_1 = require('./modal.confirm.service');
var user_service_1 = require('./user.service');
var QuizService = (function () {
    function QuizService(genericService, modalConfirmService, userService) {
        var _this = this;
        this.genericService = genericService;
        this.modalConfirmService = modalConfirmService;
        this.userService = userService;
        this.quizes = [];
        setInterval(function () {
            if (_this.getCurrentQuiz() != null) {
                console.log("fetching teams");
                _this.refreshTeams(_this.getCurrentQuiz().id);
            }
        }, 1000 * 5);
    }
    QuizService.prototype.getQuizes = function () {
        return this.quizes;
    };
    QuizService.prototype.setCurrentQuiz = function (quiz) {
        this.teams = [];
        this.selectedQuiz = quiz;
    };
    QuizService.prototype.getCurrentQuiz = function () {
        return this.selectedQuiz;
    };
    QuizService.prototype.setCurrentQuestion = function (question) {
        this.selectedQuestion = question;
    };
    QuizService.prototype.getCurrentQuestion = function () {
        return this.selectedQuestion;
    };
    QuizService.prototype.addNewQuiz = function (quiz, successError) {
        this.genericService.getObservablePost("api/Events/Add", quiz, false)
            .subscribe(successError.onSuccess, successError.onError);
    };
    QuizService.prototype.getMyQuizes = function () {
        var _this = this;
        this.genericService.getObservableGet("/api/Events/MyEvents")
            .subscribe(function (quizes) {
            _this.quizes = new Array();
            quizes.forEach(function (quiz) {
                if (quiz.questions == null) {
                    quiz.questions = [{ text: "Pitanje 1?" }];
                    quiz.questions.pop();
                }
                _this.quizes.push(quiz);
            });
        });
    };
    QuizService.prototype.refreshTeams = function (eventId) {
        var _this = this;
        this.genericService.getObservableGet("/api/Events/GetTeams/" + eventId)
            .subscribe(function (quiz) {
            _this.teams = quiz.teams;
        });
    };
    QuizService.prototype.openQuiz = function (quiz) {
        if (quiz != null) {
            quiz.isOpen = true;
            this.genericService.getObservableGet("/api/Events/OpenEvent/" + quiz.id)
                .subscribe(function (teams) {
                //this.teams = teams;
            });
        }
    };
    QuizService.prototype.startQuiz = function (quiz) {
        if (quiz != null) {
            quiz.isOpen = false;
            this.genericService.getObservableGet("/api/Events/StartEvent/" + quiz.id)
                .subscribe(function (teams) {
                //this.teams = teams;
            });
        }
    };
    QuizService.prototype.getTeams = function () {
        return this.teams;
    };
    QuizService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [generic_service_1.GenericService, modal_confirm_service_1.ModalConfirmService, user_service_1.UserService])
    ], QuizService);
    return QuizService;
}());
exports.QuizService = QuizService;
//# sourceMappingURL=quiz.service.js.map