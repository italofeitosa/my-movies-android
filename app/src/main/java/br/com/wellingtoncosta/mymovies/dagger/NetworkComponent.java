package br.com.wellingtoncosta.mymovies.dagger;

import android.app.Activity;

import javax.inject.Singleton;

import br.com.wellingtoncosta.mymovies.dagger.modules.NetworkModule;
import dagger.Component;

/**
 * @author Wellington Costa on 30/04/17.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(Activity activity);

}
