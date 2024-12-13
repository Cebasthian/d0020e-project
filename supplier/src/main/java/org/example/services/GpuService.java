package org.example.services;

import org.example.entity.Gpu;
import org.example.repository.GpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpuService {

    @Autowired
    private GpuRepository gpuRepository;

    public List<Gpu> getAllGpus() {
        return gpuRepository.findALl();
    }

    public Gpu getGpuById(int id) {
        return gpuRepository.findById(id).orElse(null);
    }

    public Gpu saveGpu(Gpu gpu) {
        return gpuRepository.save(gpu);
    }

    public void deleteGpu(int id) {
        gpuRepository.deleteById(id);
    }

}
