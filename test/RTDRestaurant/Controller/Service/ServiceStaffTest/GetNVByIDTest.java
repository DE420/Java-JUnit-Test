package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelNhanVien;
import java.sql.Connection;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class GetNVByIDTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    @Test
    public void testGetStaff_existUser() {
        int existingUserId = 100; // ID_ND tồn tại trong CSDL
        try {
            ModelNhanVien nv = ss.getStaff(existingUserId);
            Assert.assertNotNull("Nhân viên không được null", nv);

            // In ra để kiểm chứng nếu cần
            System.out.println("Tên nhân viên: " + nv.getTenNV());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi khi lấy nhân viên: " + e.getMessage());
        }
    }

    @Test
    public void testGetStaff_notExistUser() {
        int nonExistUserId = 99999; // ID_ND không tồn tại
        try {
            ModelNhanVien nv = ss.getStaff(nonExistUserId);
            Assert.assertNull("Nhân viên phải là null vì ID không tồn tại", nv);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi khi test ID không tồn tại: " + e.getMessage());
        }
    }

    @Test
    public void testGetStaff_countReturnedRows() {
        int userID = 10; // bạn chắc chắn có 1 user ID này trong DB

        int rowCount = 0;
        try {
            String sql = "SELECT ID_NV FROM NhanVien WHERE ID_ND=?";
            var ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            var rs = ps.executeQuery();
            while (rs.next()) {
                rowCount++;
            }
            rs.close();
            ps.close();

            Assert.assertEquals("SELECT chỉ nên trả về tối đa 1 dòng", 1, rowCount);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi khi đếm dòng SELECT: " + e.getMessage());
        }
    }
}
