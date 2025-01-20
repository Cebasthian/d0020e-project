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


        public PC findByID(int ID) {
                return pcRepository.findbyID(ID);
        }

        // Hitta alla datorer och returnera som en lista
        public List<PC> findALL() {
                return pcRepository.findAll();
        }

        // Ta bort en PC
        public void deletePCbyID(int ID) {
                pcRepository.deletebyID(ID);
        }

        // Spara dator i listan/databas
        // det funkar men skulle bara vela ändra parameterns namn då det tekniskt sett inte är ett ID man skickar utan hela datorn.
        public void addPC(PC ID) {
                pcRepository.save(ID);
        }

        // behövs en updatePc metod också som jag kommenterade i ManufacturerController
        public void updatePC(PC pc) {
                pcRepository.change(pc);
        }
}
