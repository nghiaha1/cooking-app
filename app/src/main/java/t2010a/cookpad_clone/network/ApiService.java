package t2010a.cookpad_clone.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.user.User;

public interface ApiService {
    String SERVER = "http://10.0.2.2:8066";

    @POST("/api/v1/register")
    Call<User> registerUser(@Body User user);

    @POST("/api/v1/login")
    Call<LoginResponse> loginUser(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1/user/profile")
    Call<User> getUser(@Header("Authorization") String token);

//    @GET("/api/v1/post")
//    Call<Post>
}
