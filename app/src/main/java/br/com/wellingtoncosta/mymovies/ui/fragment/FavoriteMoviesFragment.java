package br.com.wellingtoncosta.mymovies.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.mymovies.Application;
import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.FavoriteMovie;
import br.com.wellingtoncosta.mymovies.domain.FavoriteMovieTO;
import br.com.wellingtoncosta.mymovies.ui.adapter.FavoriteMoviesAdapter;
import br.com.wellingtoncosta.mymovies.ui.listener.OnImageClickListenter;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Wellington Costa on 06/05/2017
 */
public class FavoriteMoviesFragment extends ListFragment {

    @Inject
    Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((Application) getContext().getApplicationContext()).component().inject(FavoriteMoviesFragment.this);

        View view = inflater.inflate(R.layout.fragment_favorite_movies, container, false);

        ButterKnife.bind(this, view);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        return view;
    }

    public void loadData() {
        swipeRefreshLayout.setRefreshing(true);

        Call<List<FavoriteMovieTO>> call = (query == null || query.isEmpty()  ) ?
                service.getAllFavoriteMovies() :
                service.getAllFavoriteMoviesByTitle(query);

        call.enqueue(new Callback<List<FavoriteMovieTO>>() {
            @Override
            public void onResponse(Call<List<FavoriteMovieTO>> call, Response<List<FavoriteMovieTO>> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    List<FavoriteMovie> favoriteMovies = adjustFavoriteMovies(response.body());
                    recyclerView.setAdapter(new FavoriteMoviesAdapter(favoriteMovies, onImageClick()));
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Snackbar.make(recyclerView, R.string.server_error, Snackbar.LENGTH_LONG).show();
                    recyclerView.setAdapter(new FavoriteMoviesAdapter(Collections.<FavoriteMovie>emptyList(), null));
                }
            }

            @Override
            public void onFailure(Call<List<FavoriteMovieTO>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(recyclerView, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                recyclerView.setAdapter(new FavoriteMoviesAdapter(Collections.<FavoriteMovie>emptyList(), null));
            }
        });
    }

    private List<FavoriteMovie> adjustFavoriteMovies(List<FavoriteMovieTO> favoriteMovieTOs) {
        List<FavoriteMovie> favMovies = new ArrayList<>();

        for (FavoriteMovieTO favoriteMovieTO  : favoriteMovieTOs) {
            favMovies.add(new FavoriteMovie(favoriteMovieTO));
        }

        return favMovies;
    }

    private OnImageClickListenter onImageClick() {
        return new OnImageClickListenter() {
            @Override
            public void onClick(View view, String imageUrl) {
                Uri imageUri = Uri.parse(imageUrl);

                if (imageUri != null) {
                    startActivity(new Intent(Intent.ACTION_VIEW, imageUri));
                }
            }
        };
    }

    /*private void saveFavoriteMoviesLocal() {
        service.getAllFavoriteMovies().enqueue(new Callback<List<FavoriteMovieTO>>() {
            @Override
            public void onResponse(Call<List<FavoriteMovieTO>> call, Response<List<FavoriteMovieTO>> response) {
                final List<FavoriteMovieTO> favoriteMovies = response.body();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm bgRealm) {
                        for (FavoriteMovieTO favoriteMovieTO : favoriteMovies) {
                            FavoriteMovie favoriteMovie = realm.createObject(FavoriteMovie.class);
                            favoriteMovie.setId(favoriteMovieTO.getId());
                            favoriteMovie.setMovieName(favoriteMovieTO.getMovie().getName());
                            favoriteMovie.setMovieGenre(favoriteMovieTO.getMovie().getGenre());
                            favoriteMovie.setMovieYear(favoriteMovieTO.getMovie().getYear());
                            favoriteMovie.setUserId(favoriteMovieTO.getUser().getId());
                        }
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.e("exception", error.getMessage(), error);
                        Snackbar.make(recyclerView, R.string.save_data_local_failure, Snackbar.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<FavoriteMovieTO>> call, Throwable t) {

            }
        });
    }*/

}
