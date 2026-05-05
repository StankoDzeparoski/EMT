// private Long id;
// private String comment;
//     private Double rating;
//     @ManyToOne
//     @JoinColumn(name = "accomodation_id")
//     private Accomodation accomodation;
import type { Accommodation } from './Accommodation.ts';

export interface Review {
  id: number,
  comment: string;
  rating: number;
  accomodation: Accommodation;
}