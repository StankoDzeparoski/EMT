-- Insert countries
insert into countries (created_at, updated_at, name, continent)
values
    (now(), now(), 'North Macedonia', 'Europe'),
    (now(), now(), 'Serbia', 'Europe'),
    (now(), now(), 'United States', 'North America'),
    (now(), now(), 'Germany', 'Europe'),
    (now(), now(), 'Japan', 'Asia');

-- Insert hosts (using subqueries to link to countries)
insert into hosts (created_at, updated_at, name, surname, country_id)
values
    (now(), now(), 'Petar', 'Petrovski',
     (select id from countries where name = 'North Macedonia')),
    (now(), now(), 'Ana', 'Ivanova',
     (select id from countries where name = 'North Macedonia')),
    (now(), now(), 'John', 'Smith',
     (select id from countries where name = 'United States')),
    (now(), now(), 'Marko', 'Markovic',
     (select id from countries where name = 'Serbia')),
    (now(), now(), 'Hiroshi', 'Tanaka',
     (select id from countries where name = 'Japan'));

-- Insert accommodations
insert into accommodations (created_at, updated_at, name, category, condition, occupied, host_id, num_rooms)
values
    (now(), now(), 'Cozy Apartment in Skopje', 'APARTMENT', 'GOOD', false,
     (select id from hosts where name = 'Petar' and surname = 'Petrovski'), 2),
    (now(), now(), 'Downtown Room', 'ROOM', 'GOOD', true,
     (select id from hosts where name = 'Ana' and surname = 'Ivanova'), 1),
    (now(), now(), 'Luxury Hotel Suite', 'HOTEL', 'GOOD', false,
     (select id from hosts where name = 'John' and surname = 'Smith'), 3),
    (now(), now(), 'Rustic House in Village', 'HOUSE', 'BAD', true,
     (select id from hosts where name = 'Marko' and surname = 'Markovic'), 4),
    (now(), now(), 'Modern Flat in Tokyo', 'FLAT', 'GOOD', false,
     (select id from hosts where name = 'Hiroshi' and surname = 'Tanaka'), 2),
    (now(), now(), 'Budget Motel Room', 'MOTEL', 'BAD', true,
     (select id from hosts where name = 'John' and surname = 'Smith'), 1);