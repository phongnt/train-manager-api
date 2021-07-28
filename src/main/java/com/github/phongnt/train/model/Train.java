package com.github.phongnt.train.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The model Train.
 */
@Getter
@Setter
@Entity
@Table(name = "trains")
public class Train {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "\"distance-between-stop\"")
    @JsonProperty("distance-between-stop")
    private String distanceBetweenStop;

}
