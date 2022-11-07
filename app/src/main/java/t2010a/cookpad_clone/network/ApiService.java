package t2010a.cookpad_clone.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.user.UpdateUser;
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

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("/api/v1/user/{id}")
    Call<UpdateUser> updateUser(@Body UpdateUser user,
                          @Path(value = "id") Long id,
                          @Header("Authorization") String token);

    @GET("/api/v1/post")
    Call<List<Post>> getPostList();

//    @POST("/api/v1/")
}
