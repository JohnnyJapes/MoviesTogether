SpringBoot Project for website which can let users create watchlists and schedule watch events with other users.

This project utilizes TMDB's API but is not affiliated with them in any way.

To run locally it must be run as a spring boot application. This can be done in VsCode with the Springboot Extension. 
The project currently expects a database to be on a local MySQL database called MoviesTogether with root credentials. These credentials can be changed in resources/application.properties file.
SQL to create the database in included in the SQL folder.

Import movieDB.csv onto your movies table, matching the appropriate fields.
You will need to provide your own API key for TMDB.
place a file called apikeys.evn in your classpath which looks like this:
TMDBAPI=your_api_key_here

Prerequisites

Springboot
MySQL

Some Examples:
![Screenshot 2025-04-02 at 07-00-56 MoviesTogether - WatchLists](https://github.com/user-attachments/assets/a48b4138-52d7-43d7-8e85-b264209c8ddb)
![Screenshot 2025-04-02 at 07-03-59 Events](https://github.com/user-attachments/assets/f76ea822-1641-4240-86c4-e31846a12103)
