SELECT * FROM towns;

-- creating index
CREATE INDEX idx_towns_name ON towns(name);
-- deleting index
DROP INDEX IF EXISTS idx_towns_name;

-- explain analyze used to view performance metrics
EXPLAIN ANALYSE
SELECT *
FROM towns
WHERE id = '135564';

-- to view all available indexes
SELECT
    tablename,
    indexname,
    indexdef
FROM
    pg_indexes
WHERE
        schemaname = 'public'
ORDER BY
    tablename,
    indexname;

