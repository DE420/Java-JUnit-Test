package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelNguyenLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MenuNLTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connectToDatabase();
        con = db.getConnection();
        ss = new ServiceStaff();
    }

    // TC1: Có ít nhất 1 nguyên liệu
    @Test
    public void testMenuNL_DanhSachCoDuLieu() throws SQLException {
        ArrayList<ModelNguyenLieu> list = ss.MenuNL();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0); // Có dữ liệu
    }

    // TC2: Danh sách rỗng
    @Test
    public void testMenuNL_DanhSachRong() throws SQLException {
        try {
            con.setAutoCommit(false);
            con.prepareStatement("DELETE FROM NguyenLieu").executeUpdate();
            ArrayList<ModelNguyenLieu> list = ss.MenuNL();
            Assert.assertNotNull(list);
            Assert.assertEquals(0, list.size()); // Không có dữ liệu
        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC3: Tên nguyên liệu rỗng hoặc chứa ký tự đặc biệt
    @Test
    public void testMenuNL_TenRong_HoacKyTuDacBiet() throws SQLException {
        try {
            con.setAutoCommit(false);
            con.prepareStatement("INSERT INTO NguyenLieu VALUES (999, '', 12345, 'kg')").executeUpdate();
            con.prepareStatement("INSERT INTO NguyenLieu VALUES (998, '@#$!', 54321, 'kg')").executeUpdate();
            ArrayList<ModelNguyenLieu> list = ss.MenuNL();
            boolean found = list.stream().anyMatch(x -> x.getId() == 999 || x.getId() == 998);
            Assert.assertTrue(found); // Dữ liệu có thể đọc được tên đặc biệt
        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC4: Đơn vị không hợp lệ (không nằm trong danh sách chuẩn ví dụ 'abc')
    @Test
    public void testMenuNL_DonViKhongHopLe() throws SQLException {
        try {
            con.setAutoCommit(false);
            con.prepareStatement("INSERT INTO NguyenLieu VALUES (997, 'La la la', 12345, 'abc')").executeUpdate();
            ArrayList<ModelNguyenLieu> list = ss.MenuNL();
            boolean found = list.stream().anyMatch(x -> x.getId() == 997 && x.getDvt().equals("abc"));
            Assert.assertTrue(found); // Dữ liệu đơn vị không hợp lệ vẫn được đọc
        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
