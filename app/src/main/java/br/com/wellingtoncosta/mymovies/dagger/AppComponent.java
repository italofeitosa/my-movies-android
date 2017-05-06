package br.com.wellingtoncosta.mymovies.dagger;

import javax.inject.Singleton;

import br.com.wellingtoncosta.mymovies.ui.LoginActivity;

import br.com.wellingtoncosta.mymovies.ui.MoviesActivity;
import br.com.wellingtoncosta.mymovies.ui.SplashActivity;
import br.com.wellingtoncosta.mymovies.ui.UserRegistrationActivity;
import dagger.Component;

/**
 * @author Wellington Costa on 30/04/17.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
    void inject(UserRegistrationActivity activity);
    void inject(MoviesActivity activity);

}
