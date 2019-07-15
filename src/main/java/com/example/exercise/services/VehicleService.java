package com.example.exercise.services;

import com.example.exercise.models.Vehicle;
import com.example.exercise.repositories.IVehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private IVehicleRepository vehicleRepository;

    public VehicleService(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Optional<Vehicle> getVehicleById(int id){
        return vehicleRepository.findById(id);
    }
}
