package com.github.phongnt.train.controller;

import com.github.phongnt.train.model.Train;
import com.github.phongnt.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Train controller.
 */
@RestController
@RequestMapping("/trains")
public class TrainController {

    /**
     * The Train repository.
     */
    @Autowired
    TrainRepository trainRepository;

    /**
     * Gets all trains.
     *
     * @return the all trains
     */
    @GetMapping()
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

}
