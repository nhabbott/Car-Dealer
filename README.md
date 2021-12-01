# Compilation Instructions
In order to make vehicles and listings persistent, we need a third-party library. This library has already been added to .classpath file of this eclipse project. 
To make the project compile, you need to create the following folder located at C:\JARs\Hibernate. 

Next, download the Hibernate JAR files from https://sourceforge.net/projects/hibernate/files/hibernate-orm/5.5.7.Final/hibernate-release-5.5.7.Final.zip/download
Then put all JAR files (found in the /lib directory of the ZIP file) in the Hibernate folder that was just created.

Then, download the MySQL JDBC connector JAR from https://dev.mysql.com/downloads/connector/j/
You must select "Platform Independent" for the Operating System. Then place the JAR in C:\JARs

After that, download the JAR from http://www.java2s.com/Code/JarDownload/com.google/com.google.guava_1.6.0.jar.zip
Then place the JAR in C:\JARs

Next, download the Javax Mailer JAR from https://github.com/javaee/javamail/releases/download/JAVAMAIL-1_6_2/javax.mail.jar
Then place the JAR in C:\JARs

After doing this, you should be able to compile like normal.

# Backend Instructions
This project requires the use of a MySQL Server. It must have the following:
1. A database named carsdb.
2. A user named cars with a password of TheSUperSecurePassword123! and it must have all permissions granted for the carsdb database.
3. The carsdb databse must be initialized with the initDB.sql file that can be found in the root of this project's directory.