# API Automation Project

## Overview

This project provides a robust framework for automated API testing of a movie management system using TMDB (The Movie DataBase). It leverages Java, Cucumber, RestAssured, and JUnit to ensure reliable, maintainable, and readable test scenarios for API endpoints such as user authentication, list management, and movie rating.

## Standardized TestContext Keys

**How this is achieved:**

- All context keys are defined as constants in `com.inter2025api.utils.Constants`.
- Keys such as `LIST_ID_CONTEXT`, `MOVIES_CONTEXT`, `SESSION_ID_CONTEXT`, etc., are used throughout the codebase.
- Example usage:
  ```java
  testContext.set(Constants.LIST_ID_CONTEXT, listId);
  String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
  ```

**Result:**  
The project fully fulfills the goal of standardizing test context keys, making the test codebase more robust, readable, and maintainable.

## How to Run

1. **Install dependencies:**  
   Ensure you have Java 11+ and Maven

2. **Run tests:**  
   ```
   mvn clean test
   ```