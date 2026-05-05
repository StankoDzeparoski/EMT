-- Create reviews table to store user reviews for accommodations
CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         comment TEXT,
                         rating DOUBLE PRECISION,
                         accomodation_id BIGINT NOT NULL,
                         CONSTRAINT fk_reviews_accomodation FOREIGN KEY (accomodation_id)
                             REFERENCES accommodations(id) ON DELETE CASCADE
);

CREATE INDEX idx_reviews_accomodation_id ON reviews(accomodation_id);