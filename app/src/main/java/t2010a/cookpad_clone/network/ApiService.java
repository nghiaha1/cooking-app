package t2010a.cookpad_clone.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.client_model.Content;
import t2010a.cookpad_clone.model.client_model.HomeModel;
import t2010a.cookpad_clone.model.client_model.User;


public interface ApiService {
    String SERVER = "http://10.0.2.2:8066";

    @POST("/api/v1/register")
    Call<User> registerUser(@Body User user);

    @POST("/api/v1/login")
    Call<LoginResponse> loginUser(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1/user/profile")
    Call<User> getUser(@Header("Authorization") String token);

    @POST("/api/v1/post")
    Call<Content> createPost(@Body Content content);

    @DELETE("api/v1/post/{id}")
    Call<Content> deletePost(@Path("id") int id);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("/api/v1/user/{id}")
    Call<User> updateUser(@Body User user,
                          @Path(value = "id") Integer id,
                          @Header("Authorization") String token);

    @GET("/api/v1/post")
    Call<HomeModel> getPostList();

//
//    @GET("/api/v1/user/products")
//    Call<List<>>
}
