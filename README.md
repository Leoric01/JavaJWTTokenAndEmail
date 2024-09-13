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
