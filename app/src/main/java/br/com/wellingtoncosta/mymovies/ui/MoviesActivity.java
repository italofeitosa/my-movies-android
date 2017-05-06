package br.com.wellingtoncosta.mymovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import javax.inject.Inject;

import br.com.wellingtoncosta.mymovies.Application;
import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.FavoriteMovie;
import br.com.wellingtoncosta.mymovies.domain.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MoviesActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.content_movies)
    RelativeLayout contentMovies;

    @Inject
    Realm realm;

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((Application) getApplication()).component().inject(MoviesActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setupMaterialDrawer();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void setupMaterialDrawer() {
        View header = createHeaderDrawer();
        PrimaryDrawerItem favoriteMoviesItem = createFavoriteDrawerItem();
        PrimaryDrawerItem logoutItem = createLogoutDrawerItem();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(favoriteMoviesItem, logoutItem)
                .withHeader(header)
                .build();

        drawer.setSelection(-1);
    }

    private View createHeaderDrawer() {
        View headerView = getLayoutInflater().inflate(R.layout.header, contentMovies, false);
        TextView nameField = (TextView) headerView.findViewById(R.id.nameField);
        TextView emailField = (TextView) headerView.findViewById(R.id.emailField);
        User currentUser = realm.where(User.class).findFirst();

        nameField.setText(currentUser.getName());
        emailField.setText(currentUser.getEmail());

        return headerView;
    }

    private PrimaryDrawerItem createFavoriteDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.favorite_movies)
                .withIcon(GoogleMaterial.Icon.gmd_favorite)
                .withSelectable(false)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // TODO create favorite movies fragment
                        return false;
                    }
                });
    }

    private PrimaryDrawerItem createLogoutDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.logout)
                .withIcon(GoogleMaterial.Icon.gmd_exit_to_app)
                .withSelectable(false)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        logout();
                        return false;
                    }
                });
    }

    private void logout() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.delete(FavoriteMovie.class);
                bgRealm.delete(User.class);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // TODO call API logout
                MoviesActivity.this.startActivity(new Intent(MoviesActivity.this, LoginActivity.class));
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("exception", error.getMessage(), error);
                Snackbar.make(toolbar, R.string.logout_failure, Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
