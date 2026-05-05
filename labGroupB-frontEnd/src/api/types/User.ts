export const Role = {
  USER: 'USER',
  ADMIN: 'ADMIN'
} as const;
export type Role = typeof Role[keyof typeof Role];

export interface User {
  username: string;
  name: string;
  surname: string;
  email: string;
  role: Role | string;
}

export interface RegisterUserRequestDto {
  name: string;
  surname: string;
  email: string;
  username: string;
  password: string;
}

export interface RegisterUserResponseDto {
  username: string;
  name: string;
  surname: string;
  email: string;
  role: Role | string;
}

export interface LoginUserRequestDto {
  username: string;
  password: string;
}

export interface LoginUserResponseDto {
  token: string;
}
