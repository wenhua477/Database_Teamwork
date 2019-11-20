USE StargazingApp;

LOAD DATA LOCAL INFILE '/Users/yaowenhua/Documents/5200/Database_Teamwork/finalProject/Campsites.csv' INTO TABLE StarGazingPlaces
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1,@col2,@dummy, @col4) set Latitude=@col1,Longitude=@col2,State=@col4;
  
  
LOAD DATA LOCAL INFILE '/Users/yaowenhua/Documents/5200/Database_Teamwork/finalProject/Campsites.csv' INTO TABLE Campsites
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@col1, @col2, @col3,@dummy,@col5,@col6) set Name=@col3,Type=@col6,Capacity=@col5;
  
  
