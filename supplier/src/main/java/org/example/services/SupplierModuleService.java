package org.example.services;

import org.example.entity.Component;
import org.example.entity.Supplier;
import org.example.repository.ComponentRepository;
import org.example.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierModuleService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ComponentRepository componentRepository;

    /*
    * ##############################################################################
    * Supplier methods:
    * ##############################################################################
    */

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }


    /*
    * ##############################################################################
    * Controller methods: (maybe make separate files...)
    * ##############################################################################
    */

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Component getComponentById(Long id) {
        return componentRepository.findById(id).orElse(null);
    }

    public Component saveComponent(Component component) {
        return componentRepository.save(component);
    }

    public void deleteComponent(Long id) {
        componentRepository.deleteById(id);
    }

}
