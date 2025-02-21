package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
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
                return pcRepository.findById(ID);
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

        @Transactional
        public void updatePCField(Long id, String fieldName, String value) {
                PC pc = pcRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PC not found"));


                switch (fieldName) {
                        case "productId":
                                pc.setProductId(value);
                                break;
                        case "energyClass":
                                pc.setEnergyClass(value);
                                break;
                        case "dimensions":
                                pc.setDimensions(value);
                                break;
                        case "lifecycle":
                                pc.setLifecycle(value);
                                break;
                        case "powerRating":
                                pc.setPowerRating(Integer.parseInt(value));
                                break;
                        case "installingInstructions":
                                pc.setInstallingInstructions(value);
                                break;
                        case "maintenanceInstructions":
                                pc.setMaintenanceInstructions(value);
                                break;
                        case "repairInstructions":
                                pc.setRepairInstructions(value);
                                break;
                        case "assemblyCarbonFootprint":
                                pc.setAssemblyCarbonFootprint(value);
                                break;
                        case "warranty":
                                pc.setWarranty(value);
                                break;
                        default:
                                throw new IllegalArgumentException("Invalid field name");
                }

                pcRepository.save(pc);
        }
}
