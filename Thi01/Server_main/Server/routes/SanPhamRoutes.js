const express = require('express');
const router = express.Router();
const SanPham = require('../models/DienThoai_14082024'); // Update the model file name

// Lấy tất cả sản phẩm
router.get('/', async (req, res) => {
    try {
        const sanPhams = await SanPham.find();
        res.json(sanPhams);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// Thêm sản phẩm mới
router.post('/', async (req, res) => {
    const sanPham = new SanPham({
        ten_sanpham_ph38531: req.body.ten_sanpham_ph38531,
        ngay_nhap_ph38531: req.body.ngay_nhap_ph38531,
        trang_thai_ph38531: req.body.trang_thai_ph38531,
        mo_ta_ph38531: req.body.mo_ta_ph38531,
        hinh_anh_ph38531: req.body.hinh_anh_ph38531
    });
    try {
        const newSanPham = await sanPham.save();
        res.status(201).json(newSanPham);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
});

// Cập nhật thông tin sản phẩm
router.put('/:id', async (req, res) => {
    try {
        const sanPham = await SanPham.findById(req.params.id);
        if (!sanPham) return res.status(404).json({ message: 'Không tìm thấy sản phẩm' });

        sanPham.ten_sanpham_ph38531 = req.body.ten_sanpham_ph38531;
        sanPham.ngay_nhap_ph38531 = req.body.ngay_nhap_ph38531;
        sanPham.trang_thai_ph38531 = req.body.trang_thai_ph38531;
        sanPham.mo_ta_ph38531 = req.body.mo_ta_ph38531;
        sanPham.hinh_anh_ph38531 = req.body.hinh_anh_ph38531;

        const updatedSanPham = await sanPham.save();
        res.json(updatedSanPham);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
});

// Xóa sản phẩm
router.delete('/:id', async (req, res) => {
    try {
        const sanPham = await SanPham.findById(req.params.id);
        if (!sanPham) return res.status(404).json({ message: 'Không tìm thấy sản phẩm' });

        await SanPham.findByIdAndDelete(req.params.id);
        res.json({ message: 'Đã xóa sản phẩm' });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// Tìm kiếm sản phẩm theo tên
router.get('/search', async (req, res) => {
    try {
        const query = req.query.ten_sanpham_ph38531;
        const sanPhams = await SanPham.find({ ten_sanpham_ph38531: new RegExp(query, 'i') });
        res.json(sanPhams);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;

// private void showAddDialog() {
//     AlertDialog.Builder builder = new AlertDialog.Builder(this);
//     LayoutInflater inflater = getLayoutInflater();
//     View dialogView = inflater.inflate(R.layout.dialog_add, null);
//     builder.setView(dialogView);

//     EditText etTenSanPham = dialogView.findViewById(R.id.et_ten_xe);
//     EditText etNgayNhap = dialogView.findViewById(R.id.et_mau_sac);
//     EditText etTrangThai = dialogView.findViewById(R.id.et_gia_ban);
//     EditText etMoTa = dialogView.findViewById(R.id.et_mo_ta);
//     EditText etHinhAnh = dialogView.findViewById(R.id.et_hinh_anh);
//     Button btnSubmit = dialogView.findViewById(R.id.btn_submit);

//     AlertDialog dialog = builder.create();

//     btnSubmit.setOnClickListener(v -> {
//         String tenSanPham = etTenSanPham.getText().toString().trim();
//         String ngayNhap = etNgayNhap.getText().toString().trim();
//         String trangThaiStr = etTrangThai.getText().toString().trim();
//         String moTa = etMoTa.getText().toString().trim();
//         String hinhAnh = etHinhAnh.getText().toString().trim();

//         // Validate inputs
//         if(tenSanPham.isEmpty()) {
//         etTenSanPham.setError("Tên sản phẩm không được để trống");
//         etTenSanPham.requestFocus();
//         return;
//     }

//     if (ngayNhap.isEmpty()) {
//         etNgayNhap.setError("Ngày nhập không được để trống");
//         etNgayNhap.requestFocus();
//         return;
//     }

//     if (trangThaiStr.isEmpty()) {
//         etTrangThai.setError("Trạng thái không được để trống");
//         etTrangThai.requestFocus();
//         return;
//     }

//         double trangThai;
//     try {
//         trangThai = Double.parseDouble(trangThaiStr);
//     } catch (NumberFormatException e) {
//         etTrangThai.setError("Trạng thái phải là số");
//         etTrangThai.requestFocus();
//         return;
//     }

//     if (moTa.isEmpty()) {
//         etMoTa.setError("Mô tả không được để trống");
//         etMoTa.requestFocus();
//         return;
//     }

//     if (hinhAnh.isEmpty()) {
//         etHinhAnh.setError("Hình ảnh không được để trống");
//         etHinhAnh.requestFocus();
//         return;
//     }

//         // If all validations pass, create the new product
//         SanPhamModel newSanPham = new SanPhamModel(tenSanPham, ngayNhap, trangThai, moTa, hinhAnh);
//     addSanPham(newSanPham);
//     dialog.dismiss();
// });

// dialog.show();
// }

