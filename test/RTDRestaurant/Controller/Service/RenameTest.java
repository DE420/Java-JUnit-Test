package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelNhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import junit.framework.Assert;
import org.junit.Before;

public class ServiceStaffTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    private int getAffectedRows(ModelNhanVien data) throws SQLException {
        String sql = "UPDATE NhanVien SET TenNV=? WHERE ID_NV=?";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, data.getTenNV());
            p.setInt(2, data.getId_NV());
            return p.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        }
    }

    private String getStaffNameById(int id) throws SQLException {
        String sql = "SELECT TenNV FROM NhanVien WHERE ID_NV=?";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setInt(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TenNV");
                }
            }
        }
        return null;
    }

    @Test
    public void testRenameStaff_Success() throws SQLException {
        ModelNhanVien nv = new ModelNhanVien();
        nv.setId_NV(101);
        nv.setTenNV("Le Van Trung");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nv);
            Assert.assertTrue("Không có dòng nào bị ảnh hưởng!", affectedRows > 0);

            String updatedName = getStaffNameById(101);
            Assert.assertEquals("Le Van Trung", updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    @Test
    public void testRenameStaff_IDNotFound() throws SQLException {
        ModelNhanVien nv = new ModelNhanVien();
        nv.setId_NV(9999);
        nv.setTenNV("Le Van Trung");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nv);
            Assert.assertEquals("Update không nên ảnh hưởng đến bất kỳ dòng nào!", 0, affectedRows);

            String updatedName = getStaffNameById(9999);
            Assert.assertNull(updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    @Test
    public void testRenameStaff_EmptyName() throws SQLException {
        ModelNhanVien nv = new ModelNhanVien();
        nv.setId_NV(102);
        nv.setTenNV("");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nv);
            Assert.assertEquals("Update không nên thực hiện với tên trống!", 0, affectedRows);

            String updatedName = getStaffNameById(102);
            Assert.assertNotSame("", updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    @Test
    public void testRenameStaff_InvalidCharacters() throws SQLException {
        ModelNhanVien nv = new ModelNhanVien();
        nv.setId_NV(103);
        nv.setTenNV("Le Van Trung@gmail");

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nv);
            Assert.assertEquals("Update không nên thực hiện với tên chứa ký tự đặc biệt!", 0, affectedRows);

            String updatedName = getStaffNameById(103);
            Assert.assertNotSame("Le Van Trung@gmail", updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    @Test
    public void testRenameStaff_NameTooLong() throws SQLException {
        ModelNhanVien nv = new ModelNhanVien();
        nv.setId_NV(104);
        nv.setTenNV("A".repeat(101)); // 101 ký tự

        try {
            con.setAutoCommit(false);
            int affectedRows = getAffectedRows(nv);
            Assert.assertEquals("Update không nên thực hiện với tên quá dài!", 0, affectedRows);

            String updatedName = getStaffNameById(104);
            Assert.assertNotSame("A".repeat(101), updatedName);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
