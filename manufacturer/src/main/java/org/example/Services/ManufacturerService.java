package org.example.Services;

import org.example.Entity.*;
import org.example.Repositories.ComponentRepository;
import org.example.Repositories.PCRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

        @Autowired
        private ComponentRepository componentRepository;

        @Autowired
        private PCRepository pcRepository;


        public Optional<PC> findbyID(Long ID) {
                return pcRepository.findByID(ID);
        }

        // Hitta alla datorer och returnera som en lista
        public List<PC> findAll() {
                return pcRepository.findAll();
        }

        // Ta bort en PC
        public void deletePCbyID(Long ID) {
                pcRepository.deleteById(ID);
        }

        // Spara dator i listan/databas
        public void addPC(PC PC) {
                pcRepository.saveAndFlush(PC);
        }

        public void updatePC(Long ID) {
                pcRepository.change(ID);
        }
}
