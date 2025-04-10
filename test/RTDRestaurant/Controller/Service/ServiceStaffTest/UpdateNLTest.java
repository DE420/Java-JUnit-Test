package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelNguyenLieu;
import RTDRestaurant.Model.ModelNhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import junit.framework.Assert;
import org.junit.Before;

public class UpdateNLTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();  // Sử dụng ServiceStaff chung cho cả nhân viên và nguyên liệu
    }

    // Hàm này có thể dùng chung để cập nhật nhân viên và nguyên liệu
    private int getAffectedRows(Object data) throws SQLException {
        String sql;
        if (data instanceof ModelNhanVien) {
            sql = "UPDATE NhanVien SET TenNV=? WHERE ID_NV=?";
        } else if (data instanceof ModelNguyenLieu) {
            sql = "UPDATE NguyenLieu SET TenNL=?, Dongia=?, Donvitinh=? WHERE ID_NL=?";
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }

        try (PreparedStatement p = con.prepareStatement(sql)) {
            if (data instanceof ModelNhanVien) {
                ModelNhanVien nv = (ModelNhanVien) data;
                p.setString(1, nv.getTenNV());
                p.setInt(2, nv.getId_NV());
            } else if (data instanceof ModelNguyenLieu) {
                ModelNguyenLieu nl = (ModelNguyenLieu) data;
                p.setString(1, nl.getTenNL());
                p.setInt(2, nl.getDonGia());
                p.setString(3, nl.getDvt());
                p.setInt(4, nl.getId());
            }
            return p.executeUpdate();  // Trả về số dòng bị ảnh hưởng
        }
    }

    // Hàm này dùng chung cho nhân viên và nguyên liệu
    private String getNameById(Object data) throws SQLException {
        String sql;
        String nameColumn;
        if (data instanceof ModelNhanVien) {
            sql = "SELECT TenNV FROM NhanVien WHERE ID_NV=?";
            nameColumn = "TenNV";
        } else if (data instanceof ModelNguyenLieu) {
            sql = "SELECT TenNL FROM NguyenLieu WHERE ID_NL=?";
            nameColumn = "TenNL";
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }

        try (PreparedStatement p = con.prepareStatement(sql)) {
            if (data instanceof ModelNhanVien) {
                ModelNhanVien nv = (ModelNhanVien) data;
                p.setInt(1, nv.getId_NV());
            } else if (data instanceof ModelNguyenLieu) {
                ModelNguyenLieu nl = (ModelNguyenLieu) data;
                p.setInt(1, nl.getId());
            }
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(nameColumn);
                }
            }
        }
        return null;
    }

    // Kiểm tra các điều kiện lỗi: giá trị âm, trống, kí tự đặc biệt, quá dài
    private boolean isInvalid(ModelNguyenLieu nl) {
        return nl.getDonGia() < 0 || nl.getTenNL().isEmpty() || nl.getTenNL().length() > 20 || nl.getTenNL().matches(".*[!@#$%^&*()_+=<>?/{}|].*");
    }

    private boolean isInvalid(ModelNhanVien nv) {
        return nv.getTenNV().isEmpty() || nv.getTenNV().length() > 20 || nv.getTenNV().matches(".*[!@#$%^&*()_+=<>?/{}|].*");
    }

    // Test Case 1: Cập nhật thành công
    @Test
    public void testUpdateNguyenLieu_Success() throws SQLException {
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(100);
        nl.setTenNL("Gạo");
        nl.setDonGia(10000);
        nl.setDvt("kg");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nl);
            Assert.assertTrue("Không có dòng nào bị ảnh hưởng!", affectedRows > 0);

            String updatedName = getNameById(nl);
            Assert.assertEquals("Gạo", updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test Case 2: Kiểm tra nếu giá trị âm, trống, có ký tự đặc biệt hoặc quá dài
    @Test
    public void testUpdateNguyenLieu_InvalidFields() throws SQLException {
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(100);
        nl.setDonGia(-10000); // DonGia âm
        nl.setTenNL("Gạo@123"); // Kí tự đặc biệt
        nl.setDvt("kg");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nl);
            if (isInvalid(nl)) {
                Assert.assertEquals("Update không nên thực hiện với giá trị không hợp lệ!", 0, affectedRows);
            } else {
                Assert.fail("Lệnh UPDATE đã thực hiện mặc dù giá trị không hợp lệ.");
            }

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test Case 3: Kiểm tra tên trống
    @Test
    public void testUpdateNguyenLieu_EmptyName() throws SQLException {
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(100);
        nl.setTenNL(""); // Tên trống
        nl.setDonGia(10000);
        nl.setDvt("kg");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nl);
            if (isInvalid(nl)) {
                Assert.assertEquals("Update không nên thực hiện với tên trống!", 0, affectedRows);
            } else {
                Assert.fail("Lệnh UPDATE đã thực hiện mặc dù tên trống.");
            }

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test Case 4: Kiểm tra tên quá dài (> 20 ký tự)
    @Test
    public void testUpdateNguyenLieu_NameTooLong() throws SQLException {
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(100);
        nl.setTenNL("Gạo với tên dài hơn 20 ký tự");
        nl.setDonGia(10000);
        nl.setDvt("kg");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nl);
            if (isInvalid(nl)) {
                Assert.assertEquals("Update không nên thực hiện với tên quá dài!", 0, affectedRows);
            } else {
                Assert.fail("Lệnh UPDATE đã thực hiện mặc dù tên quá dài.");
            }

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test Case 5: Kiểm tra nếu không tồn tại ID
    @Test
    public void testUpdateNguyenLieu_IDNotFound() throws SQLException {
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(9999); // ID không tồn tại trong cơ sở dữ liệu
        nl.setTenNL("Gạo");
        nl.setDonGia(10000);
        nl.setDvt("kg");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nl);
            Assert.assertEquals("Update không nên thực hiện khi ID không tồn tại!", 0, affectedRows);

            String updatedName = getNameById(nl);
            Assert.assertNull(updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
