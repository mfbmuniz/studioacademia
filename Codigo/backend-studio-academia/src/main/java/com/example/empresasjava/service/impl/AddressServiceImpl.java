package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Cities;
import com.example.empresasjava.repository.CitiesRepository;
import com.example.empresasjava.service.AddressService;
import com.example.empresasjava.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CitiesRepository citiesRepository;

}
