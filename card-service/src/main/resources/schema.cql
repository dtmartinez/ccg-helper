-- Keyspace (single-node dev defaults)
CREATE KEYSPACE IF NOT EXISTS cards
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

-- Card table
CREATE TABLE IF NOT EXISTS cards.card (
id text PRIMARY KEY,
name text,
type text,
cost int
);
