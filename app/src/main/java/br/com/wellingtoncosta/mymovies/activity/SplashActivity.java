package br.com.wellingtoncosta.mymovies.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import br.com.wellingtoncosta.mymovies.R;

/**
 * @author Wellington Costa on 26/04/17.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(this, LoginActivity.class));
    }

}
