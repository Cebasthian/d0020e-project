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
        public void addPC(String productId, String energyClass, String dimensions, String lifecycle, int powerRating, String installingInstructions, String maintenanceInstructions, String repairInstructions, String assemblyCarbonFootprint, String warranty, String componentType, String componentId, String componentName){
                PC newPc = new PC();
                newPc.setProductId(productId);
                newPc.setEnergyClass(energyClass);
                newPc.setDimensions(dimensions);
                newPc.setLifecycle(lifecycle);
                newPc.setPowerRating(powerRating);
                newPc.setInstallingInstructions(installingInstructions);
                newPc.setMaintenanceInstructions(maintenanceInstructions);
                newPc.setRepairInstructions(repairInstructions);
                newPc.setAssemblyCarbonFootprint(assemblyCarbonFootprint);
                newPc.setWarranty(warranty);
                Component newComponent = new Component();
                newComponent.setComponentId(componentId);
                newComponent.setName(componentName);
                newComponent.setComponentType(componentType);
                newPc.addComponent(newComponent);
                pcRepository.saveAndFlush(newPc);
        }

        @Transactional
        public void updatePCField(Long id, String productId, String energyClass, String dimensions, String lifecycle, int powerRating, String installingInstructions, String maintenanceInstructions, String repairInstructions, String assemblyCarbonFootprint, String warranty, int idForComponent, String componentType, String componentId, String componentName) {

                PC pc = pcRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PC not found"));
                pc.setComponent(idForComponent, componentType, componentId, componentName);
                if(productId != null){
                        //pcRepository.updateProductid(id, productId);
                        pc.setProductId(productId);
                }
                if(energyClass != null){
                        pc.setEnergyClass(energyClass);
                }
                if(dimensions != null){
                        pc.setDimensions(dimensions);
                }
                if(lifecycle != null){
                        pc.setLifecycle(lifecycle);
                }
                if(powerRating != 0){
                        pc.setPowerRating(powerRating);
                }
                if(installingInstructions != null){
                        pc.setInstallingInstructions(installingInstructions);
                }
                if(maintenanceInstructions != null){
                        pc.setMaintenanceInstructions(maintenanceInstructions);
                }
                if(repairInstructions != null){
                        pc.setRepairInstructions(repairInstructions);
                }
                if(assemblyCarbonFootprint != null){
                        pc.setAssemblyCarbonFootprint(assemblyCarbonFootprint);
                }
                if(warranty != null){
                        pc.setWarranty(warranty);
                }
                pcRepository.save(pc);
        }
}
