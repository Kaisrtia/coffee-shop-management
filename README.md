# 🎓 DAI HOC DA NANG - TRUONG DAI HOC BACH KHOA
## KHOA CONG NGHE THONG TIN
### DO AN MON LAP TRINH JAVA
### De tai: Ung Dung Quan Ly Quan Ca Phe

## 💡 Gioi Thieu
**Coffee Manager** la mot phan mem quan ly quan ca phe duoc phat trien bang **Java** su dung **NetBeans IDE**. Ung dung duoc xay dung theo mo hinh 3 lop (GUI - BUS - DAO), cung cap cac chuc nang ho tro ban hang, quan ly san pham, hoa don, nhan vien va khach hang mot cach de dang va hieu qua.
## 🛠️ Cong Nghe Su Dung

- **Ngon ngu:** Java
- **IDE:** NetBeans
- **Giao dien nguoi dung:** Java Swing.
- **Co so du lieu:** MySQL hoac SQL Server
- **Mo hinh:** 3 lop (GUI - BUS - DAO)

## 🧱 Cau Truc Thu Muc
- Thu muc Main: Lop khoi dong chinh, entry point cua ung dung
- Thu muc Manage.BUS: Lop xu ly nghiep vu (Business Logic Layer)
- Thu muc Manage.DAO: Lop truy xuat du lieu (Data Access Layer)
- Thu muc Manage.DTO: Lop dinh nghia doi tuong du lieu (Data Transfer Object)
- Thu muc Manage.GUI: Lop giao dien nguoi dung.
- Thu muc MyCustom: Cac cau hinh dung chung, tien ich, constant, enum,...
- Thu muc Libs: Thu vien ngoai chuong trinh su dung trong du an.

## 📌 Chuc Nang Chinh

### 1. Ban Hang

- Hien thi danh sach san pham (ma SP, ten, gia, ton kho, don vi)
- Loc theo loai san pham
- Them san pham vao gio hang
- Tinh tong tien
- Xuat hoa don thanh toan

### 2. Quan Ly San Pham

- Them / sua / xoa san pham
- Lien ket san pham voi loai
- Cap nhat ton kho

### 3. Quan Ly Hoa Don

- Tao hoa don khi thanh toan
- Luu thong tin hoa don va chi tiet tung mat hang
- Tim kiem hoa don theo thoi gian hoac nhan vien

### 4. Quan Ly Nhan Vien

- Quan ly tai khoan dang nhap
- Phan quyen cho nhan vien: thu ngan, quan ly

### 5. Quan Ly Khach Hang

- Them / sua / xoa thong tin khach
- Theo doi lich su mua hang
- Theo doi tong thanh toan
### 6. Quan Ly Khuyen mai

- Them / sua / xoa thong tin ve chuong trinh khuyen mai

### 7. Thong Ke & Bao Cao

- Thong ke doanh thu theo ngay, thang
- Thong ke so luong san pham da ban
  In bieu do bao cao theo thang / nam

## ▶️ Huong Dan Chay Du An

1. Mo project bang **NetBeans IDE**
2. Tao database bang file script coffeeshop.sql co san bang MySql Workbench
3. Cau hinh chuoi ket noi CSDL trong file ket noi
4. Nhan `Run` de chay ung dung



## 🗃️ Co So Du Lieu Cau Truc Co Ban:
- sanpham (MaSP INT, TenSP VARCHAR, MaLoai INT, SoLuong INT, DonViTinh VARCHAR, HinhAnh VARCHAR, DonGia INT)
  → FK MaLoai → loai

- loai (MaLoai INT, TenLoai TEXT)

- hoadon (MaHD INT, MaKH INT, MaNV INT, NgayLap DATE, TongTien INT, GhiChu TEXT)
  → FK MaKH → khachhang, MaNV → nhanvien

- cthoadon (MaHD INT, MaSP INT, SoLuong INT, DonGia INT, ThanhTien INT)
  → FK MaHD → hoadon, MaSP → sanpham

- khachhang (MaKH INT, Ho VARCHAR, Ten VARCHAR, GioiTinh VARCHAR, TongChiTieu INT, TinhTrang INT)

- nhanvien (MaNV INT, Ho VARCHAR, Ten VARCHAR, GioiTinh VARCHAR(3), ChucVu VARCHAR)

- taikhoan (MaNV INT, TenDangNhap VARCHAR, MatKhau VARCHAR, Quyen VARCHAR, TrangThai INT)
  → PK dong thoi la FK den nhanvien.MaNV, Quyen → phanquyen

- phanquyen (Quyen VARCHAR, QLSanPham INT, QLNhanVien INT, QLKhachHang INT, ThongKe INT)

- giamgia (MaGiam INT, TenGiamGia TEXT, PhanTramGiam INT, DieuKien TEXT, NgayBD DATE, NgayKT DATE)

## 🎯 Ket qua dat duoc

- Giao dien trang ban hang

- Giao dien trang quan ly san pham

- Giao dien trang quan ly nhan vien

- Giao dien trang quan ly khuyen mai

- Giao dien trang quan ly phan quyen


