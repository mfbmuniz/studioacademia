package com.example.empresasjava.models.ResponseEntity;

import lombok.Data;

@Data
public class ExerciseResponse {
    private String name;
    private String exerciseUrl;
    private String description;

    public ExerciseResponse() {
    }

    public ExerciseResponse(String name, String exerciseUrl, String description) {
        this.name = name;
        this.exerciseUrl = exerciseUrl;
        this.description = description;
    }
}
