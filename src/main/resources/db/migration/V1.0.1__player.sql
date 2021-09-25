DROP TABLE IF EXISTS players;
CREATE TABLE players
(
    id         UUID                    NOT NULL,
    name       VARCHAR                 NOT NULL,
    email      VARCHAR                 NOT NULL,
    username   VARCHAR                 NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP DEFAULT NOW() NOT NULL,
    banned_at  TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (id)
);


