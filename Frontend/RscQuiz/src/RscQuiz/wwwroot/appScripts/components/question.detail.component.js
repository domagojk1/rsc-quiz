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
var question_1 = require('../classes/question');
var quiz_service_1 = require('../services/quiz.service');
var QuestionDetailComponent = (function () {
    function QuestionDetailComponent(quizService) {
        this.quizService = quizService;
    }
    QuestionDetailComponent.prototype.closeQuestion = function () {
        this.quizService.setCurrentQuestion(null);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', question_1.Question)
    ], QuestionDetailComponent.prototype, "question", void 0);
    QuestionDetailComponent = __decorate([
        core_1.Component({
            selector: 'question-detail',
            templateUrl: '../views/question.detail.component.html'
        }), 
        __metadata('design:paramtypes', [quiz_service_1.QuizService])
    ], QuestionDetailComponent);
    return QuestionDetailComponent;
}());
exports.QuestionDetailComponent = QuestionDetailComponent;
//# sourceMappingURL=question.detail.component.js.map