package fpoly.nghianmph38531.asm1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.POST;
public interface APIService {

    String DOMAIN = "http://10.0.3.2:3000/";


    @GET("cars")
    Call<List<CarModel>> getCars();

    @POST("cars")
    Call<CarModel> addCar(@Body CarModel car);

    @PUT("cars/{id}")
    Call<List<CarModel>> updateCar(@Path("id") String id, @Body CarModel car);

    @DELETE("cars/{id}")
    Call<Void> deleteCar(@Path("id") String id);
}
