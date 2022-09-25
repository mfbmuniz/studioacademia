package com.example.empresasjava.models;


import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.UserExerciseResponse;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
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
    private Long user_file_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_exercises",
            joinColumns = @JoinColumn(name = "user_file_id"),
            inverseJoinColumns = @JoinColumn(name = "exercises_id")
    )
    private List<Exercise> exercises;

    public UserFile() {
    }

    public UserFile(User user, List<Exercise> exercises) {
        this.user = user;
        this.exercises = exercises;
    }


    public static UserFile fromUserExerciseResponse(UserExerciseResponse userExerciseResponse){
        UserRequest a = new UserRequest();
//        new UserFile(
//            User.fromUserResponse(userExerciseResponse.getUser()),
//            userExerciseResponse.getExercises()
//        )
        return null;
    }
}
