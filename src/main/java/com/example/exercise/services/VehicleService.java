package com.example.exercise.services;

import com.example.exercise.models.Vehicle;
import com.example.exercise.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle update(int id, Vehicle upToDateVehicle) {
        return getVehicleById(id).map(vehicle -> {
            vehicle.setName(upToDateVehicle.getName());
            vehicle.setCurrentMileage(upToDateVehicle.getCurrentMileage());
            vehicle.setHp(upToDateVehicle.getHp());
            return vehicleRepository.save(vehicle);

        }).orElseThrow(() -> new EntityNotFoundException("CustomerID " + id + "not found"));
    }

    public Optional<Vehicle> getVehicleById(int id){
        return vehicleRepository.findById(id);
    }
}
