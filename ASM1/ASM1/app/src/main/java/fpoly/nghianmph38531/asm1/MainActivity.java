package fpoly.nghianmph38531.asm1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText edtTen, edtNamSX, edtHang, edtGia;
    private Button btnAdd;
    private ListView lvMain;
    private CarAdapter carAdapter;
    private APIService apiService;
    private List<CarModel> carModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTen = findViewById(R.id.edtTen);
        edtNamSX = findViewById(R.id.edtNamSX);
        edtHang = findViewById(R.id.edtHang);
        edtGia = findViewById(R.id.edtGia);
        btnAdd = findViewById(R.id.btnAdd);
        lvMain = findViewById(R.id.listviewMain);

        carModelList = new ArrayList<>();
        carAdapter = new CarAdapter(this, carModelList, apiService);
        lvMain.setAdapter(carAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);

        loadCars();

        btnAdd.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            int namSX = Integer.parseInt(edtNamSX.getText().toString());
            String hang = edtHang.getText().toString();
            double gia = Double.parseDouble(edtGia.getText().toString());

            CarModel newCar = new CarModel(null, ten, namSX, hang, gia);
            apiService.addCar(newCar).enqueue(new Callback<CarModel>() {
                @Override
                public void onResponse(Call<CarModel> call, Response<CarModel> response) {
                    if (response.isSuccessful()) {
                        carModelList.add(response.body());
                        carAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<CarModel> call, Throwable t) {
                    // Handle failure
                }
            });
        });
    }

    private void loadCars() {
        apiService.getCars().enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(Call<List<CarModel>> call, Response<List<CarModel>> response) {
                if (response.isSuccessful()) {
                    carModelList.clear();
                    if (response.body() != null) {
                        carModelList.addAll(response.body());
                    }
                    carAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CarModel>> call, Throwable t) {
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}
