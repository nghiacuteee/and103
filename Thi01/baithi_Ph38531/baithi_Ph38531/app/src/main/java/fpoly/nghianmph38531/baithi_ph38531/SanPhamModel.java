package fpoly.nghianmph38531.baithi_ph38531;

public class SanPhamModel {
    private String _id;
    private String ten_sanpham_ph38531;
    private String ngay_nhap_ph38531;
    private double trang_thai_ph38531;
    private String mo_ta_ph38531;
    private String hinh_anh_ph38531;

    public SanPhamModel() {
    }

    public SanPhamModel(String _id, String ten_sanpham_ph38531, String ngay_nhap_ph38531, double trang_thai_ph38531, String mo_ta_ph38531, String hinh_anh_ph38531) {
        this._id = _id;
        this.ten_sanpham_ph38531 = ten_sanpham_ph38531;
        this.ngay_nhap_ph38531 = ngay_nhap_ph38531;
        this.trang_thai_ph38531 = trang_thai_ph38531;
        this.mo_ta_ph38531 = mo_ta_ph38531;
        this.hinh_anh_ph38531 = hinh_anh_ph38531;
    }

    public SanPhamModel(String ten_sanpham_ph38531, String ngay_nhap_ph38531, double trang_thai_ph38531, String mo_ta_ph38531, String hinh_anh_ph38531) {
        this.ten_sanpham_ph38531 = ten_sanpham_ph38531;
        this.ngay_nhap_ph38531 = ngay_nhap_ph38531;
        this.trang_thai_ph38531 = trang_thai_ph38531;
        this.mo_ta_ph38531 = mo_ta_ph38531;
        this.hinh_anh_ph38531 = hinh_anh_ph38531;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTen_sanpham_ph38531() {
        return ten_sanpham_ph38531;
    }

    public void setTen_sanpham_ph38531(String ten_sanpham_ph38531) {
        this.ten_sanpham_ph38531 = ten_sanpham_ph38531;
    }

    public String getNgay_nhap_ph38531() {
        return ngay_nhap_ph38531;
    }

    public void setNgay_nhap_ph38531(String ngay_nhap_ph38531) {
        this.ngay_nhap_ph38531 = ngay_nhap_ph38531;
    }

    public double getTrang_thai_ph38531() {
        return trang_thai_ph38531;
    }

    public void setTrang_thai_ph38531(double trang_thai_ph38531) {
        this.trang_thai_ph38531 = trang_thai_ph38531;
    }

    public String getMo_ta_ph38531() {
        return mo_ta_ph38531;
    }

    public void setMo_ta_ph38531(String mo_ta_ph38531) {
        this.mo_ta_ph38531 = mo_ta_ph38531;
    }

    public String getHinh_anh_ph38531() {
        return hinh_anh_ph38531;
    }

    public void setHinh_anh_ph38531(String hinh_anh_ph38531) {
        this.hinh_anh_ph38531 = hinh_anh_ph38531;
    }

}
