/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Controller.Service.ServiceAdmin;
import RTDRestaurant.Model.ModelHoaDon;
import RTDRestaurant.Model.ModelNhanVien;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceAdminTest {

    private ServiceAdmin serviceAdmin;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        // Thiết lập kết nối cơ sở dữ liệu
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();

        // Khởi tạo đối tượng ServiceAdmin
        serviceAdmin = new ServiceAdmin();
    }

    /**
     * Test lấy danh sách nhân viên thành công.
     */
    @Test
    public void testGetListNV_Success() throws SQLException {        
        // Thực hiện lấy danh sách nhân viên
        ArrayList<ModelNhanVien> result = serviceAdmin.getListNV();

        // Kiểm tra kết quả
        Assert.assertNotNull(result);
        Assert.assertEquals(11, result.size()); //CSDL đang có 11 bản ghi nhân viên
    }
    
    /**
    * Test lấy danh sách hóa đơn thành công với tất cả các bản ghi.
    */
   @Test
   public void testGetListHDIn_All() throws SQLException {
       // Thực hiện lấy danh sách hóa đơn
       ArrayList<ModelHoaDon> result = serviceAdmin.getListHDIn("Tất cả");

       // Kiểm tra kết quả
       Assert.assertNotNull(result);
       Assert.assertTrue(result.size() > 0); // Đảm bảo danh sách không rỗng
   }

   /**
    * Test lấy danh sách hóa đơn thành công với hóa đơn trong ngày hôm nay.
    */
   @Test
   public void testGetListHDIn_Today() throws SQLException {
       // Thực hiện lấy danh sách hóa đơn
       ArrayList<ModelHoaDon> result = serviceAdmin.getListHDIn("Hôm nay");

       // Kiểm tra kết quả
       Assert.assertNotNull(result);
       // Kiểm tra logic nếu biết trước số bản ghi hoặc đảm bảo kết quả hợp lệ
   }

   /**
    * Test lấy danh sách hóa đơn thành công với hóa đơn trong tháng này.
    */
   @Test
   public void testGetListHDIn_ThisMonth() throws SQLException {
       // Thực hiện lấy danh sách hóa đơn
       ArrayList<ModelHoaDon> result = serviceAdmin.getListHDIn("Tháng này");

       // Kiểm tra kết quả
       Assert.assertNotNull(result);
       // Kiểm tra logic nếu biết trước số bản ghi hoặc đảm bảo kết quả hợp lệ
   }

   /**
    * Test lấy danh sách hóa đơn thành công với hóa đơn trong năm này.
    */
   @Test
   public void testGetListHDIn_ThisYear() throws SQLException {
       // Thực hiện lấy danh sách hóa đơn
       ArrayList<ModelHoaDon> result = serviceAdmin.getListHDIn("Năm này");

       // Kiểm tra kết quả
       Assert.assertNotNull(result);
       // Kiểm tra logic nếu biết trước số bản ghi hoặc đảm bảo kết quả hợp lệ
   }
    
     /**
     * Test tính tổng doanh thu hóa đơn thành công trong ngày hôm nay.
     */
    @Test
    public void testGetRevenueHD_Today() throws SQLException {
        // Thực hiện tính tổng doanh thu
        int revenue = serviceAdmin.getRevenueHD("Hôm nay");

        // Kiểm tra kết quả
        Assert.assertEquals(0, revenue);    //do CDSL lâu không có cập nhật nên doanh thu theo ngày = 0
    }

    /**
     * Test tính tổng doanh thu hóa đơn thành công trong tháng này.
     */
    @Test
    public void testGetRevenueHD_ThisMonth() throws SQLException {
        // Thực hiện tính tổng doanh thu
        int revenue = serviceAdmin.getRevenueHD("Tháng này");

        // Kiểm tra kết quả
        Assert.assertEquals(0, revenue);    //do CDSL lâu không có cập nhật nên doanh thu theo tháng = 0
    }

    /**
     * Test tính tổng doanh thu hóa đơn thành công trong năm này.
     */
    @Test
    public void testGetRevenueHD_ThisYear() throws SQLException {
        // Thực hiện tính tổng doanh thu
        int revenue = serviceAdmin.getRevenueHD("Năm này");

        // Kiểm tra kết quả
        Assert.assertEquals(0, revenue);    //do CDSL lâu không có cập nhật nên doanh thu theo năm = 0
    }
    
    /**
     * Test lấy thông tin nhân viên thành công.
     */
    @Test
    public void testGetNV_Success() throws SQLException {
        int validIdNV = 100; // ID_NV hợp lệ trong cơ sở dữ liệu

        // Thực hiện lấy thông tin nhân viên
        ModelNhanVien result = serviceAdmin.getNV(validIdNV);

        // Kiểm tra kết quả
        Assert.assertNotNull(result); // Đảm bảo dữ liệu không null
        Assert.assertEquals(validIdNV, result.getId_NV()); // ID_NV phải khớp
        Assert.assertEquals("Nguyen Hoang Viet", result.getTenNV()); // Kiểm tra đúng tên nhân viên trong CSDL
    }

    /**
     * Test lấy thông tin nhân viên với ID không tồn tại.
     */
    @Test
    public void testGetNV_InvalidId() throws SQLException {
        int invalidIdNV = -1; // ID_NV không tồn tại trong cơ sở dữ liệu

        // Thực hiện lấy thông tin nhân viên
        ModelNhanVien result = serviceAdmin.getNV(invalidIdNV);

        // Kiểm tra kết quả
        Assert.assertNull(result); // Kết quả phải null
    }

}