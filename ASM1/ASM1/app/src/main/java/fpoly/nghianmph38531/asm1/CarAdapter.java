package fpoly.nghianmph38531.asm1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CarAdapter extends BaseAdapter {

    private final Context context;
    private List<CarModel> cars;
    private final CarActionListener listener;

    public CarAdapter(Context context, List<CarModel> cars, CarActionListener listener) {
        this.context = context;
        this.cars = cars;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public CarModel getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        }

        CarModel car = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvNamSX = convertView.findViewById(R.id.tvNamSX);
        TextView tvHang = convertView.findViewById(R.id.tvHang);
        TextView tvGia = convertView.findViewById(R.id.tvGia);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        tvName.setText(car.getTen());
        tvNamSX.setText(String.valueOf(car.getNamSX()));
        tvHang.setText(car.getHang());
        tvGia.setText(String.valueOf(car.getGia()));

        btnEdit.setOnClickListener(v -> listener.onEdit(car));
        btnDelete.setOnClickListener(v -> listener.onDelete(car));

        return convertView;
    }

    public void updateData(List<CarModel> newCars) {
        this.cars = newCars;

        notifyDataSetChanged();
    }

    public interface CarActionListener {
        void onEdit(CarModel car);
        void onDelete(CarModel car);
    }
}
