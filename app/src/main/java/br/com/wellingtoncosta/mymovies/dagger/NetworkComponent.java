package br.com.wellingtoncosta.mymovies.dagger;

import javax.inject.Singleton;

import br.com.wellingtoncosta.mymovies.activity.LoginActivity;

import dagger.Component;

/**
 * @author Wellington Costa on 30/04/17.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(LoginActivity activity);

}
