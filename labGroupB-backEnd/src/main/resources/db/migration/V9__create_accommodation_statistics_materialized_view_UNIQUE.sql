-- Drop the existing regular index
DROP INDEX IF EXISTS idx_accommodation_stats_category;

-- Create a UNIQUE index required for concurrent materialized view refresh
CREATE UNIQUE INDEX idx_accommodation_stats_category_unique ON accommodation_statistics_by_category(category);
