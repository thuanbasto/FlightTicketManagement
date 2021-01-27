CREATE TABLE `Airplane` (
  `Airplane_Id` varchar(5) PRIMARY KEY,
  `Name` nvarchar(200)
);

CREATE TABLE `TravelClass` (
  `TravelClass_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Name` nvarchar(50) NOT NULL,
  `Quantity` int(10)
);

CREATE TABLE `Seat` (
  `Seat_Id` varchar(5) PRIMARY KEY,
  `TravelClass_Id` int(10)
);

CREATE TABLE `SignedLuggage_Price` (
  `Price_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Price` double,
  `ModifiedDate` datetime,
  `SignedLuggage_Id` int(10)
);

CREATE TABLE `TravelClass_Price` (
  `Price_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Price` double,
  `ModifiedDate` datetime,
  `TravelClass_Id` int(10)
);

CREATE TABLE `SignedLuggage` (
  `SignedLuggage_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Name` nvarchar(50) NOT NULL,
  `Weight` double
);

CREATE TABLE `Flight` (
  `Flight_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `From_Airport_Id` varchar(3),
  `To_Airport_Id` varchar(3),
  `DeparutreDate` datetime,
  `ArrivalDate` datetime,
  `Flight_Price` double,
  `Airplane_Id` varchar(5)
);

CREATE TABLE `Airport` (
  `Airport_Id` varchar(3) PRIMARY KEY,
  `Name` nvarchar(200),
  `City_Id` varchar(5)
);

CREATE TABLE `City` (
  `City_Id` varchar(5) PRIMARY KEY,
  `Name` nvarchar(200)
);

CREATE TABLE `Ticket` (
  `Ticket_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Booking_Id` int(10),
  `Flight_Id` int(10),
  `Customer_Id` int(10),
  `Seat_Id` varchar(5),
  `SignedLuggage_Id` int(10),
  `Ticket_PriceTotal` double
);

CREATE TABLE `Booking` (
  `Booking_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `PaymentMethod` varchar(100),
  `User_Id` int(10),
  `BookingDate` datetime,
  `Phone` nvarchar(10),
  `Email` nvarchar(200)
);

CREATE TABLE `User` (
  `User_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `LastName` nvarchar(200),
  `FirstName` nvarchar(200),
  `BirthDay` date,
  `Address` nvarchar(200),
  `Phone` nvarchar(10),
  `Email` nvarchar(200),
  `Role_Id` int(10)
);

CREATE TABLE `Tax` (
  `Tax_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `TaxName` nvarchar(200)
);

CREATE TABLE `Tax_Price` (
  `Tax_Price_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Price` nvarchar(200),
  `ModifiedDate` datetime,
  `Tax_Id` int(10)
);

CREATE TABLE `Ticket_Tax` (
  `Tax_Id` int(10),
  `Ticket_Id` int(10),
  PRIMARY KEY (`Tax_Id`, `Ticket_Id`)
);

CREATE TABLE `Role_User` (
  `Role_Id` int(10),
  `User_Id` int(10),
  PRIMARY KEY (`Role_Id`, `User_Id`)
);

CREATE TABLE `Role` (
  `Role_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `Name` varchar(100)
);

CREATE TABLE `Customer` (
  `Customer_Id` int(10) PRIMARY KEY AUTO_INCREMENT,
  `IdentityNumber` varchar(15) UNIQUE,
  `LastName` nvarchar(200),
  `FirstName` nvarchar(200),
  `BirthDay` date,
  `Address` nvarchar(200)
);

ALTER TABLE `Role_User` ADD FOREIGN KEY (`User_Id`) REFERENCES `User` (`User_Id`);

ALTER TABLE `Role_User` ADD FOREIGN KEY (`Role_Id`) REFERENCES `Role` (`Role_Id`);

ALTER TABLE `Seat` ADD FOREIGN KEY (`TravelClass_Id`) REFERENCES `TravelClass` (`TravelClass_Id`);

ALTER TABLE `Airport` ADD FOREIGN KEY (`City_Id`) REFERENCES `City` (`City_Id`);

ALTER TABLE `Flight` ADD FOREIGN KEY (`From_Airport_Id`) REFERENCES `Airport` (`Airport_Id`);

ALTER TABLE `Flight` ADD FOREIGN KEY (`To_Airport_Id`) REFERENCES `Airport` (`Airport_Id`);

ALTER TABLE `Ticket` ADD FOREIGN KEY (`Booking_Id`) REFERENCES `Booking` (`Booking_Id`);

ALTER TABLE `Ticket` ADD FOREIGN KEY (`Flight_Id`) REFERENCES `Flight` (`Flight_Id`);

ALTER TABLE `Ticket` ADD FOREIGN KEY (`SignedLuggage_Id`) REFERENCES `SignedLuggage` (`SignedLuggage_Id`);

ALTER TABLE `Ticket` ADD FOREIGN KEY (`Seat_Id`) REFERENCES `Seat` (`Seat_Id`);

ALTER TABLE `Ticket` ADD FOREIGN KEY (`Customer_Id`) REFERENCES `Customer` (`Customer_Id`);

ALTER TABLE `Flight` ADD FOREIGN KEY (`Airplane_Id`) REFERENCES `Airplane` (`Airplane_Id`);

ALTER TABLE `Ticket_Tax` ADD FOREIGN KEY (`Ticket_Id`) REFERENCES `Ticket` (`Ticket_Id`);

ALTER TABLE `Ticket_Tax` ADD FOREIGN KEY (`Tax_Id`) REFERENCES `Tax` (`Tax_Id`);

ALTER TABLE `Tax_Price` ADD FOREIGN KEY (`Tax_Id`) REFERENCES `Tax` (`Tax_Id`);

ALTER TABLE `TravelClass_Price` ADD FOREIGN KEY (`TravelClass_Id`) REFERENCES `TravelClass` (`TravelClass_Id`);

ALTER TABLE `SignedLuggage_Price` ADD FOREIGN KEY (`SignedLuggage_Id`) REFERENCES `SignedLuggage` (`SignedLuggage_Id`);

ALTER TABLE `Booking` ADD FOREIGN KEY (`User_Id`) REFERENCES `User` (`User_Id`);

