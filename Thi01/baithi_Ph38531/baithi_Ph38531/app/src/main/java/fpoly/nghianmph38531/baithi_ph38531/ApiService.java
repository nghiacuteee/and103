package fpoly.nghianmph38531.baithi_ph38531;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("sanphams")
    Call<List<SanPhamModel>> getSanPhams();

    @POST("sanphams")
    Call<SanPhamModel> createSanPham(@Body SanPhamModel sanPhamModel);

    @PUT("sanphams/{id}")
    Call<SanPhamModel> updateSanPham(@Path("id") String id, @Body SanPhamModel sanPhamModel);

    @DELETE("sanphams/{id}")
    Call<Void> deleteSanPham(@Path("id") String id);

    @GET("/search")
    Call<List<SanPhamModel>> searchSanPham(@Query("ten_sanpham_ph38531") String tenSanPham);


}
