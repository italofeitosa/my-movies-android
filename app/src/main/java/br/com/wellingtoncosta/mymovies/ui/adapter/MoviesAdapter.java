package br.com.wellingtoncosta.mymovies.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.Movie;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wellington Costa on 06/05/2017
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies;
    private OnFavoriteClickListenter listenter;

    public MoviesAdapter(List<Movie> movies, OnFavoriteClickListenter listenter) {
        this.movies = movies;
        this.listenter = listenter;
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_movies_item, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder viewHolder, int position) {
        viewHolder.bind(this.movies.get(position), listenter);
    }


    static class MoviesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.favoriteButton)
        ImageView favoriteButton;

        @BindView(R.id.movieTitle)
        TextView movieTitle;

        @BindView(R.id.movieGenre)
        TextView movieGenre;

        @BindDrawable(R.drawable.ic_favorite_border)
        Drawable favoriteBorder;

        @BindDrawable(R.drawable.ic_favorite_red)
        Drawable favoriteRed;

        MoviesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final Movie movie, final OnFavoriteClickListenter listenter) {
            movieTitle.setText(movie.getTitle());
            movieGenre.setText(movie.getGenre());

            favoriteButton.setImageDrawable(movie.isFavorite() ? favoriteRed : favoriteBorder);

            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenter.onClick(v, movie);
                }
            });
        }
    }
}
