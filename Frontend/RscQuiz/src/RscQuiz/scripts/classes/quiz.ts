﻿import { Question } from './question'
import { Team } from './team'

export class Quiz {
    id: number = 0;
    name: string = "";
    place: string = "";
    dateTime: string = "";
    description: string = "";
    maxMembersPerTeam: number = 4;
    isOpen: boolean = false;
    questions: [Question];
    teams: Array<Team>;
}