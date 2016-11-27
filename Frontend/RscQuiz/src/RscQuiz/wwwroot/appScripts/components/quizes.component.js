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
var quiz_component_1 = require('./quiz.component');
var quiz_detail_component_1 = require('./quiz.detail.component');
var quiz_service_1 = require('../services/quiz.service');
var QuizesComponent = (function () {
    function QuizesComponent(quizService) {
        this.quizService = quizService;
    }
    QuizesComponent.prototype.ngOnInit = function () {
        this.quizService.getMyQuizes();
    };
    QuizesComponent.prototype.getQuizes = function () {
        return this.quizService.getQuizes();
    };
    QuizesComponent.prototype.getCurrentQuiz = function () {
        return this.quizService.getCurrentQuiz();
    };
    QuizesComponent.prototype.openQuiz = function (quiz) {
        this.quizService.setCurrentQuiz(quiz);
    };
    QuizesComponent = __decorate([
        core_1.Component({
            selector: 'quizes',
            directives: [quiz_component_1.QuizComponent, quiz_detail_component_1.QuizDetailComponent],
            templateUrl: '../views/quizes.component.html'
        }), 
        __metadata('design:paramtypes', [quiz_service_1.QuizService])
    ], QuizesComponent);
    return QuizesComponent;
}());
exports.QuizesComponent = QuizesComponent;
//# sourceMappingURL=quizes.component.js.map