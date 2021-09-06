package com.github.phongnt.train.controller;

import com.github.phongnt.train.dto.PageResponse;
import com.github.phongnt.train.error.EntityNotFoundException;
import com.github.phongnt.train.model.Train;
import com.github.phongnt.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PageResponse<Train>> getAllTrains(Pageable pageable) {
        Page<Train> page = trainRepository.findAll(pageable);
        return new ResponseEntity<>(PageResponse.<Train>builder()
                .currentPage(page.getNumber())
                .trains(page.getContent())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build(), HttpStatus.OK);
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

}
