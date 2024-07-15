CREATE TABLE if not exists user_credentials (
    id bigserial,
    username varchar(20),
    email varchar(50),
    password varchar(150),
    PRIMARY KEY (id)
);