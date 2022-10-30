package com.example.empresasjava.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;



//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_file_id")
////    @JoinTable(
////            name = "user_exercises",
////            joinColumns = @JoinColumn(name = "user_file_id", insertable = false, updatable = false),
////            inverseJoinColumns = @JoinColumn(name = "exercises_id", insertable = false, updatable = false)
////    )
//    private List<UserExercises> exercises;


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
