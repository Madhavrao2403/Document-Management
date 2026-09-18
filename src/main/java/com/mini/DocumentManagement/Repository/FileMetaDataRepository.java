package com.mini.DocumentManagement.Repository;

import com.mini.DocumentManagement.model.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetaDataRepository extends JpaRepository<FileMetaData,Long> {
}
