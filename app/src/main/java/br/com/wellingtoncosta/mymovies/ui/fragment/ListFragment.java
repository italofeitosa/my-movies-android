package br.com.wellingtoncosta.mymovies.ui.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;

import javax.inject.Inject;

import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.Movie;
import br.com.wellingtoncosta.mymovies.retrofit.MovieService;
import br.com.wellingtoncosta.mymovies.ui.adapter.MoviesAdapter;
import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author Wellington Costa on 07/05/17.
 */
public abstract class ListFragment extends Fragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    MovieService service;

    Unbinder unbinder;

    String query;


    @Override
    public void onDetach() {
        super.onDetach();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public abstract void loadData();

    void onResponseServerError() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(recyclerView, R.string.server_error, Snackbar.LENGTH_LONG).show();
        recyclerView.setAdapter(new MoviesAdapter(Collections.<Movie>emptyList(), null));
    }

    void onLoadDataFailure() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(recyclerView, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
        recyclerView.setAdapter(new MoviesAdapter(Collections.<Movie>emptyList(), null));
    }

}
