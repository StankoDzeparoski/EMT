-- Create table to track fully occupied accommodations
-- This table records when accommodations become fully booked
CREATE TABLE fully_occupied_accommodations (
    id bigserial primary key,
    accommodation_id bigint not null,
    accommodation_name varchar(255),
    category varchar(50),
    total_rooms integer,
    host_name varchar(255),
    host_email varchar(255),
    country_name varchar(255),
    fully_occupied_at timestamp not null,
    expected_vacancy_date timestamp,
    status varchar(20) not null default 'OCCUPIED',  -- OCCUPIED, VACANT, ARCHIVED
    notes text,
    created_at timestamp not null,
    updated_at timestamp not null,
    foreign key (accommodation_id) references accommodations(id) on delete cascade
);

-- Create indices for efficient querying
CREATE INDEX idx_fully_occupied_accommodations_accommodation_id ON fully_occupied_accommodations(accommodation_id);
CREATE INDEX idx_fully_occupied_accommodations_status ON fully_occupied_accommodations(status);
CREATE INDEX idx_fully_occupied_accommodations_fully_occupied_at ON fully_occupied_accommodations(fully_occupied_at DESC);
CREATE INDEX idx_fully_occupied_accommodations_expected_vacancy ON fully_occupied_accommodations(expected_vacancy_date);

