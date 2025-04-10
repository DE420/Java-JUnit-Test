package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelPNK;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuPNKTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    // TC1: Danh sách có dữ liệu
    @Test
    public void testMenuPNK_CoDuLieu() throws Exception {
        try {
            con.setAutoCommit(false);

            // Chèn dữ liệu giả
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO PhieuNK(ID_NK, ID_NV, NgayNK, Tongtien) VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'), ?)"
            );
            stmt.setInt(1, 200);
            stmt.setInt(2, 102);
            stmt.setString(3, "01-04-2025");
            stmt.setInt(4, 1000000);
            stmt.executeUpdate();

            ArrayList<ModelPNK> list = ss.MenuPNK();
            Assert.assertTrue(list.size() > 0);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC2: Danh sách rỗng
    @Test
    public void testMenuPNK_Rong() throws Exception {
        try {
            con.setAutoCommit(false);

            // Xóa sạch bảng PhieuNK
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM PhieuNK");

            ArrayList<ModelPNK> list = ss.MenuPNK();
            Assert.assertEquals(0, list.size());

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC3: Dữ liệu có ngày tháng không hợp lệ (bị lỗi SQL)
    @Test
    public void testMenuPNK_NgayKhongHopLe() throws Exception {
        try {
            con.setAutoCommit(false);

            boolean exceptionThrown = false;
            try {
                // Chèn ngày lỗi (ví dụ: ngày không tồn tại)
                PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO PhieuNK(ID_NK, ID_NV, NgayNK, Tongtien) VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'), ?)"
                );
                stmt.setInt(1, 201);
                stmt.setInt(2, 102);
                stmt.setString(3, "32-13-2025"); // Ngày không hợp lệ
                stmt.setInt(4, 999999);
                stmt.executeUpdate();
            } catch (Exception e) {
                exceptionThrown = true;
            }

            Assert.assertTrue(exceptionThrown);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
