package br.com.wellingtoncosta.mymovies.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.mymovies.Application;
import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.Movie;
import br.com.wellingtoncosta.mymovies.retrofit.MovieService;
import br.com.wellingtoncosta.mymovies.ui.adapter.MoviesAdapter;
import br.com.wellingtoncosta.mymovies.ui.adapter.OnFavoriteClickListenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Wellington Costa on 06/05/2017
 */
public class MoviesFragment extends Fragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.movies_list)
    RecyclerView recyclerView;

    @Inject
    MovieService service;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((Application) getContext().getApplicationContext()).component().inject(MoviesFragment.this);
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        setupSwipeRefreshLayout();
        setupRecyclerView();
        loadMovies();

        return view;
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies();
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void loadMovies() {
        service.getAllMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new MoviesAdapter(response.body(), onFavoriteClick()));
                } else {
                    Snackbar.make(recyclerView, R.string.server_error, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(recyclerView, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                recyclerView.setAdapter(new MoviesAdapter(Collections.<Movie>emptyList(), null));
            }
        });
    }

    private OnFavoriteClickListenter onFavoriteClick() {
        return new OnFavoriteClickListenter() {
            @Override
            public void onClick(View view, Movie movie) {
                Call<ResponseBody> call = movie.isFavorite() ?
                        service.unfavoriteMovie(movie.getId()) :
                        service.favoriteMovie(movie);

                swipeRefreshLayout.setRefreshing(true);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        loadMovies();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        swipeRefreshLayout.setRefreshing(false);
                        Snackbar.make(recyclerView, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        };
    }
}
