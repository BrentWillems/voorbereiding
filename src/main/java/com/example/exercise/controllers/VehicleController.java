package com.example.exercise.controllers;

import com.example.exercise.models.Vehicle;
import com.example.exercise.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService customerService) {
        this.vehicleService = customerService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Vehicle> vehicles = vehicleService.getAll();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") int id){
        Optional<Vehicle> customer = vehicleService.getVehicleById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle){
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable int id, @RequestBody Vehicle vehicle){
        vehicleService.update(id, vehicle);
        return ResponseEntity.noContent().build();
    }
}
