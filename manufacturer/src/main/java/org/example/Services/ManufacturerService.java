package org.example.Services;

import org.example.Entity.PC;
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

        public PC findByID() {
                return getPC_ID();
        }

        public List<PC> findALL() {
                return List;
        }

        public PC deletePCbyID() {
                return PC -1;
        }

        public List<PC> addPC() {
                return PC;
        }
}
