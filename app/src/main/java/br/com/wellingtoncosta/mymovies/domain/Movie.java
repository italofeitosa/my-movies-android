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

    private String description;

}
