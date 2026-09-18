package com.mini.DocumentManagement.controller;

import com.mini.DocumentManagement.Repository.FileMetaDataRepository;
import com.mini.DocumentManagement.model.FileMetaData;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileMetaDataRepository fileMetaDataRepository;

    public FileController(FileMetaDataRepository fileMetaDataRepository) {
        this.fileMetaDataRepository = fileMetaDataRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileMetaData> upload(@RequestParam("file") MultipartFile file) throws IOException {

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        Set<String> allowedExtensions = Set.of("pdf", "jpg", "jpeg", "png", "docx");
        if(file.isEmpty() ||  file.getSize()>10 * 1024 * 1024 || !allowedExtensions.contains(extension)) {
            throw new IOException("Invalid file extension");
        }

        Path uploadPath = Path.of("uploads");
        Files.createDirectories(uploadPath);
        String uniqueId = UUID.randomUUID().toString();
        String storedName = uniqueId + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(storedName);
        Files.copy(file.getInputStream(), filePath);

        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setOriginalName(file.getOriginalFilename());
        fileMetaData.setStoredName(storedName);
        fileMetaData.setContentType(file.getContentType());
        fileMetaData.setSize(file.getSize());
        fileMetaData.setFilePath(filePath.toString());
        fileMetaDataRepository.save(fileMetaData);
        URI location = URI.create("/files/" + fileMetaData.getId());
        return ResponseEntity.created(location).body(fileMetaData);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws MalformedURLException {
        FileMetaData fileMetaData = fileMetaDataRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
        Resource resource = new UrlResource(Path.of(fileMetaData.getFilePath()).toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileMetaData.getContentType()))
                .header("Content-Disposition","attachment; filename=\""+fileMetaData.getOriginalName()+"\"")
                .body(resource);
    }

    @GetMapping
    public ResponseEntity<List<FileMetaData>> getAllMetaData() {
        return ResponseEntity.ok(fileMetaDataRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetaData> getMetaData(@PathVariable Long id) {
        return ResponseEntity.ok(fileMetaDataRepository.findById(id).orElseThrow(()->new RuntimeException("id not found")));
    }
}
