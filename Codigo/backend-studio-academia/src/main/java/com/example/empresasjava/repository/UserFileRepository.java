package com.example.empresasjava.repository;


import com.example.empresasjava.models.ResponseEntity.UserFileResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFileRepository extends JpaRepository<UserFile,Long> {

    UserFile findByUserFileIdAndUserAndDeletedAtIsNull(Long userFileId, User user);

    Page<UserFile> findAllByUserAndDeletedAtIsNull(User user, Pageable pages);

    Page<UserFile> findAllByDeletedAtIsNull(Pageable pages);

    Page<UserFile> findAllByUserAndFileNameAndDeletedAtIsNull(User user, Pageable pages, String fileName);
}
