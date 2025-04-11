/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelLogin;
import RTDRestaurant.Model.ModelNguoiDung;
import java.sql.Connection;
import org.junit.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author vuduy
 */
public class ServiceUserTest {
    private Connection con;
    private ServiceUser su;
    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        su = new ServiceUser();
    }
    
    /**
    * Test người dùng đăng nhập thành công.
    */
    @Test
    public void testLogin_ThanhCong() {
        // Người dùng đăng nhập thành công
        System.out.println("login_ThanhCong");
        ModelLogin login = new ModelLogin();
        login.setEmail("KHPhucNguyen@gmail.com");
        login.setPassword("123");

        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user không null (đăng nhập thành công)
            assertNotNull(user);
            // Kiểm tra email và password (tuỳ theo dữ liệu trong DB test)
            assertEquals("KHPhucNguyen@gmail.com", user.getEmail());
            assertEquals("123", user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    } 
    
    /**
    * Test người dùng đăng nhập với password sai
    */
    @Test
    public void testLogin_PasswordSai() {
        System.out.println("login_PasswordSai");
        // Người dùng đăng nhập không thành công
        ModelLogin login = new ModelLogin();
        login.setEmail("KHPhucNguyen@gmail.com");
        login.setPassword("123456");
            
        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user = null hay không (đăng nhập không thành công)
            assertEquals(user, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test người dùng đăng nhập với email sai
    */
    @Test
    public void testLogin_EmailSai() {
        System.out.println("login_EmailSai");
        // Người dùng đăng nhập không thành công
        ModelLogin login = new ModelLogin();
        login.setEmail("khphucnguyen@gmail.com");
        login.setPassword("123");
            
        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user = null hay không (đăng nhập không thành công)
            assertEquals(user, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test người dùng đăng nhập với email trống
    */
    @Test
    public void testLogin_EmailTrong() {
        System.out.println("login_EmailTrong");
        // Người dùng không nhập email
        ModelLogin login = new ModelLogin();
        login.setEmail("");
        login.setPassword("123");

        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user = null hay không (đăng nhập không thành công)
            assertEquals(user, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test người dùng đăng nhập với password trống
    */
    @Test
    public void testLogin_PasswordTrong() {
        System.out.println("login_PasswordTrong");
        // Người dùng không nhập email
        ModelLogin login = new ModelLogin();
        login.setEmail("KHPhucNguyen@gmail.com");
        login.setPassword("");

        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user = null hay không (đăng nhập không thành công)
            assertEquals(user, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test người dùng đăng nhập với password trống, email trống
    */
    @Test
    public void testLogin_EmailVaPasswordTrong() {
        System.out.println("login_EmailVaPasswordTrong");
        // Người dùng không nhập email
        ModelLogin login = new ModelLogin();
        login.setEmail("");
        login.setPassword("");
        
        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user = null hay không (đăng nhập không thành công)
            assertEquals(user, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
  
    
    /**
     * Test of insertUser method, of class ServiceUser.
     */
    
    /**
    * Test thêm người dùng mới thành công
    */
    @Test
    public void testInsertUser_ThanhCong() {
        System.out.println("InsertUser_ThanhCong");
        // thêm người dùng mới thành công
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("testUser@gmail.com");
        user.setPassword("123");
        
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            // Kiểm tra sau khi insert: userID và verifyCode không null
            assertTrue(user.getUserID() > 0);
            assertNotNull(user.getVerifyCode());
            assertEquals("Khách Hàng", user.getRole());
            
            // Truy vấn lại từ DB để kiểm tra dữ liệu
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NguoiDung WHERE ID_ND=?");
            ps.setInt(1, user.getUserID());
            ResultSet rs = ps.executeQuery();

            assertTrue(rs.next());

            assertEquals("testUser@gmail.com", rs.getString("Email"));
            assertEquals("123", rs.getString("MatKhau"));
            assertEquals(user.getVerifyCode(), rs.getString("VerifyCode"));

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test thêm người dùng mới nhưng trùng email
    */
    @Test
    public void testInsertUser_TrungEmail() {
        System.out.println("InsertUser_TrungEmail");
        // thêm người dùng mới thành công
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("KHPhucNguyen@gmail.com");
        user.setPassword("123456");
        
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            // Kiểm tra sau khi insert: userID và verifyCode không null
            assertEquals(user.getUserID(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test thêm người dùng mới  với email rỗng
    */
    @Test
    public void testInsertUser_EmailRong() {
        System.out.println("InsertUser_EmailRong");
        // thêm người dùng mới thành công
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("");
        user.setPassword("123456");
        
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            // Kiểm tra sau khi insert: userID và verifyCode không null
            assertEquals(user.getUserID(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /**
    * Test thêm người dùng mới với password rỗng
    */
    @Test
    public void testInsertUser_PasswordRong() {
        System.out.println("InsertUser_PasswordRong");
        // thêm người dùng mới thành công
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("testUser@gmail.com");
        user.setPassword("");
        
        try {
            con.setAutoCommit(false);
            // Kiểm tra sau khi insert: userID và verifyCode không null
            assertEquals(user.getUserID(), 0);
            assertNull(user.getVerifyCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
