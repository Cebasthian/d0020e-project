package org.example.Services;

import jakarta.persistence.Id;
import org.example.Entity.*;
import org.example.Repositories.PerformanceRepository;
import org.example.Repositories.ComponentRepository;
import org.example.Repositories.PCRepository;
import org.example.Repositories.WarrantyRepository;
import org.example.Repositories.EnergyclassRepository;
import org.example.Repositories.SizeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

        @Autowired
        private ComponentRepository componentRepository;

        @Autowired
        private PCRepository pcRepository;

        @Autowired
        private WarrantyRepository warrantyRepository;

        @Autowired
        private EnergyclassRepository energyclassRepository;

        @Autowired
        private SizeRepository sizeRepository;

        @Autowired
        private PerformanceRepository performanceRepository;

        public PC findByID(int ID) {

                PC pc1 = new PC();
                Long C = componentRepository.findbyID(ID);
                String EC = energyclassRepository.findbyID(ID);
                int perf = performanceRepository.findbyID(ID);
                int S = sizeRepository.findbyID(ID);
                int W = warrantyRepository.findbyID(ID);

                pc1.setPC_ID(ID);
                pc1.set_component(C);
                pc1.set_energyClass(EC);
                pc1.set_performance(perf);
                pc1.set_size(S);
                pc1.set_warranty(W);

                return pc1;
        }

        public List<PC> findALL() {
                return pcRepository.findAll();
        }

        public PC deletePCbyID() {
                return pcRepository.delete();
        }

        public List<PC> addPC() {
                return pcRepository.save();
        }
}
