package br.com.wellingtoncosta.mymovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import br.com.wellingtoncosta.mymovies.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * @author Wellington Costa on 26/04/17.
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnEditorAction(R.id.passwordField)
    public boolean handleEditorAction() {
        login();
        return true;
    }

    @OnClick(R.id.loginButton)
    public void login() {
        Toast.makeText(this, "Logar", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.registerNewUserButton)
    public void registerNewUser() {
        startActivity(new Intent(this, UserRegisterActivity.class));
    }

}
