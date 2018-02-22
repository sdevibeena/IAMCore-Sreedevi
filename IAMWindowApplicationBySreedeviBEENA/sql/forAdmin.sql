
/*These are the queries used to create the shema and table for Admin. Please do not execute this, since everything is inside 
 *the embedded derby and will run automatically when you will run the program.
 */
CREATE SCHEMA "ROOT"

CREATE TABLE ADMIN (USER_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY
					 CONSTRAINT USER_PK PRIMARY KEY, USER_NAME VARCHAR(255) UNIQUE, PASSWORD VARCHAR(255),
		            PRIVILEGE VARCHAR(255));
		            
INSERT INTO ADMIN( USER_NAME, PASSWORD, PRIVILEGE) VALUES ('admin','admin','ADMIN')

/*Name of the database is UserTable*/