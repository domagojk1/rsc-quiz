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
var quiz_1 = require('../classes/quiz');
var quiz_service_1 = require('../services/quiz.service');
var question_component_1 = require('./question.component');
var question_detail_component_1 = require('./question.detail.component');
var teams_component_1 = require('./teams.component');
var QuizDetailComponent = (function () {
    function QuizDetailComponent(quizService) {
        this.quizService = quizService;
    }
    QuizDetailComponent.prototype.closeQuiz = function () {
        this.quizService.setCurrentQuiz(null);
    };
    QuizDetailComponent.prototype.getCurrentQuestion = function () {
        return this.quizService.getCurrentQuestion();
    };
    QuizDetailComponent.prototype.openQuestion = function (question) {
        if (this.quiz.isOpen == false) {
            this.quizService.setCurrentQuestion(question);
        }
    };
    QuizDetailComponent.prototype.startQuiz = function () {
        if (this.quizService.getTeams() != null && this.quizService.getTeams().length > 1) {
            this.quizService.startQuiz(this.quiz);
        }
    };
    QuizDetailComponent.prototype.canStartQuiz = function () {
        return this.quizService.getTeams() != null && this.quizService.getTeams().length > 1;
    };
    QuizDetailComponent.prototype.isInProgress = function () {
        return this.quizService.getTeams() != null && this.quizService.getTeams().length > 1 && this.quiz.isOpen == false;
    };
    QuizDetailComponent.prototype.getNextQuestion = function () {
        this.quizService.sendNextQuestion(this.quiz);
        this.quiz.questions.push({ text: "Next question" });
    };
    QuizDetailComponent.prototype.getNumberOfTeams = function () {
        return this.quizService.getTeams() != null ? this.quizService.getTeams().length : 0;
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', quiz_1.Quiz)
    ], QuizDetailComponent.prototype, "quiz", void 0);
    QuizDetailComponent = __decorate([
        core_1.Component({
            selector: 'quiz-detail',
            directives: [question_component_1.QuestionComponent, question_detail_component_1.QuestionDetailComponent, teams_component_1.TeamsComponent],
            templateUrl: '../views/quiz.detail.component.html'
        }), 
        __metadata('design:paramtypes', [quiz_service_1.QuizService])
    ], QuizDetailComponent);
    return QuizDetailComponent;
}());
exports.QuizDetailComponent = QuizDetailComponent;
//# sourceMappingURL=quiz.detail.component.js.map