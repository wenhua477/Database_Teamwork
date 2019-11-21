-- create tables-- 
CREATE SCHEMA IF NOT EXISTS PM2;
USE PM2;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Footprints;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Persons;
DROP TABLE IF EXISTS Campsites; 
DROP TABLE IF EXISTS Observatory;
DROP TABLE IF EXISTS StarGazingPlaces; 
DROP TABLE IF EXISTS LocationInfo;
DROP TABLE IF EXISTS CountyInfo;

CREATE TABLE Persons (
	UserId INT AUTO_INCREMENT,
    UserName VARCHAR(255),
	Password VARCHAR(255),
    CONSTRAINT pk_Persons_UserId PRIMARY KEY (UserId)
);

CREATE TABLE Administrators (
	UserId INT AUTO_INCREMENT,
    LastLogin TIMESTAMP,
    CONSTRAINT pk_Administrators_UserId PRIMARY KEY (UserId),
    CONSTRAINT fk_Administrators_UserId
		FOREIGN KEY (UserId)
		REFERENCES Persons(UserId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Users (
	UserId INT AUTO_INCREMENT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(255),
    Street VARCHAR(255),
    City VARCHAR(255),
    State VARCHAR(255),
    Zip VARCHAR(255),
    Level ENUM ('active', 'normal', 'lazy'),
    CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId),
    CONSTRAINT fk_Users_UserId
		FOREIGN KEY (UserId)
		REFERENCES Persons(UserId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE StarGazingPlaces (
	PlaceId INT AUTO_INCREMENT,
    Latitude decimal (9,5),
    Longitude decimal (9,5),
    State VARCHAR(255),
    CONSTRAINT pk_StarGazingPlaces_PlaceId PRIMARY KEY (PlaceId)																	
);

CREATE TABLE Campsites (
	PlaceId INT AUTO_INCREMENT,
    Name VARCHAR(255),
	Type VARCHAR(255),
    Capacity INT,
    fips VARCHAR(255),
    CONSTRAINT pk_Campsites_PlaceId PRIMARY KEY (PlaceId),
    CONSTRAINT fk_Campsites_PlaceId
		FOREIGN KEY (PlaceId)
		REFERENCES StarGazingPlaces(PlaceId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Observatory (
	PlaceId INT AUTO_INCREMENT,
    Price DECIMAL(4,2),
    OpenHour VARCHAR(255),
    CONSTRAINT pk_Observatory_PlaceId PRIMARY KEY (PlaceId),
    CONSTRAINT fk_Observatory_PlaceId
		FOREIGN KEY (PlaceId)
		REFERENCES StarGazingPlaces(PlaceId)
		ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Reviews (
	ReviewId INT AUTO_INCREMENT,
    CreatedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Content LONGTEXT,
    Rating DECIMAL(4,1),
    UserId INT,
    PlaceId INT,
    CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
    CONSTRAINT fk_Reviews_UserId
		FOREIGN KEY (UserId)
		REFERENCES Persons(UserId)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Reviews_PlaceId
		FOREIGN KEY (PlaceId)
		REFERENCES StarGazingPlaces(PlaceId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recommendations (
	RecommendationId INT AUTO_INCREMENT,
    UserId INT,
    PlaceId INT,
    CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
    CONSTRAINT fk_Recommendations_UserId
		FOREIGN KEY (UserId)
		REFERENCES Persons(UserId)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Recommendations_PlaceId
		FOREIGN KEY (PlaceId)
		REFERENCES StarGazingPlaces(PlaceId)
		ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Footprints(
	FootprintId INT AUTO_INCREMENT,
    UserId INT,
    PlaceId INT,
    TimeVisited TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_Footprints_RecommendationId PRIMARY KEY (FootprintId),
    CONSTRAINT fk_Footprints_UserId
		FOREIGN KEY (UserId)
		REFERENCES Persons(UserId)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Footprints_PlaceId
		FOREIGN KEY (PlaceId)
		REFERENCES StarGazingPlaces(PlaceId)
		ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE LocationInfo (
  Latitude decimal (9,5),  
  Longitude decimal (9,5),   
  Elevation decimal (7,5),  
  Population VARCHAR(255),
  State VARCHAR(255),
  County VARCHAR(255),
  fips VARCHAR(255),
  CONSTRAINT pk_LocationInfo_Latitude_Longitude PRIMARY KEY (Latitude,Longitude)
);

CREATE TABLE CountyInfo (
  CountyName VARCHAR(255),
  StateName VARCHAR(255),
  CrimeRate decimal(8,4),
  fips VARCHAR(255),
  CONSTRAINT pk_CountyInfo_CountyName_StateName PRIMARY KEY (CountyName,StateName)
);

