package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "user_file")

public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_file_id")
    private Long userFileId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "file_name")
    private String fileName;


    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "deleted_at")
    private Date deleted_at;



    public UserFile(User user, @NotNull(message = "Campo user não pode ser nulo") @NotEmpty(message = "Campo user não pode ser vazio") String fileName) {
        this.user = user;
        this.fileName = fileName;
    }

    public UserFile(User user) {
        this.user = user;

    }
    public UserFile() {

    }
}
