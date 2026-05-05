-- Create activity log table to track accommodation rental events
CREATE TABLE accommodation_activity_log (
    id bigserial primary key,
    accommodation_id bigint not null,
    activity_type varchar(50) not null,
    description text,
    total_rooms integer,
    available_rooms integer,
    created_at timestamp not null,
    foreign key (accommodation_id) references accommodations(id) on delete cascade
);

-- Create index for efficient querying
CREATE INDEX idx_accommodation_activity_log_accommodation_id ON accommodation_activity_log(accommodation_id);
CREATE INDEX idx_accommodation_activity_log_created_at ON accommodation_activity_log(created_at);
CREATE INDEX idx_accommodation_activity_log_activity_type ON accommodation_activity_log(activity_type);

