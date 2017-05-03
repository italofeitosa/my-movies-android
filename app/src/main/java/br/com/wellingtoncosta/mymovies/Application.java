package br.com.wellingtoncosta.mymovies;

import br.com.wellingtoncosta.mymovies.dagger.AppComponent;
import br.com.wellingtoncosta.mymovies.dagger.DaggerAppComponent;
import br.com.wellingtoncosta.mymovies.dagger.NetworkModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Wellington Costa on 30/04/17.
 */
public class Application extends android.app.Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initRealmConfiguration();

        appComponent = DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule())
                .build();
    }

    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public AppComponent component() {
        return appComponent;
    }
}
