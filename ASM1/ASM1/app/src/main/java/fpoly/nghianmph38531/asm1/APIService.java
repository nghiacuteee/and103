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


        @GET("/api/list")
        Call<List<CarModel>> getCars();

        @POST("/api/add_xe")
        Call<CarModel> addCar(@Body CarModel car);

        @DELETE("/api/xoa/{id}")
        Call<Void> deleteCar(@Path("id") String id);

        @PUT("/api/update/{id}")
        Call<CarModel> updateCar(@Path("id") String id, @Body CarModel car);
    }