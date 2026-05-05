import type { Country } from './Country.ts';

export interface Host {
  id: number;
  name: string;
  surname: string;
  country: Country;
}

export interface DisplayHostDto {
  id: number;
  name: string;
  surname: string;
  country: Country;
}

export interface CreateHostDto {
  name: string;
  surname: string;
  countryId: number;
}

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  numberOfElements: number;
  first: boolean;
  last: boolean;
  empty: boolean;
}