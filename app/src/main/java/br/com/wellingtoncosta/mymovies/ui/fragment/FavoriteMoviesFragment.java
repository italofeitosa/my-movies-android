package br.com.wellingtoncosta.mymovies.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wellingtoncosta.mymovies.R;

/**
 * @author Wellington Costa on 06/05/2017
 */
public class FavoriteMoviesFragment extends Fragment {

    public FavoriteMoviesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no_favorite_movies, container, false);
    }

}
