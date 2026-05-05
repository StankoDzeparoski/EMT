import type { Host } from './Host.ts';
import type { Review } from './Review.ts';

export const Category = {
  ROOM: 'ROOM',
  FLAT: 'FLAT',
  HOUSE: 'HOUSE',
  APARTMENT: 'APARTMENT',
  MOTEL: 'MOTEL'
} as const;
export type Category = typeof Category[keyof typeof Category];

export const Condition = {
  BAD: 'BAD',
  GOOD: 'GOOD'
} as const;
export type Condition = typeof Condition[keyof typeof Condition];

export interface DisplayReviewDto {
  id: number;
  comment: string;
  rating: number;
}

export interface HostInfoDto {
  id: number;
  name: string;
  surname: string;
}

export interface DisplayAccommodationDto {
  id: number;
  name: string;
  category: Category | string;
  condition: Condition | string;
  host: HostInfoDto | Host;
  numRooms: number;
  occupied: boolean;
  averageRating: number;
  reviews: DisplayReviewDto[];
}

export interface CreateAccommodationDto {
  name: string;
  category: Category | string;
  condition: Condition | string;
  numRooms: number;
  hostId: number;
  countryId?: number;
}

export interface AccommodationProjection {
  id: number;
  name: string;
  category: string;
  numRooms: number;
}

export interface AccommodationExtendedProjection {
  id: number;
  name: string;
  category: string;
  numRooms: number;
  hostName: string;
  hostSurname: string;
  countryName: string;
}

export interface OptimizedAccommodationDto {
  id: number;
  name: string;
  category: Category | string;
  condition: Condition | string;
  numRooms: number;
  occupied: boolean;
  createdAt: string;
  host: HostInfoDto;
  averageRating: number;
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

export interface Accommodation {
  id: number;
  name: string;
  category: string;
  condition: boolean;
  host: Host;
  numRooms: number;
  reviews: Review[];
}