USE PM2;

LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/finalProject/Campsites_new.csv' INTO TABLE StarGazingPlaces
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2,@dummy, @col4) set Latitude=@col1,Longitude=@col2,State=@col4;
  
  
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/finalProject/Campsites_new.csv' INTO TABLE Campsites
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2, @col3,@dummy,@col5,@col6,@dummy,@col8) set Name=@col3,Type=@col6,Capacity=@col5,fips=@col8;
  
  
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/finalProject/populationAndElevation.csv' INTO TABLE LocationInfo
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2, @col3, @col4, @col5,@col6,@col7) 
  set Latitude=@col1,Longitude=@col2,Elevation = @col3,Population =@col4,
    State= @col5,County= @col6,fips=@col7;
    
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/finalProject/crimeMap.csv' INTO TABLE CountyInfo
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2, @col3,@col4) 
  set CrimeRate=@col1, CountyName =@col2,stateName =@col2,fips = @col4;


LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/Mock csvs/MockUsers.csv' INTO TABLE Persons
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2) 
  set UserName=@col1, Password =@col2;



LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/Mock csvs/MockUsers.csv' INTO TABLE Users
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2,@col3, @col4, @col5,@col6,@col7,@col8,@col9,@col10,@col11) 
  set FirstName=@col3, LastName =@col4,Email =@col5,Phone = @col6, Street = @col7, City = @col8, State = @col9, Zip = @col10, Level = @col11;
  
  

LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/Mock csvs/MockReviews.csv' INTO TABLE Reviews
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2,@col3, @col4, @col5) 
  set CreatedTime=@col1, Content =@col2, Rating =@col3,UserId = @col4, PlaceId = @col5;
  
  
  
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/Mock csvs/MockRecommendations.csv' INTO TABLE Recommendations
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2) 
  set UserId = @col1, PlaceId = @col2;
  
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/fp/Database_Teamwork/Mock csvs/MockFootprints.csv' INTO TABLE Footprints
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2,@col3) 
  set UserId = @col1, PlaceId = @col2,TimeVisited = @col3;
   
  
  

 
  