package com.example.exercise.services;

import com.example.exercise.models.Customer;
import com.example.exercise.models.Vehicle;
import com.example.exercise.repositories.IVehicleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Vehicle update(int id, Vehicle upToDateVehicle) {
        return getVehicleById(id).map(vehicle -> {
            vehicle.setName(upToDateVehicle.getName());
            vehicle.setCurrentMilage(upToDateVehicle.getCurrentMilage());
            vehicle.setHp(upToDateVehicle.getHp());
            return vehicleRepository.save(vehicle);

        }).orElseThrow(() -> new EntityNotFoundException("CustomerID " + id + "not found"));
    }

    public Optional<Vehicle> getVehicleById(int id){
        return vehicleRepository.findById(id);
    }
}
