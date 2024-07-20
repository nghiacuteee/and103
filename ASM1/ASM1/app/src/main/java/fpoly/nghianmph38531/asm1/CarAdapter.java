package fpoly.nghianmph38531.asm1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAdapter extends BaseAdapter {

    private List<CarModel> carModelList;
    private Context context;
    private APIService apiService;

    public CarAdapter(Context context, List<CarModel> carModelList, APIService apiService) {
        this.context = context;
        this.carModelList = carModelList;
        this.apiService = apiService;
    }

    @Override
    public int getCount() {
        return carModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return carModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_car, parent, false);
        }

        TextView tvID = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvNamSX = convertView.findViewById(R.id.tvNamSX);
        TextView tvHang = convertView.findViewById(R.id.tvHang);
        TextView tvGia = convertView.findViewById(R.id.tvGia);

        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        CarModel car = carModelList.get(position);

        tvID.setText(car.get_id());
        tvName.setText(car.getTen());
        tvNamSX.setText(String.valueOf(car.getNamSX()));
        tvHang.setText(car.getHang());
        tvGia.setText(String.valueOf(car.getGia()));

//        btnUpdate.setOnClickListener(v -> {
//            CarModel updatedCar = new CarModel(car.get_id(), "Updated Name", car.getNamSX(), car.getHang(), car.getGia());
//            apiService.updateCar(car.get_id(), updatedCar).enqueue(new Callback<CarModel>() {
//                @Override
//                public void onResponse(Call<CarModel> call, Response<CarModel> response) {
//                    if (response.isSuccessful()) {
//                        carModelList.set(position, response.body());
//                        notifyDataSetChanged();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<CarModel> call, Throwable t) {
//                    // Handle failure
//                }
//            });
//        });

        btnDelete.setOnClickListener(v -> {
            apiService.deleteCar(car.get_id()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        carModelList.remove(position);
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle failure
                }
            });
        });

        return convertView;
    }
}
