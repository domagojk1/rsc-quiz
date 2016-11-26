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
        this.genericService = genericService;
        this.modalConfirmService = modalConfirmService;
        this.userService = userService;
        this.quizes = [];
        var quiz1 = { id: 1, name: "Quiz 1", description: "This is quiz 1", questions: [{ text: "Pitanje 1?" }, { text: "Pitanje 2?" }, { text: "Pitanje 3?" }] };
        var quiz2 = { id: 2, name: "Quiz 2", description: "This is quiz 2", questions: [{ text: "Pitanje 2?" }] };
        var quiz3 = { id: 3, name: "Quiz 3", description: "This is quiz 3", questions: [{ text: "Pitanje 3?" }] };
        this.quizes.push(quiz1, quiz2, quiz3);
    }
    QuizService.prototype.getQuizes = function () {
        return this.quizes;
    };
    QuizService.prototype.setCurrentQuiz = function (quiz) {
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
    QuizService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [generic_service_1.GenericService, modal_confirm_service_1.ModalConfirmService, user_service_1.UserService])
    ], QuizService);
    return QuizService;
}());
exports.QuizService = QuizService;
//# sourceMappingURL=quiz.service.js.map