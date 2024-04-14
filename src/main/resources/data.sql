CREATE TABLE task
(
    id          bigserial PRIMARY KEY NOT NULL,
    title       varchar(255)          NOT NULL,
    description varchar(255),
    due_date    timestamp(6),
    completed   boolean
);