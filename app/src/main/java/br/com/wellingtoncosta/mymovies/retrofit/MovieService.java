package br.com.wellingtoncosta.mymovies.retrofit;

import android.graphics.Movie;

import java.util.List;

import br.com.wellingtoncosta.mymovies.domain.FavoriteMovie;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Wellington Costa on 30/04/17.
 */
public interface MovieService {

    String ENDPOINT_MOVIES = "api/movies";
    String ENDPOINT_FAVORITE_MOVIES = "api/movies/favorites";

    @GET(ENDPOINT_MOVIES)
    Call<Movie> getAllMovies();

    @GET(ENDPOINT_MOVIES + "/name/{name}")
    Call<Movie> getAllMoviesByName(@Path("name") String name);

    @GET(ENDPOINT_FAVORITE_MOVIES)
    Call<List<FavoriteMovie>> getAllFavoriteMovies();

    @GET(ENDPOINT_FAVORITE_MOVIES + "/name/{name}")
    Call<List<FavoriteMovie>> getFavoriteMoviesByName(@Path("name") String name);

    @POST(ENDPOINT_FAVORITE_MOVIES)
    Call<FavoriteMovie> favoriteMovie(@Body Movie movie);

    @DELETE(ENDPOINT_FAVORITE_MOVIES + "/{id}")
    Call<Response> unfavoriteMovie(@Path("id") Long id);

}
