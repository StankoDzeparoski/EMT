-- Create countries table
create table countries (
                           id bigserial primary key,
                           created_at timestamp not null,
                           updated_at timestamp not null,
                           name varchar(255) not null,
                           continent varchar(255) not null
);

-- Create hosts table (depends on countries)
create table hosts (
                       id bigserial primary key,
                       created_at timestamp not null,
                       updated_at timestamp not null,
                       name varchar(255) not null,
                       surname varchar(255) not null,
                       country_id bigint references countries(id) on delete set null
);

-- Create accommodations table (depends on hosts)
create table accommodations (
                                id bigserial primary key,
                                created_at timestamp not null,
                                updated_at timestamp not null,
                                name varchar(255) not null,
                                category varchar(50) not null,          -- stores enum: ROOM, HOUSE, FLAT, APARTMENT, HOTEL, MOTEL
                                condition varchar(50) not null,          -- stores enum: GOOD, BAD
                                occupied boolean not null,
                                host_id bigint references hosts(id) on delete set null,
                                num_rooms integer not null
);