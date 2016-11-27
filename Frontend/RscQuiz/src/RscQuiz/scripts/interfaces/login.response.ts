import { User } from '../classes/user';

export interface LoginResponse {
    userInfo: User;
    token: string;
    tokenExpires: number;
    message: string;
    errors: [any];
    code: number;
}