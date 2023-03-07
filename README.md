# Mini Orm Framework

This is a mini ORM (Object-Relational Mapping) framework built in Java, which demonstrates how to map Java classes to database tables and perform basic CRUD (Create, Read, Update, Delete) operations using JDBC (Java Database Connectivity). The framework uses the Osoba (Person) model, which represents a person with basic information such as name, email, and phone number.

## The framework is built with the following technologies

* Java 8<br/>
* JDBC<br/>

## This application demonstrates<br/>

* How to create a basic ORM framework in Java<br/>
* How to map Java classes to database tables using annotations<br/>
* How to perform basic CRUD operations using JDBC<br/>

## Requirements<br/>
* JDK 8 or higher<br/>
* MySQL 8.0 or higher<br/>

## Running the Application<br/>
* Clone the repository: git clone https://github.com/MartinDobos22/MiniOrmFramework.git<br/>
* Navigate to the project directory: cd MiniOrmFramework<br/>
* Open application.properties file and update the MySQL database details according to your system configuration.<br/>
* Build the project: mvn clean package<br/>
* Run the Demo class to test the CRUD operations.<br/>
  
## Usage<br/>
The framework provides a Repository class which contains methods for performing CRUD operations on the database. To use the framework, follow these steps:

* Create a new instance of the Repository class: Repository<Osoba> repository = new Repository<>(Osoba.class);<br/>
* Use the save method to insert or update an object: repository.save(osoba);<br/>
* Use the findById method to retrieve an object by ID: Osoba osoba = repository.findById(1);<br/>
* Use the findAll method to retrieve all objects: List<Osoba> osobe = repository.findAll();<br/>
* Use the delete method to delete an object: repository.delete(osoba);<br/>
  
## Contribution
If you find any issues with this mini ORM framework or want to contribute to this project, please feel free to submit a pull request.



