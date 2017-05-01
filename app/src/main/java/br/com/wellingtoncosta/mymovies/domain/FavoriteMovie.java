package br.com.wellingtoncosta.mymovies.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Wellington Costa on 30/04/17.
 */
public class FavoriteMovie extends RealmObject {

    @PrimaryKey
    private Long id;

    private Movie movie;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
