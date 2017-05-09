package br.com.wellingtoncosta.mymovies.ui.adapter;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.Movie;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wellington Costa on 06/05/2017
 */
public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies;
    private OnFavoriteClickListenter listenter;

    public FavoriteMoviesAdapter(List<Movie> movies, OnFavoriteClickListenter listenter) {
        this.movies = movies;
        this.listenter = listenter;
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    @Override
    public FavoriteMoviesAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_movies_item, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder viewHolder, int position) {
        viewHolder.bind(this.movies.get(position), listenter);
    }


    static class MoviesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movieImage)
        SimpleDraweeView movieImage;

        @BindView(R.id.movieTitle)
        TextView movieTitle;

        @BindView(R.id.movieGenre)
        TextView movieGenre;

        @BindView(R.id.favoriteButton)
        ImageView favoriteButton;

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
                    favoriteButton.setClickable(false);
                }
            });

            loadMovieImage();
        }

        private void loadMovieImage() {
            Uri imageUri = Uri.parse("http://lorempixel.com/200/200/sports");

            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                    .setResizeOptions(new ResizeOptions(64, 64))
                    .setProgressiveRenderingEnabled(true)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();

            AbstractDraweeController newController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .build();
        }
    }
}
