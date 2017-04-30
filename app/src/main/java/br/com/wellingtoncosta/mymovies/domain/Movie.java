package br.com.wellingtoncosta.mymovies.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Wellington Costa on 29/04/17.
 */
@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String year;

    @Column
    private String description;

}
