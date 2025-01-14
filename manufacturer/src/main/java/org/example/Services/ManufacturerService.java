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
                // använd repositoryns egna metod
//                return pcRepository.findById(ID);


                PC pc1 = new PC();
                Long C = componentRepository.findComponent(ID);
               // String EC = energyclassRepository.findbyID(ID);
                //int perf = performanceRepository.findbyID(ID);
                //int S = sizeRepository.findbyID(ID);
                //int W = warrantyRepository.findbyID(ID);

                pc1.setPC_ID(ID);
                pc1.add_component(C);
                //pc1.set_energyClass(EC);
                //pc1.set_performance(perf);
                //pc1.set_size(S);
                //pc1.set_warranty(W);

                return pc1;
        }

        // Hitta alla datorer och returnera som en lista
        public List<PC> findALL() {
//                return pcRepository.findAll();

                int x = 0;
                PC temp = pcRepository.findbyID(x);
                return new ArrayList<PC>();
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
}
