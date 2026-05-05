CREATE TABLE auth_logs (
                           id BIGSERIAL PRIMARY KEY,
                           username VARCHAR(255) NOT NULL,
                           token VARCHAR(1000) NOT NULL,
                           issued_at TIMESTAMP NOT NULL,
                           expires_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_auth_logs_username ON auth_logs(username);