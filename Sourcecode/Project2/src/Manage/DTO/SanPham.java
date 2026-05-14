package Manage.DTO;

/**
 * Class dai dien cho mot san pham trong he thong
 */
public class SanPham {
    private int maSP;
    private String tenSP;
    private int maLoai;
    private int soLuong;
    private String donViTinh;
    private String hinhAnh;
    private int donGia;

    /**
     * Khoi tao mot san pham moi voi cac gia tri mac dinh
     */
    public SanPham() {
        this.maSP = 0;
        this.tenSP = "";
        this.maLoai = 0;
        this.soLuong = 0;
        this.donViTinh = "";
        this.hinhAnh = "";
        this.donGia = 0;
    }

    /**
     * Khoi tao mot san pham moi voi cac gia tri duoc chi dinh
     * @param maSP Ma san pham
     * @param tenSP Ten san pham
     * @param maLoai Ma loai san pham
     * @param soLuong So luong
     * @param donViTinh Don vi tinh
     * @param hinhAnh Duong dan hinh anh
     * @param donGia Don gia
     */
    public SanPham(int maSP, String tenSP, int maLoai, int soLuong, String donViTinh, String hinhAnh, int donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        if (maSP < 0) {
            throw new IllegalArgumentException("Ma san pham khong duoc am");
        }
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        if (tenSP == null || tenSP.trim().isEmpty()) {
            throw new IllegalArgumentException("Ten san pham khong duoc de trong");
        }
        this.tenSP = tenSP.trim();
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        if (maLoai < 0) {
            throw new IllegalArgumentException("Ma loai khong duoc am");
        }
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("So luong khong duoc am");
        }
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        if (donViTinh == null || donViTinh.trim().isEmpty()) {
            throw new IllegalArgumentException("Don vi tinh khong duoc de trong");
        }
        this.donViTinh = donViTinh.trim();
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh != null ? hinhAnh.trim() : "";
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Don gia khong duoc am");
        }
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", maLoai=" + maLoai +
                ", soLuong=" + soLuong +
                ", donViTinh='" + donViTinh + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", donGia=" + donGia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanPham sanPham = (SanPham) o;
        return maSP == sanPham.maSP;
    }

    @Override
    public int hashCode() {
        return maSP;
    }
}
