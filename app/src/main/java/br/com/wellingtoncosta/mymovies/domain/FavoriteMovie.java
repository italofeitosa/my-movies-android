package br.com.wellingtoncosta.mymovies.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Wellington Costa on 30/04/17.
 */
public class FavoriteMovie extends RealmObject {

    @PrimaryKey
    private Long id;

    private Long userId;

    private String movieName;

    private String movieYear;

    private String movieGenre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

}
