package br.com.wellingtoncosta.mymovies.retrofit;

import java.util.List;

import br.com.wellingtoncosta.mymovies.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Wellington Costa on 30/04/17.
 */
public interface UserService {

    String ENDPONIT = "users/";

    @GET(ENDPONIT)
    Call<List<User>> getUsers();

    @POST(ENDPONIT)
    Call<User> saveNewUser(@Body User user);

}
