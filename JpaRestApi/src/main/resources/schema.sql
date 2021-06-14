CREATE TABLE Member (
    id bigint auto_increment,
    name      VARCHAR(255),
    address   VARCHAR(255),
    PRIMARY KEY ( id ),
    UNIQUE (name)
);


