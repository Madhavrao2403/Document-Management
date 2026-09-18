# 📁 Document Management API

A RESTful Document Management API built using **Java and Spring Boot**.

This project allows users to upload documents, store the actual files on the filesystem, save file metadata in MySQL, retrieve metadata, and download uploaded files.

The main goal of this project is to learn **file handling and multipart requests in Spring Boot**.

---

## 🚀 Features

- 📤 Upload files using `multipart/form-data`
- 📁 Store actual files in the local filesystem
- 🔐 Generate unique filenames using UUID
- 🗄️ Store file metadata in MySQL
- 📋 Get metadata of all uploaded files
- 🔎 Get metadata of a specific file
- 📥 Download uploaded files
- ✅ Validate empty files
- 📏 Validate maximum file size
- 📄 Validate allowed file extensions
- ⚙️ Configure multipart file size limits using Spring Boot

---

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Postman

---

## 📚 Concepts Learned

### 1. MultipartFile

Used Spring Boot's `MultipartFile` to receive uploaded files.

```java
@RequestParam("file") MultipartFile file
2. multipart/form-data

Files are uploaded using:

multipart/form-data

instead of:

application/json
3. File Metadata

The following information is stored for every uploaded file:

Original filename
Stored filename
Content type
File size
File path
4. Filesystem Storage

Java NIO APIs are used to store files:

Path uploadPath = Path.of("uploads");

Files.createDirectories(uploadPath);

Path filePath = uploadPath.resolve(storedName);

Files.copy(file.getInputStream(), filePath);
5. Unique Filenames using UUID

To avoid filename conflicts, UUID is used:

String uniqueId = UUID.randomUUID().toString();

The stored filename becomes:

UUID_originalFilename

Example:

550e8400-e29b-41d4-a716-446655440000_Madhav_s_Resume.pdf
6. Database Metadata

The actual file is stored in the filesystem, while its metadata is stored in MySQL.

Filesystem
    ↓
Actual File

MySQL
    ↓
File Metadata
7. Resource and UrlResource

Spring's Resource and UrlResource are used to return files to the client.

Resource resource =
        new UrlResource(Path.of(fileMetaData.getFilePath()).toUri());
8. File Download

Files are returned using:

ResponseEntity<Resource>

and the response includes:

Content-Type
Content-Disposition
9. File Validation

The API validates:

Empty files
Files larger than 10 MB
Unsupported file extensions

Allowed extensions:

pdf
jpg
jpeg
png
docx
10. Multipart Configuration

Spring Boot upload limits are configured using:

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
📂 Project Structure
DocumentManagement
│
├── src
│   └── main
│       ├── java
│       │   └── com.mini.DocumentManagement
│       │       │
│       │       ├── controller
│       │       │   └── FileController.java
│       │       │
│       │       ├── model
│       │       │   └── FileMetaData.java
│       │       │
│       │       └── Repository
│       │           └── FileMetaDataRepository.java
│       │
│       └── resources
│           └── application.properties
│
├── uploads
│   └── uploaded files
│
├── pom.xml
└── README.md
🔗 API Endpoints
1. Upload File
POST /files/upload
Postman

Select:

Body → form-data

Add:

Key: file
Type: File
Value: Select a file

Example:

POST http://localhost:8080/files/upload
2. Get All File Metadata
GET /files

Example:

GET http://localhost:8080/files

Returns metadata of all uploaded files.

3. Get File Metadata by ID
GET /files/{id}

Example:

GET http://localhost:8080/files/1
4. Download File
GET /files/{id}/download

Example:

GET http://localhost:8080/files/1/download

The file is returned as a downloadable resource.

🗄️ Database

The project uses MySQL to store file metadata.

Example metadata:

Field	Example
id	1
originalName	Madhav_s_Resume.pdf
storedName	UUID_Madhav_s_Resume.pdf
contentType	application/pdf
size	245678
filePath	uploads/UUID_Madhav_s_Resume.pdf

The actual file is not stored inside MySQL. Only its metadata and filesystem path are stored.

⚙️ Configuration

Example application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/document_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

Update the database username and password according to your MySQL configuration.

🧪 Testing

Postman can be used to test the API.

Upload
POST /files/upload
List Metadata
GET /files
Get Metadata
GET /files/1
Download
GET /files/1/download
📌 Example Workflow
                Upload File
                     ↓
              MultipartFile
                     ↓
               Validation
                     ↓
              Generate UUID
                     ↓
          Save file to uploads/
                     ↓
           Create FileMetadata
                     ↓
              Save to MySQL
                     ↓
              Return Metadata

For downloading:

             GET /files/{id}/download
                       ↓
                Find metadata
                       ↓
                  Get filePath
                       ↓
                  UrlResource
                       ↓
              ResponseEntity<Resource>
                       ↓
                 Download File
🎯 Learning Outcome

After completing this project, I learned how to:

Handle file uploads in Spring Boot
Work with MultipartFile
Understand multipart/form-data
Work with Java NIO Path and Files
Store files on the filesystem
Generate unique filenames using UUID
Store file metadata using Spring Data JPA
Retrieve files using Resource
Return files using ResponseEntity<Resource>
Configure multipart upload limits
Implement basic file validation
🏷️ Challenge

Day 8/50 — Java + Spring Boot Mini Project Challenge
