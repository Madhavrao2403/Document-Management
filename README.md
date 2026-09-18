# 📁 Document Management API

A RESTful Document Management API built with **Java and Spring Boot** for uploading, storing, managing, and downloading files.

## 🚀 Features

- 📤 File upload using `MultipartFile`
- 📁 Filesystem storage
- 🔐 Unique filenames using UUID
- 🗄️ File metadata stored in MySQL
- 📋 Get all file metadata
- 🔎 Get metadata by ID
- 📥 Download files
- ✅ File validation
- 📏 10 MB upload limit
- 📄 Allowed file types: PDF, JPG, JPEG, PNG, DOCX

## 🛠️ Technologies

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Maven
- Postman

## 🔗 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/files/upload` | Upload a file |
| GET | `/files` | Get all file metadata |
| GET | `/files/{id}` | Get file metadata |
| GET | `/files/{id}/download` | Download a file |

## 📚 Concepts Learned

- `MultipartFile`
- `multipart/form-data`
- Java `Path` and `Files`
- `Files.copy()`
- UUID-based unique filenames
- Filesystem file storage
- JPA metadata storage
- `Resource` and `UrlResource`
- `ResponseEntity<Resource>`
- `Content-Disposition`
- File validation
- Multipart configuration

## ⚙️ Configuration

```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
spring.jpa.hibernate.ddl-auto=update
```

## 📂 Storage

Actual files are stored in:

```text
uploads/
```

File metadata is stored in **MySQL**.

## 🎯 Learning Outcome

Learned how to build a file management system using Spring Boot with filesystem storage, database metadata, file validation, and file downloads.

## 🏷️ Challenge

**Day 8/50 — Java + Spring Boot Mini Project Challenge**
