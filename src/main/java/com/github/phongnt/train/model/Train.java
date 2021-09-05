package com.github.phongnt.train.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The model Train.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trains")
public class Train {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @Column(name = "\"max-speed\"")
    @JsonProperty("max-speed")
    private String maxSpeed;

    @Column(name = "\"distance-between-stop\"")
    @JsonProperty("distance-between-stop")
    private String distanceBetweenStop;

    @Column(name = "\"sharing-tracks\"")
    @JsonProperty("sharing-tracks")
    private Boolean sharingTracks;

    @Column(name = "\"grade-crossing\"")
    @JsonProperty("grade-crossing")
    private Boolean gradeCrossing;

    @Column(name = "\"train-frequency\"")
    @JsonProperty("train-frequency")
    private String trainFrequency;

    private String amenities;

}
