USE PM2;

LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/PM4/Database_Teamwork/Database_Teamwork/finalProject/Campsites_withFipId.csv' INTO TABLE StarGazingPlaces
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2,@dummy, @col4) set Latitude=@col1,Longitude=@col2,State=@col4;
  
  
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/PM4/Database_Teamwork/Database_Teamwork/finalProject/Campsites_withFipId.csv' INTO TABLE Campsites
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2, @col3,@dummy,@col5,@col6,@dummy,@dummy,@col9) set Name=@col3,Type=@col6,Capacity=@col5,fips=@col9;
  
  
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/PM4/Database_Teamwork/Database_Teamwork/finalProject/populationAndElevation.csv' INTO TABLE LocationInfo
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2, @col3, @col4, @col5,@col6,@col7) 
  set Latitude=@col1,Longitude=@col2,Elevation = @col3,Population =@col4,
    State= @col5,County= @col6,fips=@col7;
    
LOAD DATA LOCAL INFILE '/Users/zoeyw/Documents/database5200/PMs/PM4/Database_Teamwork/Database_Teamwork/finalProject/crimeMap.csv' INTO TABLE CountyInfo
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2, @col3,@col4) 
  set CrimeRate=@col1, CountyName =@col2,stateName =@col2,fips = @col4;
