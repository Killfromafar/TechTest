## Overview
This is my solution to the interview test project for Matillion

This project is a Spring Boot Application and built using Maven

This project depends on the MySQL connector for the live version and H2 for creating in-memory tables for testing

This project is targeted towards Java 11.

## Installing
Running install.sh will create an executable jar called `querydb-1.0-SNAPSHOT.jar` in the `target` directory.

## Running
This is designed to be run from a terminal.

After creating the executable jar either by following the instructions in [Installing](#Installing) or building it yourself - it can be ran using the `run.sh` script

The application will read environment variables to determine what the values for the query should be.

All query parameters must exist as environment variables or else it will error.

The environment variables that must be set are `department`, `pay` and `education`.

The environment variables can have no value.

If no value has been set then it will be treated as a wildcard.

For example running the following in your terminal:
```
export department='HQ General Management'
export pay=
export education=''
./run.sh
```
Will cause the application to find all employees whose department description is `HQ General Management`

The results will be printed out in the terminal.

## Testing
To run the tests run the `test.sh` script