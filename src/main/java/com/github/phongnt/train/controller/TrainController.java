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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        } else if (params.containsKey("amenities")) {
            trains = trainRepository.findAllByAmenitiesContains(params.get("amenities"));
        } else {
            throw new InvalidParameterException("allow parameter amenities only");
        }
        if (trains == null || trains.size() == 0) {
            return new ResponseEntity(new GenericResponse("train not found", null), HttpStatus.OK);
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

    /**
     * Create the train
     *
     * @param body the body
     * @return the generic response
     */
    @PostMapping
    public ResponseEntity<GenericResponse> createTrain(@RequestBody Train body) {
        trainRepository.save(body);
        return new ResponseEntity<>(new GenericResponse("new train added successfully", null),
                HttpStatus.CREATED);
    }

    @PutMapping("/{trainId}")
    public ResponseEntity<GenericResponse> updateTrain(@PathVariable("trainId") long id, @RequestBody Train body) {
        Optional<Train> train = trainRepository.findById(id);
        if (train.isPresent()) {
            body.setId(id);
            if (body.getName() == null) {
                body.setName(train.get().getName());
            }
            if (body.getDescription() == null) {
                body.setDescription(train.get().getDescription());
            }
            if (body.getMaxSpeed() == null) {
                body.setMaxSpeed(train.get().getMaxSpeed());
            }
            if (body.getDistanceBetweenStop() == null) {
                body.setDistanceBetweenStop(train.get().getDistanceBetweenStop());
            }
            if (body.getSharingTracks() == null) {
                body.setSharingTracks(train.get().getSharingTracks());
            }
            if (body.getGradeCrossing() == null) {
                body.setGradeCrossing(train.get().getGradeCrossing());
            }
            if (body.getTrainFrequency() == null) {
                body.setTrainFrequency(train.get().getTrainFrequency());
            }
            if (body.getAmenities() == null) {
                body.setAmenities(train.get().getAmenities());
            }
            trainRepository.save(body);
            return new ResponseEntity<>(new GenericResponse("train edited successfully", null), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("train not found");
        }
    }

}
