import {Component, Input} from '@angular/core';
import { Question} from '../classes/question'

@Component({
    selector: 'question',
    templateUrl: '../views/question.component.html'
})
export class QuestionComponent {
    @Input() question: Question;
}