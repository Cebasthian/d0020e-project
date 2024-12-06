package org.example.Services;

import org.example.Repositories.PerformanceRepository;
import org.example.Repositories.ComponentRepository;
import org.example.Repositories.PCRepository;
import org.example.Repositories.WarrantyRepository;
import org.example.Repositories.EnergyclassRepository;
import org.example.Repositories.SizeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        public void findByID() {
        }

        public void findALL() {
        }

        public void deletePCbyID() {
        }

        public void addPC() {
        }
}
