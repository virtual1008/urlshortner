CREATE TABLE short_urls (
    id BIGINT PRIMARY KEY,
    short_code VARCHAR(255) NOT NULL UNIQUE,
    original_url TEXT NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    deleted BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);