package com.example.empresasjava.models.RequestEntity;

import lombok.Data;

import java.util.List;

@Data
public class UserFileRequest {
    List<Long> exercisesIds;
    Long userId;
    Integer series;
    Integer repetitions;
}
