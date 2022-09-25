package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Cities;
import com.example.empresasjava.repository.CitiesRepository;
import com.example.empresasjava.service.CityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CitiesRepository citiesRepository;

    @Override
    public Cities findByCity(String city) {
        return this.citiesRepository.findByCity(city)
                .orElseGet(() -> this.citiesRepository.save(new Cities(city)));
    }
}
