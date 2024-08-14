package fpoly.nghianmph38531.baithi_ph38531;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    private List<SanPhamModel> listSanPham;
    private Context context;
    ApiService apiService;

    public SanPhamAdapter(List<SanPhamModel> listSanPham, Context context) {
        this.listSanPham = listSanPham;
        this.context = context;
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SanPhamModel sanPham = listSanPham.get(position);
        holder.tvTenSanPham.setText("Tên điện thoại: " + sanPham.getTen_sanpham_ph38531());
        holder.tvMoTa.setText("Mô Tả: " + sanPham.getMo_ta_ph38531());
        holder.tvTrangThai.setText("Trạng Thái: " + String.valueOf(sanPham.getTrang_thai_ph38531()));


        // Hiển thị ảnh từ server
        Glide.with(context)
                .load(sanPham.getHinh_anh_ph38531()) // URL ảnh từ server
                .placeholder(R.drawable.placeholder_image) // Hình ảnh chờ khi tải ảnh
                .error(R.drawable.error_image) // Hình ảnh lỗi nếu không tải được ảnh
                .into(holder.ivHinhAnh); // ImageView để hiển thị ảnh


        holder.btnEdit.setOnClickListener(v -> {
            showEditDialog(sanPham, position, context);
        });

        holder.btnDelete.setOnClickListener(v -> {
            showDeleteConfirmationDialog(sanPham, position);
        });

        holder.tvTenSanPham.setOnClickListener(v -> {
            showDetailDialog(sanPham);
        });
    }

    private void showDeleteConfirmationDialog(SanPhamModel sanPham, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            Call<Void> deleteCall = apiService.deleteSanPham(sanPham.get_id());
            deleteCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        listSanPham.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, listSanPham.size());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API_ERROR", "Lỗi: " + t.getMessage());
                }
            });
        });

        builder.setNegativeButton("Không", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listSanPham.size();
    }

    public void addSanPham(SanPhamModel sanPham) {
        listSanPham.add(sanPham);
        notifyItemInserted(listSanPham.size() - 1);
    }

    private void showDetailDialog(SanPhamModel sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_detail, null);
        builder.setView(dialogView);

        TextView tvTenSanPham = dialogView.findViewById(R.id.tv_detail_ten_xe);
        TextView tvMoTa = dialogView.findViewById(R.id.tv_detail_mo_ta);
        TextView tvTrangThai = dialogView.findViewById(R.id.tv_detail_gia_ban);
        ImageView ivHinhAnh = dialogView.findViewById(R.id.iv_detail_hinh_anh);

        tvTenSanPham.setText("Tên điện thoại: " + sanPham.getTen_sanpham_ph38531());
        tvMoTa.setText("Mô Tả: " + sanPham.getMo_ta_ph38531());
        tvTrangThai.setText("Trạng Thái: " + String.valueOf(sanPham.getTrang_thai_ph38531()));

        Glide.with(context).load(sanPham.getHinh_anh_ph38531()).into(ivHinhAnh);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateSanPham(String id, SanPhamModel sanPhamModel, int position) {
        Call<SanPhamModel> call = apiService.updateSanPham(id, sanPhamModel);
        call.enqueue(new Callback<SanPhamModel>() {
            @Override
            public void onResponse(Call<SanPhamModel> call, Response<SanPhamModel> response) {
                if (response.isSuccessful()) {
                    listSanPham.set(position, response.body());
                    notifyItemChanged(position);
                }
            }

            @Override
            public void onFailure(Call<SanPhamModel> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi: " + t.getMessage());
            }
        });
    }

    private void showEditDialog(SanPhamModel sanPham, int position, Context context1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
        View dialogView = LayoutInflater.from(context1).inflate(R.layout.dialog_add, null);
        builder.setView(dialogView);

        EditText etTenSanPham = dialogView.findViewById(R.id.et_ten_xe);
        EditText etNgayNhap = dialogView.findViewById(R.id.et_mau_sac);
        EditText etTrangThai = dialogView.findViewById(R.id.et_gia_ban);
        EditText etMoTa = dialogView.findViewById(R.id.et_mo_ta);
        EditText etHinhAnh = dialogView.findViewById(R.id.et_hinh_anh);
        Button btnSubmit = dialogView.findViewById(R.id.btn_submit);

        etTenSanPham.setText(sanPham.getTen_sanpham_ph38531());
        etNgayNhap.setText(sanPham.getNgay_nhap_ph38531());
        etTrangThai.setText(String.valueOf(sanPham.getTrang_thai_ph38531()));
        etMoTa.setText(sanPham.getMo_ta_ph38531());
        etHinhAnh.setText(sanPham.getHinh_anh_ph38531());

        AlertDialog dialog = builder.create();

//        if(etTenSanPham.isEmpty()) {
////         etTenSanPham.setError("Tên sản phẩm không được để trống");
////         etTenSanPham.requestFocus();
////         return;
////     }
//     if (ngayNhap.isEmpty()) {
//         etNgayNhap.setError("Ngày nhập không được để trống");
//         etNgayNhap.requestFocus();
//         return;
//     }
        btnSubmit.setOnClickListener(v -> {
            String tenSanPham = etTenSanPham.getText().toString();
            String ngayNhap = etNgayNhap.getText().toString();
            double trangThai = Double.parseDouble(etTrangThai.getText().toString());
            String moTa = etMoTa.getText().toString();
            String hinhAnh = etHinhAnh.getText().toString();


            SanPhamModel updatedSanPham = new SanPhamModel(tenSanPham, ngayNhap, trangThai, moTa, hinhAnh);
            updateSanPham(sanPham.get_id(), updatedSanPham, position);
            dialog.dismiss();
        });

        dialog.show();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvMoTa, tvTrangThai;
        ImageView ivHinhAnh ,btnDelete, btnEdit;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.txt_ten_sanpham);
            tvMoTa = itemView.findViewById(R.id.txt_mo_ta);
            tvTrangThai = itemView.findViewById(R.id.txt_trang_thai);

            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            ivHinhAnh = itemView.findViewById(R.id.img_sanpham); // ImageView để hiển thị ảnh
        }
    }
}
