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
var quiz_1 = require('../classes/quiz');
var NewQuizComponent = (function () {
    function NewQuizComponent(quizService) {
        this.quizService = quizService;
        this.quiz = new quiz_1.Quiz();
    }
    NewQuizComponent.prototype.addNewQuiz = function () {
        var _this = this;
        var successError = {
            onSuccess: function (data) {
                if (data.message != null) {
                    _this.message = data.message;
                    console.log("added new quiz");
                }
            },
            onError: function (error) {
                return; //successError.onError(error) 
            }
        };
        this.quizService.addNewQuiz(this.quiz, successError);
    };
    NewQuizComponent = __decorate([
        core_1.Component({
            selector: 'new-quiz',
            directives: [quiz_component_1.QuizComponent, quiz_detail_component_1.QuizDetailComponent],
            templateUrl: '../views/new.quiz.component.html'
        }), 
        __metadata('design:paramtypes', [quiz_service_1.QuizService])
    ], NewQuizComponent);
    return NewQuizComponent;
}());
exports.NewQuizComponent = NewQuizComponent;
//# sourceMappingURL=new.quiz.component.js.map