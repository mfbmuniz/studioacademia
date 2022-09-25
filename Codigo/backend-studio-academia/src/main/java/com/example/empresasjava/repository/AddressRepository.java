package com.example.empresasjava.repository;

import com.example.empresasjava.models.Address;
import com.example.empresasjava.models.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
