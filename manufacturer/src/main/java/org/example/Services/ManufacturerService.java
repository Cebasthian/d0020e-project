package org.example.Services;

import org.example.Entity.*;
import org.example.Repositories.ComponentRepository;
import org.example.Repositories.PCRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerService {

        @Autowired
        private ComponentRepository componentRepository;

        @Autowired
        private PCRepository pcRepository;


        public PC findbyID(int ID) {
                return pcRepository.findbyID(ID);
        }

        // Hitta alla datorer och returnera som en lista
        public List<PC> findAll() {
                return pcRepository.findAll();
        }

        // Ta bort en PC
        public void deletePCbyID(int ID) {
                pcRepository.deletebyID(ID);
        }

        // Spara dator i listan/databas
        public void addPC(PC PC) {
                pcRepository.saveAndFlush(PC);
        }

        public void updatePC(PC pc) {
                pcRepository.change(pc);
        }
}
