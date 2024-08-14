const mongoose = require('mongoose');

const sanPhamSchema = new mongoose.Schema({
    ten_sanpham_ph38531: { type: String, required: true },
    ngay_nhap_ph38531: { type: String, required: true },
    trang_thai_ph38531: { type: Number, required: true },
    mo_ta_ph38531: { type: String },
    hinh_anh_ph38531: { type: String }
});

module.exports = mongoose.model('dienthoai_18042004s', sanPhamSchema);
