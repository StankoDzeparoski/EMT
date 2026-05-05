-- Create a simplified database view for accommodation information
-- This view combines accommodation, host, and country data for easier querying

CREATE OR REPLACE VIEW accommodation_details_view AS
SELECT
    a.id as accommodation_id,
    a.name as accommodation_name,
    a.category,
    a.num_rooms,
    CONCAT(h.name, ' ', h.surname) as host_full_name,
    c.name as country_name
FROM accommodations a
LEFT JOIN hosts h ON a.host_id = h.id
LEFT JOIN countries c ON h.country_id = c.id;

