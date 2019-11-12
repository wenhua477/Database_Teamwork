
USE PM2;
-- insert persons infomation-- 
INSERT INTO Persons(UserName, Password) 
 VALUES('BrakeBread','password1010'); 
INSERT INTO Persons(UserName, Password) 
 VALUES('AngryAnt','password2020');   
INSERT INTO Persons(UserName, Password) 
 VALUES('CreamCandy','password2333');  
INSERT INTO Persons(UserName, Password) 
 VALUES('admin','admin');  

INSERT INTO Users(UserId,FirstName, LastName, Email, Phone, street,city, state, Zip,level) 
 VALUES(1,'Bred','Pit','bdpt@gmail.com','8501133004','1170 COUNTY RD','Robertsdale','Alabama','36567','ACTIVE'); 
INSERT INTO Users(UserId,FirstName, LastName, Email, Phone, street,city, state, Zip,level) 
 VALUES(2,'Thom','Cruis','tmcs@gmail.com','2601253007','112 AUGUSTA STREET','Clanton','Alabama','35045','NORMAL'); 
INSERT INTO Users(UserId,FirstName, LastName, Email, Phone, street,city, state, Zip,level) 
 VALUES(3,'Thom','Hardison','tmhd@gmail.com','3701656894','148 N. MAIN ST','Clinton','Michigan','48035','LAZY'); 

INSERT INTO Administrators(UserId, LastLogin) 
 VALUES(4,'2019-09-12');  
  
  
-- insert places information-- 
LOAD DATA INFILE '/tmp/fed_campsites.csv' INTO TABLE StarGazingPlaces
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10) set PlaceId=@col2,Latitude=@col6,Longitude=@col7,State=@col9;
  
LOAD DATA INFILE '/tmp/fed_campsites.csv' INTO TABLE Campsites
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10) set PlaceId=@col2,Name=@col3,Type=@col4;
  

-- insert reviews/recommendations/footprints info-- 
INSERT INTO Reviews(CreatedTime, Content, Rating, UserId, PlaceId) 
 VALUES('2019-08-11','not bad', 3.9, 2, 1); 
INSERT INTO Reviews(CreatedTime, Content, Rating, UserId, PlaceId) 
 VALUES('2019-08-23','really nice place', 4.9, 1, 2); 
  
INSERT INTO Recommendations(UserId, PlaceId) 
 VALUES(1, 2); 
INSERT INTO Recommendations(UserId, PlaceId) 
 VALUES(2, 1); 
  
INSERT INTO Footprints(UserId, PlaceId, TimeVisited) 
 VALUES(1, 2, '2019-08-23 13:00:01'); 
INSERT INTO Footprints(UserId, PlaceId, TimeVisited) 
 VALUES(2, 1,'2019-08-11 17:00:01'); 
  

  
-- insert county info--
LOAD DATA LOCAL INFILE '/tmp/crime_data_w_population_and_crime_rate.csv' INTO TABLE CountyInfo
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  lines terminated by '\n'
  IGNORE 1 LINES
  (@col1,@col2,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy) set CountyName =@col1,CrimeRate=@col2 ;

-- insert location info--
LOAD DATA LOCAL INFILE '/tmp/aqs_sites.csv' INTO TABLE LocationInfo
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  lines terminated by '\n' 
  IGNORE 1 LINES
    (@dummy, @dummy,@dummy, @col4, @col5, @dummy, @col7, @dummy, @col9, @dummy, @dummy,@dummy,@dummy, @dummy,@dummy,@dummy, @dummy,@dummy,@dummy, @dummy, @dummy,@col22, @col23,@col24,@dummy,@dummy,@dummy,@dummy) set Latitude = @col4, Longitude=@col5, Elevation = @col7, Population =@col9,
    Zip =@col22,State= @col23,County= @col24;


  