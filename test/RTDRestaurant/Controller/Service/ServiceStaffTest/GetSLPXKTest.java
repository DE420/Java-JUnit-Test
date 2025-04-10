package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class GetSLPXKTest {

    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connectToDatabase();
        con = db.getConnection();
        ss = new ServiceStaff();
    }

    /**
     * Test case: Ngày hiện tại có phiếu xuất kho.
     */
    @Test
    public void testGetSLPXK_todayHasRecords() {
        try {
            con.setAutoCommit(false);
            
            // Lấy ngày hôm nay
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String today = sdf.format(new Date());

            // Chèn tạm 1 phiếu xuất kho vào ngày hôm nay
            String insertSQL = "INSERT INTO PhieuXK(ID_XK, ID_NV, NgayXK) VALUES (999, 102, TO_DATE(?, 'dd-mm-yyyy'))";
            PreparedStatement ps = con.prepareStatement(insertSQL);
            ps.setString(1, today);
            ps.executeUpdate();
            ps.close();

            // Gọi hàm kiểm tra
            int sl = ss.getSLPXK();
            Assert.assertTrue("Số lượng phiếu xuất kho trong ngày phải lớn hơn 0", sl > 0);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Không mong đợi exception: " + e.getMessage());
        } finally {
            try {
                con.rollback(); // không giữ lại dữ liệu test
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test case: Ngày hiện tại không có phiếu xuất kho.
     */
    @Test
    public void testGetSLPXK_todayNoRecords() {
        try {
            con.setAutoCommit(false);

            // Đảm bảo xóa hết phiếu xuất kho trong ngày hôm nay
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String today = sdf.format(new Date());

            String deleteSQL = "DELETE FROM PhieuXK WHERE NgayXK = TO_DATE(?, 'dd-mm-yyyy')";
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            ps.setString(1, today);
            ps.executeUpdate();
            ps.close();

            int sl = ss.getSLPXK();
            Assert.assertEquals("Không có phiếu xuất kho nào trong ngày", 0, sl);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Không mong đợi exception: " + e.getMessage());
        } finally {
            try {
                con.rollback(); // restore lại dữ liệu
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
