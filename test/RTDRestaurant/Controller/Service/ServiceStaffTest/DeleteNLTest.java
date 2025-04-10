package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelNguyenLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeleteNLTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    // TC1: Xoá nguyên liệu hợp lệ
    @Test
    public void testDeleteNL_HopLe() throws Exception {
        try {
            con.setAutoCommit(false);

            // Thêm nguyên liệu tạm
            ModelNguyenLieu nl = new ModelNguyenLieu(210, "TestMeo", 50000, "kg");
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO NguyenLieu(ID_NL, TenNL, Dongia, Donvitinh) VALUES (?, ?, ?, ?)"
            );
            stmt.setInt(1, nl.getId());
            stmt.setString(2, nl.getTenNL());
            stmt.setInt(3, nl.getDonGia());
            stmt.setString(4, nl.getDvt());
            stmt.executeUpdate();

            // Kiểm tra số lượng trước khi xóa
            int sizeBefore = ss.MenuNL().size();

            // Gọi hàm xoá
            ss.DeleteNL(nl);

            // Kiểm tra số lượng sau khi xóa
            int sizeAfter = ss.MenuNL().size();

            Assert.assertEquals(sizeBefore - 1, sizeAfter);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC2: Xoá nguyên liệu không tồn tại
    @Test
    public void testDeleteNL_KhongTonTai() throws Exception {
        try {
            con.setAutoCommit(false);

            // Kiểm tra số lượng ban đầu
            int sizeBefore = ss.MenuNL().size();

            // Gọi hàm xoá với ID không tồn tại
            ModelNguyenLieu fake = new ModelNguyenLieu(9999, "Khong co", 99999, "xxx");
            ss.DeleteNL(fake);

            // Kiểm tra số lượng sau khi xóa (không thay đổi)
            int sizeAfter = ss.MenuNL().size();

            Assert.assertEquals(sizeBefore, sizeAfter);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // TC3: Xoá khi truyền null
    @Test(expected = NullPointerException.class)
    public void testDeleteNL_NullInput() throws Exception {
        try {
            con.setAutoCommit(false);

            // Truyền null
            ss.DeleteNL(null);

        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }
}
