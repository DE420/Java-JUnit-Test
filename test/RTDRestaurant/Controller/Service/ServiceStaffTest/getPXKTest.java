package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelPXK;
import java.sql.Connection;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class getPXKTest {

    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    /**
     * Test case: ID tồn tại trong bảng PhieuXK (r.next() == true)
     */
    @Test
    public void testGetPXKByID_exist() {
        try {
            con.setAutoCommit(false);

            int idTest = 100; // tồn tại trong data mẫu
            ModelPXK pxk = ss.getPXKbyID(idTest);

            Assert.assertNotNull(pxk);
            Assert.assertEquals(100, pxk.getIdXK());
            Assert.assertEquals(102, pxk.getIdNV());
            Assert.assertEquals("10-01-2023", pxk.getNgayXK());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Không mong đợi exception: " + e.getMessage());
        } finally {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test case: ID không tồn tại trong bảng PhieuXK (r.next() == false)
     */
    @Test
    public void testGetPXKByID_notExist() {
        try {
            con.setAutoCommit(false);

            int idTest = 999; // không tồn tại trong data mẫu
            ModelPXK pxk = ss.getPXKbyID(idTest);

            Assert.assertNull(pxk);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Không mong đợi exception: " + e.getMessage());
        } finally {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
