import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { SuccessError } from '../interfaces/success.error';
import { GenericService } from './generic.service';
import { GenericResponse } from '../interfaces/generic.response';
import { ModalConfirmService } from './modal.confirm.service';
import { UserService } from './user.service';
import { ModalConfirmData } from '../interfaces/modal.confirm.data';
import { Quiz } from '../classes/quiz';
import { Question } from '../classes/question';

@Injectable()
export class QuizService {
    private quizes: Array<Quiz>;
    private selectedQuiz: Quiz;
    private selectedQuestion: Question;

    constructor(private genericService: GenericService,
        private modalConfirmService: ModalConfirmService,
        private userService: UserService) {
        this.quizes = [];
        var quiz1: Quiz = { id: 1, name: "Quiz 1", description: "This is quiz 1", questions: [{ text: "Pitanje 1?" }, { text: "Pitanje 2?" }, { text: "Pitanje 3?" }] };
        var quiz2: Quiz = { id: 2, name: "Quiz 2", description: "This is quiz 2", questions: [{ text: "Pitanje 2?" }] };
        var quiz3: Quiz = { id: 3, name: "Quiz 3", description: "This is quiz 3", questions: [{ text: "Pitanje 3?" }] };
        this.quizes.push(quiz1, quiz2, quiz3);
    }

    getQuizes(): Quiz[] {
        return this.quizes;
    }    

    setCurrentQuiz(quiz: Quiz): void {
        this.selectedQuiz = quiz;
    }

    getCurrentQuiz(): Quiz {
        return this.selectedQuiz;
    }

    setCurrentQuestion(question: Question): void {
        this.selectedQuestion = question;
    }

    getCurrentQuestion(): Question {
        return this.selectedQuestion;
    }

    addNewQuiz(quiz: Quiz, successError: SuccessError) {
        this.genericService.getObservablePost<GenericResponse>("api/Events/Add",quiz,false)
            .subscribe(successError.onSuccess, successError.onError);
    }
}