-- Create a materialized view for accommodation statistics by category
-- This view aggregates data such as: category, total accommodations, total rooms, average rooms per accommodation

CREATE MATERIALIZED VIEW accommodation_statistics_by_category AS
SELECT
    a.category,
    COUNT(DISTINCT a.id) as total_accommodations,
    SUM(a.num_rooms) as total_rooms,
    ROUND(AVG(a.num_rooms)::numeric, 2) as avg_rooms_per_accommodation
FROM accommodations a
GROUP BY a.category
ORDER BY a.category;

-- Create index on the materialized view for better performance
CREATE INDEX idx_accommodation_stats_category ON accommodation_statistics_by_category(category);

