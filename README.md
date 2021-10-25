# HomeSensors

Section 1:

1. Read Temperature Data via the sensor using PI using Python

2. Send out temperature data to google server(see section 2)

Section 2. 

1. Get an google server. 
      
      create account:       clout.test.yanzhe.2021@gmail.com
      
      Get a google server: https://cloud.google.com/
      


   Set up environments: 
   
      Google Cloud:
      
        Config server to accept requests on port 8080
        
      JRE
        
        sudo apt update
        
        sudo apt install default-jre
        
      Install MYSQL:
      
        install wget: sudo apt install wget 
        
        get mysql: wget http://repo.mysql.com/mysql-apt-config_0.8.13-1_all.deb
        
        sudo apt install mariadb-server
        
        sudo mysql_secure_installation
        
        sudo mysql
        
        GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'timlilt1' WITH GRANT OPTION;
        
      Create DB and Tables:
        
        CREATE DATABASE `bc`;
        
        use bc;

        CREATE TABLE `temperature_sensors` (
          `id` int(11) NOT NULL AUTO_INCREMENT,
          `target` varchar(45) NOT NULL,
          `ambient` varchar(45) NOT NULL,
          `time_collected` varchar(45) DEFAULT NULL,
          `comments` varchar(45) DEFAULT NULL,
          `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
          `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
          PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1;
        

2. Write a java server to accept incoming requests and write the data into DB

      Use Springboot to quickly create a web server to listen to port 8080. check the code in miniservice

      Run server: nohup java -Xms1024M -jar miniservices-1.0-SNAPSHOT.jar &
   

Section 3. 

Server monitors the status/data of the temperatures. Whenever there is an alarm triggered, the program should call mom's cell phone and send out the txt.

TODO:

1. More sensors to protect home

      a. Adding video support
      
      b. AS dectection
  
      c. Sound/motion detection sensors
  
      d. sensor could check the temperatures of the oven at home, and students in the school. 
      
2. Add AI plugs

  a. in the server, once we have lots of data, we can monitor and forecast the flu
  
  b. we can use AI to study the data and auto configurate the alerts models
  
3. Control 

  a. It would be perfect if we can turn off the oven remotely.
