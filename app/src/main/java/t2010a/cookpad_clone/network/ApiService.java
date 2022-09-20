package t2010a.cookpad_clone.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import t2010a.cookpad_clone.model.user.User;

public interface ApiService {
    String SERVER = "http://10.0.2.2:8066";

    @POST("/api/v1/users/register")
    Call<User> registerUser(@Body User user);

    @POST("/api/v1/users/login")
    Call<User> loginUser(@Body User user);
}
