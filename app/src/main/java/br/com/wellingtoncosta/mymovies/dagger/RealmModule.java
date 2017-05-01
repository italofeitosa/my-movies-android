package br.com.wellingtoncosta.mymovies.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author Wellington Costa on 01/05/17.
 */
@Module
class RealmModule {

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}
