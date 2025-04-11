/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelMessage;
import org.junit.Test;
import junit.framework.Assert;

import java.sql.Connection;

public class ServiceMailTest {

    private ServiceMail serviceMail;
    private Connection con;

    @org.junit.Before
    public void setUp() throws Exception {
        // Thiết lập kết nối cơ sở dữ liệu
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();

        // Khởi tạo đối tượng ServiceMail
        serviceMail = new ServiceMail();
    }

    /**
     * Test gửi email thành công.
     */
    @Test
    public void testSendMail_Success() {
        String toEmail = "abc@gmail.com";
        String code = "123456";
        try {
            con.setAutoCommit(false);
            // Set up môi trường kiểm thử
            ModelMessage result = serviceMail.sendMain(toEmail, code);

            // Kiểm tra kết quả
            Assert.assertNotNull(result);
            Assert.assertTrue(result.isSuccess());
            Assert.assertEquals("", result.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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
     * Test gửi email với email không hợp lệ.
     */
    @Test
    public void testSendMail_InvalidEmail() {
        String toEmail = "abc";
        String code = "123456";
        try {
            con.setAutoCommit(false);
            // Set up môi trường kiểm thử
            ModelMessage result = serviceMail.sendMain(toEmail, code);

            // Kiểm tra kết quả
            Assert.assertNotNull(result);
            Assert.assertFalse(result.isSuccess());
            Assert.assertEquals("Email không chính xác", result.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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