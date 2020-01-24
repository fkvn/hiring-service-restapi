# Hiring Process Service

The faculty hiring process is fairly long and complicated.

The goal of the project is to develop a REST API services and application (with UI, if applicable) to facilitate the faculty hiring process.

## Getting Started

This project is developed based on a school project of "Advanced Topics in Web Programming" class for master students at California State University, Los Angeles - 2019. 

The main purpose is for learning how to implement Rest API backend with Spring Boot application.

* [Data Requirements](/DataRequirements.md)
* [Data Modeling with ER Diagram](/ERDiagram.md)
* [Process Requirements](/ProcessRequirements.md)

### Prerequisites

* Java: JDK 8 or above.
* IDE: Eclipse (recommended), IntelliJ IDEA or any IDE supports running Java application.
* Database: MySQL 8 
* Database Management Tools: MySQL Workbench or any equivalent alternatives.

### Installing

To import the project into Eclipse, first clone the project into a local Git repository, then in Eclipse, use File -> Import ... -> Maven -> Existing Maven Projects to import the project into Eclipse.

```
1. Import the project into Eclipse by following the instructions below
```

1. Clone the project to your local disk (or your workspace in Eclipse if needed).
1. Open Eclipse and import project via File - Import - Maven - Existing Maven Projects.

```
2. Set up Database connection
```
1. Go to "src/main/resources"
1. Edit name of "application.properties.sample" to "application.properties"
1. fill out the database information needed.

```
3. Run the project as Java application 
```

## Running the tests

Install Postman in order to test the API implementations.

Go to the URL "http://localhost:8080/<api_design_mapping>" via Postman.

## Deployment

Add additional notes about how to deploy is updated soon.

## Built With

* [SpringBoot](http://start.spring.io) - The web framework used
* [Eclipse](https://www.eclipse.org/downloads/packages/release/indigo/sr2/eclipse-ide-java-ee-developers) - IDE supported
* [MySQL](https://dev.mysql.com/downloads/mysql/) - The database environment used
* [Postman](https://www.getpostman.com) - API development

## Contributing

When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.

Update the README.md with details of changes to the interface, this includes new environment variables, exposed ports, useful file locations and container parameters.
 

## Authors

* **Kevin Ngo** - *Initial work* - [fkvn](https://github.com/fkvn)

See also the list of [contributors](https://github.com/fkvn/hiring-service-restapi/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details

## Acknowledgments

* Inspiration: Chengyu Sun, PH.D. - Professor of Department of Computer Science - California State University, Los Angeles




 
