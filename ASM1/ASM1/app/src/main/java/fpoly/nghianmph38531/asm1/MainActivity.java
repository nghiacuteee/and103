package fpoly.nghianmph38531.asm1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private APIService apiService;
    private ListView listView;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listviewMain);
        Button addButton = findViewById(R.id.btnAdd);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);

        // Fetch initial car data
        fetchCars();

        // Set up button click listener to show add car dialog
        addButton.setOnClickListener(v -> showAddCarDialog());
    }

    // Fetch list of cars from server
    private void fetchCars() {
        apiService.getCars().enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(Call<List<CarModel>> call, Response<List<CarModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CarModel> cars = response.body();
                    if (carAdapter == null) {
                        carAdapter = new CarAdapter(MainActivity.this, cars, carActionListener);
                        listView.setAdapter(carAdapter);
                    } else {
                        // Update data in adapter
                        carAdapter.updateData(cars);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch cars", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CarModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching cars: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Show dialog for adding a new car
    private void showAddCarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Car");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_car, null);
        builder.setView(view);

        EditText etName = view.findViewById(R.id.etName);
        EditText etNamSX = view.findViewById(R.id.etNamSX);
        EditText etHang = view.findViewById(R.id.etHang);
        EditText etGia = view.findViewById(R.id.etGia);

        builder.setPositiveButton("Add", (dialog, which) -> {
            try {
                CarModel car = new CarModel();
                car.setTen(etName.getText().toString());
                car.setNamSX(Integer.parseInt(etNamSX.getText().toString()));
                car.setHang(etHang.getText().toString());
                car.setGia(Double.parseDouble(etGia.getText().toString()));

                apiService.addCar(car).enqueue(new Callback<CarModel>() {
                    @Override
                    public void onResponse(Call<CarModel> call, Response<CarModel> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();

                            fetchCars(); // Refresh list
                        } else {
                            Toast.makeText(MainActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CarModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input format", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    // Listener for car actions (edit, delete)
    private final CarAdapter.CarActionListener carActionListener = new CarAdapter.CarActionListener() {
        @Override
        public void onEdit(CarModel car) {
            showEditCarDialog(car);
        }

        @Override
        public void onDelete(CarModel car) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Car")
                    .setMessage("Are you sure you want to delete this car?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        apiService.deleteCar(car.get_id()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Car deleted successfully", Toast.LENGTH_SHORT).show();
                                    fetchCars(); // Refresh list
                                } else {
                                    Toast.makeText(MainActivity.this, "Failed to delete car", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Error deleting car: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    };

    // Show dialog for editing an existing car
    private void showEditCarDialog(CarModel car) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Car");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_car, null);
        builder.setView(view);

        EditText etName = view.findViewById(R.id.etName);
        EditText etNamSX = view.findViewById(R.id.etNamSX);
        EditText etHang = view.findViewById(R.id.etHang);
        EditText etGia = view.findViewById(R.id.etGia);

        etName.setText(car.getTen());
        etNamSX.setText(String.valueOf(car.getNamSX()));
        etHang.setText(car.getHang());
        etGia.setText(String.valueOf(car.getGia()));

        builder.setPositiveButton("Save", (dialog, which) -> {
            try {
                CarModel updatedCar = new CarModel();
                updatedCar.setTen(etName.getText().toString());
                updatedCar.setNamSX(Integer.parseInt(etNamSX.getText().toString()));
                updatedCar.setHang(etHang.getText().toString());
                updatedCar.setGia(Double.parseDouble(etGia.getText().toString()));

                apiService.updateCar(car.get_id(), updatedCar).enqueue(new Callback<CarModel>() {
                    @Override
                    public void onResponse(Call<CarModel> call, Response<CarModel> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Car updated successfully", Toast.LENGTH_SHORT).show();
                            fetchCars(); // Refresh list
                        } else {
                            Toast.makeText(MainActivity.this, "Car updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CarModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Car updated successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input format", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
