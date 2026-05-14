CREATE DATABASE IF NOT EXISTS `coffee_shop_management`;
USE `coffee_shop_management`;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

-- BẢNG ĐỘC LẬP (Không chứa khóa ngoại)
CREATE TABLE `giamgia` (
  `MaGiam` int NOT NULL AUTO_INCREMENT,
  `TenGiamGia` text NOT NULL,
  `PhanTramGiam` int NOT NULL,
  `DieuKien` int NOT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL,
  PRIMARY KEY (`MaGiam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `khachhang` (
  `MaKH` int NOT NULL AUTO_INCREMENT,
  `Ho` varchar(255) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `GioiTinh` varchar(10) NOT NULL,
  `TongChiTieu` int NOT NULL DEFAULT '0',
  `TinhTrang` int NOT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `loai` (
  `MaLoai` int NOT NULL AUTO_INCREMENT,
  `TenLoai` text NOT NULL,
  PRIMARY KEY (`MaLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `phanquyen` (
  `Quyen` varchar(255) NOT NULL,
  `QLSanPham` int NOT NULL,
  `QLNhanVien` int NOT NULL,
  `QLKhachHang` int NOT NULL,
  `ThongKe` int NOT NULL,
  PRIMARY KEY (`Quyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `nhanvien` (
  `MaNV` int NOT NULL AUTO_INCREMENT,
  `Ho` varchar(255) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `GioiTinh` varchar(10) NOT NULL,
  `ChucVu` varchar(255) NOT NULL,
  PRIMARY KEY (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- BẢNG PHỤ THUỘC (Có khóa ngoại)
CREATE TABLE `sanpham` (
  `MaSP` int NOT NULL AUTO_INCREMENT,
  `TenSP` varchar(255) NOT NULL,
  `MaLoai` int NOT NULL,
  `SoLuong` int NOT NULL,
  `DonViTinh` varchar(255) NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `DonGia` int NOT NULL,
  PRIMARY KEY (`MaSP`),
  KEY `sanpham_ibfk_1` (`MaLoai`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loai` (`MaLoai`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taikhoan` (
  `MaNV` int NOT NULL,
  `TenDangNhap` varchar(255) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `Quyen` varchar(255) NOT NULL,
  `TrangThai` int NOT NULL,
  PRIMARY KEY (`MaNV`),
  KEY `taikhoan_ibfk_2` (`Quyen`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`Quyen`) REFERENCES `phanquyen` (`Quyen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hoadon` (
  `MaHD` int NOT NULL AUTO_INCREMENT,
  `MaKH` int NOT NULL,
  `MaNV` int NOT NULL,
  `NgayLap` date NOT NULL,
  `TongTien` int NOT NULL,
  `GhiChu` text NOT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `hoadon_ibfk_1` (`MaNV`),
  KEY `hoadon_ibfk_2` (`MaKH`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cthoadon` (
  `MaHD` int NOT NULL,
  `MaSP` int NOT NULL,
  `SoLuong` int NOT NULL,
  `DonGia` int NOT NULL,
  `ThanhTien` int NOT NULL,
  PRIMARY KEY (`MaHD`,`MaSP`),
  KEY `fk_cthoadon_sanpham` (`MaSP`),
  CONSTRAINT `cthoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cthoadon_sanpham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- INSERT DỮ LIỆU MẪU
INSERT INTO `giamgia` VALUES 
(1,'Không giảm giá',0,0,'2025-05-05','2042-12-31'),
(2,'Khách hàng thân thiết',20,0,'2025-05-05','2026-06-01');

INSERT INTO `khachhang` VALUES 
(1,'Ngô Quang','Vinh','Nam',480000,1),
(2,'Đỗ Thiện','Quân','Nam',130000,1),
(3,'Nguyễn Minh','Nhật','Nữ',468000,1),
(4,'Nguyễn Hải','Sơn','Nam',32000,1);

INSERT INTO `loai` VALUES 
(1,'Đồ uống'),
(2,'Bánh'),
(3,'Soft drink'),
(4,'Beer');

INSERT INTO `phanquyen` VALUES 
('Default',0,0,0,0),
('Nhân viên',0,0,1,0),
('Quản lý',0,1,1,1),
('Quản trị',1,1,1,1);

INSERT INTO `nhanvien` VALUES 
(0,'Admin','','','Quản trị'),
(1,'Ngô Quang','Vinh','Nam','Quản lý'),
(2,'Đỗ Minh','Quân','Nam','Nhân viên'),
(5,'Lê Thuỳ','Na','Nữ','Nhân viên');

INSERT INTO `sanpham` VALUES 
(1,'Almond',2,20,'Cái','Almond.png',30000),
(2,'Bạc Xỉu',1,100,'Cốc','bac_xiu.png',20000),
(3,'Blue Berry Smothie',1,99,'Cốc','blue_berry_smothie.png',50000),
(4,'Chocolate Cake',2,19,'Cái','Chocolate.png',30000),
(5, 'Chocolate Cookie', 1, 97, 'Cốc', 'chocolate_cookie.png', 50000),
(6,'Coke',3,97,'Lon','cocacola.png',20000),
(7,'Coconut Cake',2,18,'Cái','coconut.png',30000),
(8,'Coconut Coffee',1,93,'Cốc','coconut_coffee.png',50000),
(9,'Coconut Matcha Latte',1,93,'Cốc','coconut_matcha_latte.png',40000),
(16,'Hanoi Beer',4,97,'Chai','hanoi_beer.png',30000);


INSERT INTO `taikhoan` VALUES 
(0,'admin','admin','Quản trị',1),
(1,'ql01','ql01','Quản lý',1),
(2,'nv01','nv01','Nhân viên',1),
(5,'nv02','nv02','Nhân viên',1);

INSERT INTO `hoadon` VALUES 
(1, 1, 1, '2025-05-09', 180000, 'Đã thanh toán'),
(2, 2, 2, '2025-05-09', 130000, 'Đã thanh toán'),
(4, 4, 0, '2025-05-10', 32000, 'Đã thanh toán'),
(5, 1, 0, '2025-05-10', 250000, 'Đã thanh toán'),
(6, 3, 0, '2025-05-10', 168000, 'Đã thanh toán'),
(7, 3, 0, '2025-05-10', 40000, 'Đã thanh toán'),
(8, 1, 0, '2025-05-09', 50000, 'Đã thanh toán');

INSERT INTO `cthoadon` VALUES 
(1, 1, 2, 20000, 40000),
(1, 2, 1, 40000, 40000),
(1, 3, 1, 40000, 40000),
(1, 4, 1, 40000, 40000),
(1, 7, 2, 10000, 20000),
(2, 1, 2, 20000, 40000),
(2, 3, 1, 40000, 40000),
(2, 6, 1, 30000, 30000),
(2, 7, 2, 10000, 20000),
(4, 6, 1, 30000, 30000),
(4, 7, 1, 10000, 10000),
(5, 3, 1, 50000, 50000),
(5, 6, 1, 20000, 20000),
(5, 7, 1, 30000, 30000),
(5, 8, 5, 50000, 150000),
(6, 4, 1, 30000, 30000),
(6, 7, 1, 30000, 30000),
(6, 9, 6, 40000, 120000),
(6, 16, 3, 30000, 30000),
(7, 8, 1, 50000, 50000),
(8, 5, 1, 50000, 50000);
