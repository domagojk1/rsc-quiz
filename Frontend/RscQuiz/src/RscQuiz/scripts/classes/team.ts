import { TeamMember } from './team.member'

export class Team {
    id: string;
    eventId: string;
    username: string;
    score: number;
    isWinner: boolean;
    teamMembers: TeamMember[];
}