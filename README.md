# CategorisationSpringBoot
 # Problem
You are a software engineer consulted by a local company to solve a significant problem on one of their platforms regarding the data structure around category and product entities. The future platform to be implemented will host the hierarchical structuring of various supermarkets in the area:

    AUCHAN: 2 levels of categorization
    Exclusive Hypermarket: 3 levels of categorization
    UTILE: 2 levels of categorization
    Sakanal: 4 levels of categorization

Therefore, you must design an evolutionary, scalable database that can adapt without needing amendment to any supermarket regardless of their level of categorization: N levels with N ranging from 1 to several.
A product is linked to a category, and a category can be linked to 0 or several.
The required database is PostgreSQL.
The module must be written in Spring with the MVC architecture, integrating the various interfaces of IN (data entry) and OUT (data display).
Considering the issues of scaling, propose an OUT logic that can prevent this type of problem.
Taking into account that you are not immortal, set up documentation for your code or business logic and your web services if existing.
## Project Documentation: Categorization System
# Overview

This document provides a comprehensive overview of the Categorization System, detailing the implementation of a nested set model for hierarchical data management. The system facilitates efficient retrieval and manipulation of category data, essential for operations that require hierarchical structures such as e-commerce platforms, content management systems, and organizational charts.
System Architecture
Database

    PostgreSQL: Chosen for its robust support for hierarchical queries and transactional integrity.
    Nested Set Model: Utilized for representing hierarchical data within a single table, providing efficient read operations.

# Backend

    Java Spring Boot: Serves as the backend framework, offering a simplified mechanism for RESTful API development and dependency management.
    JPA (Java Persistence API): Manages relational data in Java applications.
    Hibernate: Implemented as the JPA provider for object-relational mapping.
    MapStruct: Automates the mapping between DTOs and entity classes, enhancing code maintainability.

# Key Components
Entity Models

    Category: Represents nodes within the category tree, containing id, name, left, and right fields for nested set implementation.

DTOs

    CategoryDTO: Data Transfer Object used for creating and updating categories via the API.

Repositories

    CategoryRepository: Extends JpaRepository, containing custom methods for nested set manipulations like incrementLeftValues, incrementRightValues, and findMaxRightValue.

Services

    CategoryService: Handles the business logic for category management, including CRUD operations and nested set adjustments.

Controllers

    CategoryController: Processes incoming HTTP requests, invokes service layer operations, and returns HTTP responses.

Mappers

    CategoryMapper: Interface for MapStruct to map between Category entities and CategoryDTO.

# Implementation Details
# CRUD Operations

    Create: Inserts a new category, automatically calculating left and right values based on the provided parent ID.
    Read: Retrieves categories, supporting efficient hierarchical data retrieval using the nested set model.
    Update: Modifies an existing category, updating fields and re-indexing nested set values as necessary.
    Delete: Removes a category, adjusting left and right values of remaining categories to maintain the nested set integrity.

# Transaction Management

    Ensured transactional integrity during nested set updates to prevent data corruption during concurrent modifications.

# Error Handling

    Implemented global exception handling to manage errors such as EntityNotFoundException.

# Logging

    Utilized ILogger for critical and informational logging throughout the application lifecycle.

# Challenges and Solutions

    Concurrency: Addressed potential race conditions with transactional annotations and database isolation levels.
    Nested Set Complexity: Simplified nested set manipulations by encapsulating logic within repository methods.
    Performance: Ensured efficient reads by leveraging the nested set model, and writes through careful transaction management.

# Future Enhancements

    Caching: Plan to implement caching for read-heavy operations to further improve performance.
    Frontend Integration: Aiming to develop a dynamic user interface to manage the category hierarchy visually.

# Conclusion

The Categorization System is a robust solution for managing hierarchical data, leveraging the power of the nested set model and modern Java technologies. This document serves as a guideline for understanding the system's implementation and will be updated as the system evolves.
