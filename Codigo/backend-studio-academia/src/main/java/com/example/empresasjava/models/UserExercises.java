package com.example.empresasjava.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(name = "series")
    private Integer series;

    @Column(name = "repetition")
    private Integer repetition;

    public UserExercises() {
    }

    public UserExercises(Long userExercisesId, UserFile userFile, Exercise exercises, Integer series, Integer repetition) {
        this.userExercisesId = userExercisesId;
        this.userFile = userFile;
        this.exercises = exercises;
        this.series = series;
        this.repetition = repetition;
    }
}
