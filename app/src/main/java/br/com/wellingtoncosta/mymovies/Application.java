package br.com.wellingtoncosta.mymovies;

import br.com.wellingtoncosta.mymovies.dagger.DaggerNetworkComponent;
import br.com.wellingtoncosta.mymovies.dagger.NetworkComponent;
import br.com.wellingtoncosta.mymovies.dagger.NetworkModule;

/**
 * @author Wellington Costa on 30/04/17.
 */
public class Application extends android.app.Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent
                .builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent component() {
        return networkComponent;
    }
}
