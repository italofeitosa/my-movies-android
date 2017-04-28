package br.com.wellingtoncosta.mymovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.com.wellingtoncosta.mymovies.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * @author Wellington Costa on 26/04/17.
 */
public class UserRegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setupToolbar();
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnEditorAction(R.id.confirmPasswordField)
    public boolean handleEditorAction() {
        saveNewUser();
        return true;
    }

    @OnClick(R.id.saveNewUserButton)
    public void saveNewUser() {
        Toast.makeText(this, "Cadastrar novo usuário", Toast.LENGTH_LONG).show();
    }

}
