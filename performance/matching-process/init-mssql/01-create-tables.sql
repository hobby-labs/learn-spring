USE master;

CREATE TABLE Persons (
    PersonID int IDENTITY(1,1) PRIMARY KEY,
    Name varchar(255),
    MailAddress varchar(255),
    City varchar(255)
);
