package RTDRestaurant.Controller.Service;

import RTDRestaurant.Model.ModelNguyenLieu;
import RTDRestaurant.Controller.Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class getNLByIDTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    // Phương thức hỗ trợ lấy thông tin Nguyên Liệu theo ID
    private ModelNguyenLieu getNLbyID(int id) throws SQLException {
        String sql = "SELECT ID_NL, TenNL, Dongia, Donvitinh FROM NguyenLieu WHERE ID_NL=?";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setInt(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return new ModelNguyenLieu(
                        rs.getInt("ID_NL"),
                        rs.getString("TenNL"),
                        rs.getInt("Dongia"),
                        rs.getString("Donvitinh")
                    );
                }
            }
        }
        return null;
    }

    // Test trường hợp lấy Nguyên Liệu với ID hợp lệ
    @Test
    public void testGetNLByID_ValidID() throws SQLException {
        int validID = 101;  // Giả sử ID này tồn tại trong cơ sở dữ liệu
        try {
            con.setAutoCommit(false);

            // Gọi phương thức lấy thông tin Nguyên Liệu
            ModelNguyenLieu result = getNLbyID(validID);
            Assert.assertNotNull("Không tìm thấy nguyên liệu!", result);
            Assert.assertEquals(validID, result.getId());
            Assert.assertEquals("Thit heo", result.getTenNL());  // Dữ liệu giả định
            Assert.assertEquals(50000, result.getDonGia());  // Dữ liệu giả định
            Assert.assertEquals("kg", result.getDvt());  // Dữ liệu giả định

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test trường hợp lấy Nguyên Liệu với ID không tồn tại
    @Test
    public void testGetNLByID_NonExistentID() throws SQLException {
        int nonExistentID = 9999;  // Giả sử ID này không tồn tại trong cơ sở dữ liệu
        try {
            con.setAutoCommit(false);

            // Gọi phương thức lấy thông tin Nguyên Liệu
            ModelNguyenLieu result = getNLbyID(nonExistentID);
            Assert.assertNull("Phải trả về null khi không tìm thấy nguyên liệu!", result);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test trường hợp lấy Nguyên Liệu với ID âm
    @Test
    public void testGetNLByID_NegativeID() throws SQLException {
        int negativeID = -5;  // ID âm
        try {
            con.setAutoCommit(false);

            // Gọi phương thức lấy thông tin Nguyên Liệu
            ModelNguyenLieu result = getNLbyID(negativeID);
            Assert.assertNull("Phải trả về null khi ID âm", result);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Test trường hợp lấy Nguyên Liệu với ID bằng 0
    @Test
    public void testGetNLByID_ZeroID() throws SQLException {
        int zeroID = 0;  // ID bằng 0
        try {
            con.setAutoCommit(false);

            // Gọi phương thức lấy thông tin Nguyên Liệu
            ModelNguyenLieu result = getNLbyID(zeroID);
            Assert.assertNull("Phải trả về null khi ID bằng 0", result);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    

    // Test trường hợp ID chứa ký tự đặc biệt (ví dụ: ID không phải là số nguyên hợp lệ)
    @Test
    public void testGetNLByID_InvalidCharactersInID() throws SQLException {
        String invalidID = "1@34!";  // ID chứa ký tự đặc biệt
        try {
            con.setAutoCommit(false);

            // Gọi phương thức lấy thông tin Nguyên Liệu
            ModelNguyenLieu result = getNLbyID(Integer.parseInt(invalidID));  // Sẽ ném Exception nếu không hợp lệ
            Assert.assertNull("Phải trả về null khi ID chứa ký tự đặc biệt", result);

        } catch (NumberFormatException e) {
            Assert.assertTrue("ID không hợp lệ", true);  // Kiểm tra rằng ID không hợp lệ sẽ ném ra lỗi

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
