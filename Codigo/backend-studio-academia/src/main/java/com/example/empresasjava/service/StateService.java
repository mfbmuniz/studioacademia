package com.example.empresasjava.service;

import com.example.empresasjava.models.States;
import javassist.NotFoundException;

public interface StateService {

    States findByUf(String uf) throws NotFoundException;
}
