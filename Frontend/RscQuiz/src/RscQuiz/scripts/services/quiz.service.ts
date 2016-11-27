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
import { Team } from '../classes/team';

@Injectable()
export class QuizService {
    private quizes: Array<Quiz>;
    private selectedQuiz: Quiz;
    private selectedQuestion: Question;
    private teams: Array<Team>;

    constructor(private genericService: GenericService,
        private modalConfirmService: ModalConfirmService,
        private userService: UserService) {
        this.quizes = [];


        setInterval(() => {
            if (this.getCurrentQuiz() != null) {
                this.refreshTeams(this.getCurrentQuiz().id);
            }
        }, 1000 * 5);
    }

    getQuizes(): Quiz[] {
        return this.quizes;
    }    

    setCurrentQuiz(quiz: Quiz): void {
        this.teams = [];
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

    getMyQuizes(): void {
        this.genericService.getObservableGet<Array<Quiz>>("api/Events/MyEvents")
            .subscribe(quizes => {
                this.quizes = new Array<Quiz>();
                quizes.forEach(quiz => {
                    if (quiz.questions == null) {
                        quiz.questions = [{ text: "Pitanje 1?" }];
                        quiz.questions.pop();
                    }
                    this.quizes.push(quiz);
                });
            });
    }

    refreshTeams(eventId: number): void {
        this.genericService.getObservableGet<Quiz>("api/Events/GetTeams/" + eventId)
            .subscribe(quiz => {
                if (this.getCurrentQuiz() != null) {
                    console.log("got teams: " + quiz.teams.length + ", for quiz: " + quiz.name);
                    this.teams = quiz.teams;
                }
            });
    }

    openQuiz(quiz: Quiz) {
        if (quiz != null) {
            quiz.isOpen = true;
            this.genericService.getObservableGet<Array<Team>>("api/Events/OpenEvent/" + quiz.id)
                .subscribe(teams => {
                    //this.teams = teams;
                });
        }
    }

    startQuiz(quiz: Quiz) {
        if (quiz != null) {
            quiz.isOpen = false;
            this.genericService.getObservableGet<Array<Team>>("api/Events/StartEvent/" + quiz.id)
                .subscribe(teams => {
                    //this.teams = teams;
                });
        }
    }

    sendNextQuestion(quiz: Quiz) {
        if (quiz != null) {
            this.genericService.getObservableGet<Array<Team>>("api/Questions/GenerateQuestion/" + quiz.id)
                .subscribe(teams => {
                    //this.teams = teams;
                });
        }
    }

    getTeams(): Team[] {
        return this.teams;
    }
}