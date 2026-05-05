export interface Country {
  id: number;
  name: string;
  continent: string;
}

export interface DisplayCountryDto {
  id: number;
  name: string;
  continent: string;
}

export interface CreateCountryDto {
  name: string;
  continent: string;
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