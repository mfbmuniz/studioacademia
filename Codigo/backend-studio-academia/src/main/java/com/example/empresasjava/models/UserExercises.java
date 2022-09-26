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
@Table(name = "user_exercises")
public class UserExercises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_exercises_id")
    Long userExercisesId;

    @ManyToOne
    @JoinColumn(name = "user_file_id")
    UserFile userFile;

    @ManyToOne
    @JoinColumn(name = "exercises_id")
    Exercise exercises;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_exercises",
            joinColumns = @JoinColumn(name = "user_file_id"),
            inverseJoinColumns = @JoinColumn(name = "exercises_id")
    )
    private List<Exercise> exercises;
   */
    @Column(name = "series")
    private Integer series;

    @Column(name = "repetition")
    private Integer repetition;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public UserExercises() {
    }

    public UserExercises(Long userExercisesId, UserFile userFile, Exercise exercises, Integer series, Integer repetition) {
        this.userExercisesId = userExercisesId;
        this.userFile = userFile;
        this.exercises = exercises;
        this.series = series;
        this.repetition = repetition;
    }

    public UserExercises( UserFile userFile, Exercise exercises, Integer series, Integer repetition) {
        this.userFile = userFile;
        this.exercises = exercises;
        this.series = series;
        this.repetition = repetition;
    }

    public static UserExercises fromUserExerciseResponse(UserExerciseResponse userExerciseResponse){

       return new UserExercises(
                userExerciseResponse.getUserFile(),
                userExerciseResponse.getExercises(),
                userExerciseResponse.getSeries(),
                userExerciseResponse.getRepetitions()
        );

    }
}
