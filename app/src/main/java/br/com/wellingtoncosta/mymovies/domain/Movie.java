package br.com.wellingtoncosta.mymovies.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Wellington Costa on 30/04/17.
 */
public class Movie extends RealmObject {

    @PrimaryKey
    private Long id;

    private String name;

    private String year;

    private String genre;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
