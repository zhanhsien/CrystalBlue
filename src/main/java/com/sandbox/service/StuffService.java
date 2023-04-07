package com.sandbox.service;

import com.sandbox.api.model.Stuff;
import com.sandbox.repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StuffService {

    StuffRepository stuffRespository;

    @Autowired
    public StuffService(StuffRepository stuffRepository) {
        this.stuffRespository = stuffRepository;
    }

    public Stuff getStuff(String id) {
        Optional<Stuff> stuff = stuffRespository.findById(id);
        return stuff.orElse(null);
    }
}
