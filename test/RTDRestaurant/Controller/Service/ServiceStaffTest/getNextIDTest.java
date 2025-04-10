package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class getNextIDTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    // TC1: Dữ liệu có sẵn trong bảng → nextID = MAX(ID_NL) + 1
    @Test
    public void testGetNextID_CoDuLieu() throws SQLException {
        try {
            con.setAutoCommit(false);
            
            // Thêm bản ghi mới để xác định MAX(ID_NL)
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO NguyenLieu(ID_NL, TenNL, Dongia, Donvitinh) VALUES (?, ?, ?, ?)"
            );
            stmt.setInt(1, 200); // cao hơn các ID bạn có (115)
            stmt.setString(2, "Bo my");
            stmt.setInt(3, 100000);
            stmt.setString(4, "kg");
            stmt.executeUpdate();

            int nextID = ss.getNextID_NL();
            Assert.assertEquals(201, nextID);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC2: Không có bản ghi nào → nextID = 1
    @Test
    public void testGetNextID_BangRong() throws SQLException {
        try {
            con.setAutoCommit(false);

            // Xoá toàn bộ dữ liệu
            PreparedStatement stmt = con.prepareStatement("DELETE FROM NguyenLieu");
            stmt.executeUpdate();

            int nextID = ss.getNextID_NL();
            Assert.assertEquals(1, nextID); // MAX = NULL → nextID = 1

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
