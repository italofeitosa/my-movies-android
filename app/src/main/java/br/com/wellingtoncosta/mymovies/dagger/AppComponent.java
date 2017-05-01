package br.com.wellingtoncosta.mymovies.dagger;

import javax.inject.Singleton;

import br.com.wellingtoncosta.mymovies.activity.LoginActivity;

import br.com.wellingtoncosta.mymovies.activity.MoviesActivity;
import br.com.wellingtoncosta.mymovies.activity.SplashActivity;
import br.com.wellingtoncosta.mymovies.activity.UserRegistrationActivity;
import dagger.Component;

/**
 * @author Wellington Costa on 30/04/17.
 */
@Singleton
@Component(modules = {NetworkModule.class, RealmModule.class})
public interface AppComponent {

    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
    void inject(UserRegistrationActivity activity);
    void inject(MoviesActivity activity);

}
