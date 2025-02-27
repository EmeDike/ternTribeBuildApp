﻿# ternTribeBuildApp
ModifiedApplication

ModifiedApplication is a Spring Boot-based application designed to facilitate the management of charitable causes. This application provides APIs to create, update, delete, and retrieve causes, while also allowing users to contribute to existing causes.
Table of Contents

    Features
    Technologies Used
    Project Structure
    Prerequisites
    Setup and Installation
    API Documentation
        Create Cause
        Update Cause
        Find Cause by ID
        Get All Causes
        Delete Cause
        Contribute to a Cause
    Future Enhancements
    Contributing
    License

Features

    Create Causes: Add a new cause with details such as title, description, and image.
    Update Causes: Modify existing cause details.
    View Causes: Retrieve a cause by ID or list all causes.
    Delete Causes: Remove a cause by its unique ID.
    Contribute to Causes: Allow users to contribute to specific causes.

Technologies Used

    Java: Programming language.
    Spring Boot: Framework for RESTful API development.
    Lombok: Reduces boilerplate code in data classes.
    Maven: Build automation tool.
    H2 Database: In-memory database for development.

Project Structure

src/
├── main/
│   ├── java/
│   │   └── org.ModifiedApplication.dike/
│   │       ├── controller/
│   │       │   └── CauseController.java
│   │       ├── dtos/
│   │       │   ├── request/
│   │       │   │   └── CreateCauseRequest.java
│   │       │   ├── response/
│   │       │   │   └── CreateCauseResponse.java
│   │       ├── model/
│   │       │   ├── Cause.java
│   │       │   ├── Contributions.java
│   │       ├── exceptions/
│   │       │   └── CauseException.java
│   │       ├── repositories/
│   │       │   ├── CauseRepository.java
│   │       │   ├── ContributionRepository.java
│   │       ├── services/
│   │       │   ├── CauseService.java
│   │       │   ├── CauseServiceImpl.java

Prerequisites

Ensure the following tools are installed:

    Java 11 or higher
    Apache Maven
    Postman (for API testing, optional)

Setup and Installation

    Clone the Repository:

git clone https://github.com/EmeDike/ModifiedApplication.git
cd ModifiedApplication

Build the Application:

mvn clean install

Run the Application:

    mvn spring-boot:run

    Access the Application: The application runs at http://localhost:8080.

API Documentation
1. Create Cause

    Endpoint: POST /createCause
    Request Body:

{
  "title": "Help the Homeless",
  "description": "Providing shelter and food for the homeless.",
  "imageUrl": "http://example.com/image.jpg"
}

Response:

    {
      "id": 1,
      "message": "Cause created successfully"
    }

2. Update Cause

    Endpoint: PUT /updateCause
    Request Body:

{
  "id": 1,
  "title": "Help the Homeless - Updated",
  "description": "Updated shelter and food drive details.",
  "imageUrl": "http://example.com/updated-image.jpg"
}

Response:

    {
      "id": 1,
      "message": "Cause updated successfully"
    }

3. Find Cause by ID

    Endpoint: GET /findCause/{id}
    Path Parameter: id (Long)
    Response:

    {
      "id": 1,
      "title": "Help the Homeless",
      "description": "Providing shelter and food for the homeless.",
      "imageUrl": "http://example.com/image.jpg"
    }

4. Get All Causes

    Endpoint: GET /findAllCause
    Response:

    [
      {
        "id": 1,
        "title": "Help the Homeless",
        "description": "Providing shelter and food for the homeless.",
        "imageUrl": "http://example.com/image.jpg"
      }
    ]

5. Delete Cause

    Endpoint: DELETE /deleteCause/{id}
    Path Parameter: id (Long)
    Response:

    {
      "message": "Cause with ID {id} deleted successfully"
    }

6. Contribute to a Cause

    Endpoint: POST /contributeCause
    Request Body:

{
  "contributorName": "John Doe",
  "amount": 1000
}

Query Parameter: causeId (Long)
Response:

    {
      "contributorName": "John Doe",
      "amountContributed": 1000,
      "remainingAmount": 49000
    }

Future Enhancements

    Add authentication and authorization.
    Implement email notifications.
    Build a front-end interface.
    Introduce recurring contribution support.

Contributing

Contributions are welcome! Please follow these steps:

    Fork the repository.
    Create a new branch: git checkout -b feature-name.
    Commit your changes: git commit -m "Description of changes".
    Push to the branch: git push origin feature-name.
    Open a pull request.

License

This project is licensed under the MIT License.
