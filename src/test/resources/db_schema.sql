CREATE TABLE IF NOT EXISTS USERS (
                                     userId varchar(12) NOT NULL,
                                     password varchar(12) NOT NULL,
                                     name varchar(20) NOT NULL,
                                     email varchar(50),
                                     PRIMARY KEY (userId)
);
