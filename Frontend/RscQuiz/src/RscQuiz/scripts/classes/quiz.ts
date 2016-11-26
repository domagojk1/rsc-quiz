import { Question } from './question'

export class Quiz {
    id: number;
    name: string;
    place: string;
    dateTime: string;
    description: string;
    maxMembersPerTeam: number;
    questions: [Question];
}