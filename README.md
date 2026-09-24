# EMR — Electronic Medical Record Application

EMR is an Android application developed to provide a centralized way of storing and managing hospital and patient records.

The application was originally developed in **2021 as an academic project** using **Java and XML in Android Studio**, with **SQLite** providing local database storage.

The idea behind the project was to explore how patient records could be kept in one place instead of managing multiple records separately, while providing different ways for patients and hospitals to interact with those records.

## Features

### Patient Registration

Patients can create an account through the signup page by providing their details and choosing a unique patient ID.

The registered information is stored locally using SQLite.

### Patient Login

Registered patients can log in using their:

* Email
* Patient ID
* Password

After logging in, a patient can choose to:

* Add a medical record
* View existing records

Patients are able to view the complete details of their stored records.

### Hospital Access

The application also provides a separate workflow for hospitals.

Hospital users access a patient's records using the patient's:

* Name
* Patient ID

After accessing the patient, the hospital can either add a new record or view existing records.

Unlike patients, hospital users are only shown selected information from a patient's records.

The original prototype also requires the hospital workflow to authenticate/access the patient separately when switching between adding and viewing records.

### Adding Records

Both patients and hospitals can add records.

A new record is created by completing the required fields in the record form. The information is then stored in the application's SQLite database.

### Viewing Records

The information displayed depends on the type of user accessing it.

**Patients** can view the complete details of their records.

**Hospitals** are provided with a restricted view containing only selected record information.

## Technologies

| Technology     | Usage                            |
| -------------- | -------------------------------- |
| Java           | Application logic                |
| XML            | Android UI layouts               |
| SQLite         | Local database storage           |
| Android SDK    | Android application development  |
| Gradle         | Build configuration              |
| Android Studio | Original development environment |

## Database

The application uses **SQLite** for local data persistence.

Database creation and operations are handled through database helper classes within the Android application. These classes provide the interface between the application's Java code and the locally stored patient and medical-record data.

## Application Flow

```text
                         EMR
                          │
               ┌──────────┴──────────┐
               │                     │
            Patient               Hospital
               │                     │
          Sign Up / Login       Patient Access
               │                     │
        ┌──────┴──────┐       ┌─────┴─────┐
        │             │       │           │
   Add Record    View Records  Add Record  View Records
        │             │       │           │
        └─────────────┴───────┴───────────┘
                          │
                       SQLite
```

## Project Status

This repository contains the source code of the original **2021 version** of the project.

The project has been preserved primarily as an academic and portfolio project. Because it was developed using Android tooling and dependencies available at the time, newer versions of Android Studio, Gradle, or the Android SDK may require configuration changes before the application can be built and run.

## Important Note

This application is an **educational prototype** and is not intended for use with real patient or medical information.

The authentication, authorization, local storage, privacy, and security mechanisms used in this project should not be considered suitable for a production healthcare system.

## Future Improvements

Potential improvements to the original project include:

* Modernizing the Android project and dependencies
* Improving authentication and authorization
* Encrypting sensitive locally stored information
* Introducing stronger role-based access control
* Improving the user interface
* Migrating from local-only storage to an appropriate backend architecture
* Adding automated tests
* Improving validation and error handling

---

**Developed as an Android/Java academic project in 2021.**
