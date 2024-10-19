# GoREST Test Automation

## Overview

This repository contains automated UI and API tests.
The tests are written in Java using Cucumber, JUnit, Maven, RestAssured for the API tests, and Selenium for the UI tests.
These tests provide confidence in the functionality and reliability of GoREST API services.

## Project Structure

* src/test/java: This directory contains the Java test code.
* com/eneco: This package contains the test classes.
* stepDefinitions: This package contains the Cucumber step definition classes (e.g. GetUserStepDef.java, CreateUserStepDef.java, etc.).
* requests: This package contains classes related to making API requests (e.g. CallService.java).
* utils: This package contains utility classes (e.g. LogUtils.java, PropertiesFactory.java, etc.).
* pageObjects: This package contains page object classes for UI testing (e.g. SalesFlowPageObjects.java).
* pojo: This package contains plain old Java object (POJO) classes (e.g. User.java).
* src/test/resources: This directory contains test resources.
* features: This directory contains Cucumber feature files (e.g. GetUser.feature, CreateUser.feature, etc.).
* config: This directory contains application properties (baseURI, homepage, browser), log4j and additional files (e.g. schema.txt).
* .github/workflows: This directory contains GitHub Actions workflow files (e.g. maven-cucumber.yml).
* setup.sh: Setup script for installing dependencies and configuring the project

## Code Structure

* The project uses a behavior-driven development (BDD) approach with Cucumber.
* Each feature file (e.g. GetUser.feature) contains scenarios that describe the desired behavior of the system.
* Each scenario is implemented by a step definition class (e.g. GetUserStepDef.java) that contains the Java code to execute the scenario.
* The step definition classes use utility classes (e.g. LogUtils.java, PropertiesFactory.java) and page object classes (e.g. SalesFlowPageObjects.java) to interact with the system.
* The project uses Maven as the build tool and GitHub Actions for continuous integration.

# Tech Stack

* **Cucumber**: A behavior-driven development (BDD) framework that allows for easy test creation and understanding through plain text descriptions.
* **Java:** The primary programming language for writing the test automation code.
* **RestAssured**: A Java library used to perform HTTP requests and validate responses when interacting with RESTful APIs.
* **Selenium**: A web automation tool for testing web applications
* **JUnit**: A popular testing framework for writing and executing test cases.
* **Maven**: A build automation and project management tool for managing project dependencies and building Java-based applications.

## API Documentation

For reference, the API documentation can be found at [GoREST API Documentation](https://gorest.co.in/).
This resource will provide you with in-depth details about the API endpoints and their functionality.

## Prerequisites

* Java Development Kit (JDK) 8 or later
* Maven 3.6 or later
* Git 2.24 or later
* IDE of your choice (e.g. IntelliJ IDEA, Visual Studio Code)

## Setup Instructions

* Clone the repository: Clone the project repository from GitHub using the command **git clone <repository-url>**
* Install dependencies: Run the command **mvn clean install** to install the project dependencies.
* Configure the environment: Create a **configuration.properties** file in the root directory and add the necessary property **token**
* Set up the IDE: Import the project into your IDE and configure the project settings (e.g. Java version, Maven settings).

## Additional Setup Instructions

* Cucumber plugin: Install the Cucumber plugin for your IDE to enable Cucumber-specific features (e.g. syntax highlighting, code completion).
* Environment variables: Set up environment variables (e.g. JAVA_HOME, MAVEN_HOME) to ensure that the project builds and runs correctly.

## How to Run Tests
**Note**:
You must have a configuration.properties file in the root directory. You must fill access "**token**" field in the file.

The **configuration.properties** file will look like this:
```configuration.properties
token = your-api-token
```

To execute the test suite, follow these steps:

1. Open a command prompt or terminal and navigate to the application's root folder.
2. Run the following command to clean the project and verify the tests:

   `mvn clean verify`
3. If you want to run a specific feature, you can use a feature tag to isolate specific tests. For example:

   `mvn clean verify -q -Dcucumber.filter.tags="@regression"`

# Test Report

After running the tests, you can access the test report via the following link: [Report Collection](https://reports.cucumber.io/report-collections/90f84492-f95b-4f38-b41e-693a563361a3)
