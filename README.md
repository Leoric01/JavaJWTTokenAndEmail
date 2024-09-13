# Signup, Login with Email Verification Tutorial

## Overview

This project demonstrates a Spring Boot application for user authentication with JWT-based security. It includes user signup, login, email verification, and basic error handling.

## Features

- **User signup with email verification**
- **User login with JWT token generation**
- **User email verification flow** (send verification code, verify user)
- **Fetch authenticated user details**
- **Basic error handling and exception logging**

## Getting Started

To get started with this project, follow these steps:

1. **Clone the Repository**
    ```bash
    git clone https://github.com/yourusername/your-repository.git
    ```

2. **Navigate to the Project Directory**
    ```bash
    cd your-repository
    ```

3. **Build and Run the Application**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the Application**
    Open your browser and go to `http://localhost:8080` to see the application in action.

## Setup and Configuration

1. **Database Configuration**
    Update `application.properties` with your database configuration.

2. **Email Configuration**
    Set up your email provider in `application.properties` to enable email verification.

3. **JWT Configuration**
    Configure JWT settings in `application.properties` to match your security requirements.

## API Endpoints

- **Signup**
    - `POST /api/auth/signup`
    - Request body: `{ "email": "user@example.com", "password": "password" }`

- **Login**
    - `POST /api/auth/login`
    - Request body: `{ "email": "user@example.com", "password": "password" }`

- **Verify Email**
    - `GET /api/auth/verify-email`
    - Query parameter: `code`

- **Fetch User Details**
    - `GET /api/user`
    - Requires JWT token in `Authorization` header

## Error Handling

Basic error handling is implemented to provide meaningful error messages and handle exceptions gracefully.
