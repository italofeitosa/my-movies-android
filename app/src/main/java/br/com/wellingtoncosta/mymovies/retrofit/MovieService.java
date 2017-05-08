package br.com.wellingtoncosta.mymovies.retrofit;

import java.util.List;

import br.com.wellingtoncosta.mymovies.domain.FavoriteMovieTO;
import br.com.wellingtoncosta.mymovies.domain.Movie;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
    Call<List<Movie>> getAllMovies();

    @GET(ENDPOINT_MOVIES + "/{id}")
    Call<Movie> getMovieById(@Path("id") Long id);

    @GET(ENDPOINT_MOVIES + "/title/{title}")
    Call<List<Movie>> getAllMoviesByName(@Path("title") String title);

    @GET(ENDPOINT_FAVORITE_MOVIES)
    Call<List<FavoriteMovieTO>> getAllFavoriteMovies();

    @POST(ENDPOINT_FAVORITE_MOVIES)
    Call<ResponseBody> favoriteMovie(@Body Movie movie);

    @DELETE(ENDPOINT_FAVORITE_MOVIES + "/{id}")
    Call<ResponseBody> unfavoriteMovie(@Path("id") Long movieId);

}
