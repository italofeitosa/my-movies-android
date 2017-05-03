package br.com.wellingtoncosta.mymovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import javax.inject.Inject;

import br.com.wellingtoncosta.mymovies.Application;
import br.com.wellingtoncosta.mymovies.R;
import br.com.wellingtoncosta.mymovies.domain.User;
import br.com.wellingtoncosta.mymovies.retrofit.UserService;
import br.com.wellingtoncosta.mymovies.validation.Validation;
import br.com.wellingtoncosta.mymovies.validation.validators.EmailValidator;
import br.com.wellingtoncosta.mymovies.validation.validators.NotEmptyValidator;
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

    @BindView(R.id.emailLayout)
    TextInputLayout emailLayout;

    @BindView(R.id.emailField)
    EditText emailField;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.passwordField)
    EditText passwordField;

    @Inject
    UserService userService;

    private Validation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((Application) getApplication()).component().inject(LoginActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setupValidation();
    }

    private void setupValidation() {
        validation = new Validation();

        validation.addValidator(new EmailValidator(
                emailLayout,
                Patterns.EMAIL_ADDRESS.pattern(),
                getString(R.string.invalid_email)
        ));

        validation.addValidator(new NotEmptyValidator(
                passwordLayout,
                getString(R.string.field_required)
        ));
    }

    @OnEditorAction(R.id.passwordField)
    public boolean handleEditorAction() {
        login();
        return true;
    }

    @OnClick(R.id.loginButton)
    public void login() {
        if (validation.validate()) {
            User user = buildUser(); // TODO call API login
            Toast.makeText(this, "Logar", Toast.LENGTH_LONG).show();
        }
    }

    private User buildUser() {
        User user = new User();
        user.setEmail(emailField.getText().toString());

        try {
            byte[] bytes = passwordField.getText().toString().getBytes("UTF-8");
            String encryptedPassword = Base64.encodeToString(bytes, Base64.DEFAULT);
            user.setPassword(encryptedPassword);
        } catch (IOException e) {
            Log.e("exception", e.getMessage(), e);
        }

        return user;
    }

    @OnClick(R.id.registerNewUserButton)
    public void registerNewUser() {
        startActivity(new Intent(this, UserRegistrationActivity.class));
        finish();
    }

}
