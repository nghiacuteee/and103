package fpoly.nghianmph38531.baithi_ph38531;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SanPhamAdapter sanPhamAdapter;
    FloatingActionButton btn_add;
    EditText etSearch;
  List<SanPhamModel> sanPhamList;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_xemay);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_add = findViewById(R.id.btn_add);
        etSearch = findViewById(R.id.et_search);

        apiService = RetrofitClient.getInstance().create(ApiService.class);
        loadSanPhams(); // Load all products initially

        btn_add.setOnClickListener(v -> showAddDialog());

 // Set up TextWatcher for the search bar
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) { // Trigger search after 1 or more characters
                    searchSanPham(s.toString());
                } else {
                    loadSanPhams(); // Reload all products if search query is cleared
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed after text changes
            }
        });
    }
/// show dialog
    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(dialogView);

        EditText etTenSanPham = dialogView.findViewById(R.id.et_ten_xe);
        EditText etNgayNhap = dialogView.findViewById(R.id.et_mau_sac);
        EditText etTrangThai = dialogView.findViewById(R.id.et_gia_ban);
        EditText etMoTa = dialogView.findViewById(R.id.et_mo_ta);
        EditText etHinhAnh = dialogView.findViewById(R.id.et_hinh_anh);
        Button btnSubmit = dialogView.findViewById(R.id.btn_submit);

        AlertDialog dialog = builder.create();

        btnSubmit.setOnClickListener(v -> {
            String tenSanPham = etTenSanPham.getText().toString();
            String ngayNhap = etNgayNhap.getText().toString();
            double trangThai = Double.parseDouble(etTrangThai.getText().toString());
            String moTa = etMoTa.getText().toString();
            String hinhAnh = etHinhAnh.getText().toString();

            SanPhamModel newSanPham = new SanPhamModel(tenSanPham, ngayNhap, trangThai, moTa, hinhAnh);
            addSanPham(newSanPham);
            dialog.dismiss();
        });

        dialog.show();
    }
/// add san pham
    private void addSanPham(SanPhamModel sanPhamModel) {
        Call<SanPhamModel> call = apiService.createSanPham(sanPhamModel);
        call.enqueue(new Callback<SanPhamModel>() {
            @Override
            public void onResponse(Call<SanPhamModel> call, Response<SanPhamModel> response) {
                if (response.isSuccessful()) {
                    // Add new data to the list and update the adapter
                    sanPhamAdapter.addSanPham(response.body());
                }
            }

            @Override
            public void onFailure(Call<SanPhamModel> call, Throwable t) {
                // Handle failure when calling the API
            }
        });
    }

/// load
    private void loadSanPhams() {
        Call<List<SanPhamModel>> call = apiService.getSanPhams();
        call.enqueue(new Callback<List<SanPhamModel>>() {
            @Override
            public void onResponse(Call<List<SanPhamModel>> call, Response<List<SanPhamModel>> response) {
                if (response.isSuccessful()) {
                    List<SanPhamModel> sanPhamList = response.body();
                    sanPhamAdapter = new SanPhamAdapter(sanPhamList, MainActivity.this);
                    recyclerView.setAdapter(sanPhamAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<SanPhamModel>> call, Throwable t) {
                // Handle failure when calling the API
            }
        });
    }
/// tim kiem
    private void searchSanPham(String query) {
        Call<List<SanPhamModel>> call = apiService.searchSanPham(query);
        call.enqueue(new Callback<List<SanPhamModel>>() {
            @Override
            public void onResponse(Call<List<SanPhamModel>> call, Response<List<SanPhamModel>> response) {
                if (response.isSuccessful()) {
                    List<SanPhamModel> sanPhamList = response.body();
                    sanPhamAdapter = new SanPhamAdapter(sanPhamList, MainActivity.this);

                    recyclerView.setAdapter(sanPhamAdapter);

                    sanPhamAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<SanPhamModel>> call, Throwable t) {
                // Handle failure when calling the API
            }
        });
    }
}
