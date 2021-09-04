package com.github.phongnt.train.controller;

import com.github.phongnt.train.dto.GenericResponse;
import com.github.phongnt.train.error.EntityNotFoundException;
import com.github.phongnt.train.error.InvalidParameterException;
import com.github.phongnt.train.model.Train;
import com.github.phongnt.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<List<Train>> getAllTrains(@RequestParam Map<String, String> params) {
        List<Train> trains;
        if (params.isEmpty()) {
            trains = trainRepository.findAll();
        } else if (params.size() == 1 && params.containsKey("amenities")) {
            trains = trainRepository.findAllByAmenities(params.get("amenities"));
        } else {
            throw new InvalidParameterException("allow parameter amenities only");
        }
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    /**
     * Gets a train by id
     *
     * @param id the train id
     * @return the train
     */
    @GetMapping("/{trainId}")
    public ResponseEntity<Train> getTrain(@PathVariable("trainId") long id) {
        Optional<Train> train = trainRepository.findById(id);
        if (train.isPresent()) {
            return new ResponseEntity<>(train.get(), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("train not found");
        }
    }

    /**
     * Get trains which have sharing-tracks
     *
     * @return the trains list
     */
    @GetMapping("/sharing-tracks")
    public ResponseEntity<List<Train>> getTrainsHaveSharingTracks() {
        return new ResponseEntity<>(trainRepository.findAllBySharingTracksTrue(), HttpStatus.OK);
    }

    /**
     * Delete a train by id
     * @param id the train id
     * @return the generic response
     */
    @DeleteMapping("/{trainId}")
    public ResponseEntity<Object> deleteTrain(@PathVariable("trainId") long id) {
        Optional<Train> train = trainRepository.findById(id);
        if (train.isPresent()) {
            trainRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("train not found");
        }
        return new ResponseEntity<>(new GenericResponse("train removed successfully", null), HttpStatus.OK);
    }

}
