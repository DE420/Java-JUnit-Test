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
    public void login_1() {
        // Đăng nhập với thông tin hợp lệ và tài khoản đã được xác minh
        ModelLogin login = new ModelLogin();
        login.setEmail("NVHoangViet@gmail.com");
        login.setPassword("123");
        try {
            con.setAutoCommit(false);
            ModelNguoiDung user = su.login(login); // gọi hàm login
            // Kiểm tra user không null (đăng nhập thành công)
            assertNotNull(user);
            // Kiểm tra email và password
            assertEquals("NVHoangViet@gmail.com", user.getEmail());
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
    
    @Test
    public void login_2() {
        // Đăng nhập với mật khẩu sai
        ModelLogin login = new ModelLogin();
        login.setEmail("NVHoangViet@gmail.com");
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
    
    @Test
    public void login_3() {
        // Đăng nhập với email sai
        ModelLogin login = new ModelLogin();
        login.setEmail("NVHoang@gmail.com");
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
    
    @Test
    public void login_4() {
        // Đăng nhập với email trống
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
    
    @Test
    public void login_5() {
        // Đăng nhập với password rỗng
        ModelLogin login = new ModelLogin();
        login.setEmail("NVHoangViet@gmail.com");
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
    
    @Test
    public void login_6() {
        // Đăng nhập với cả email và password đều trống
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
    
    @Test
    public void login_7() {
        // Đăng nhập với email có khoảng trắng
        ModelLogin login = new ModelLogin();
        login.setEmail("     NVHoangViet@gmail.com    ");
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
    
    @Test
    public void login_8() {
        // Đăng nhập với mật khẩu có khoảng trắng
        ModelLogin login = new ModelLogin();
        login.setEmail("NVHoangViet@gmail.com");
        login.setPassword("    123    ");
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
    
    @Test
    public void login_9() {
        // Đăng nhập với email null
        ModelLogin login = new ModelLogin();
        login.setEmail(null);
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
    
    @Test
    public void login_10() {
        // Đăng nhập với mật khẩu null
        ModelLogin login = new ModelLogin();
        login.setEmail("NVHoangViet@gmail.com");
        login.setPassword(null);
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
    
    @Test
    public void login_11() {
        // Đăng nhập với cả email và mật khẩu null
        ModelLogin login = new ModelLogin();
        login.setEmail(null);
        login.setPassword(null);
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
    
    @Test
    public void login_12() {
        // Kiểm thử SQL Injection
        ModelLogin login = new ModelLogin();
        login.setEmail("' OR '1'='1");
        login.setPassword("' OR '1'='1" );
        
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
    @Test
    public void insertUser_1() {
        // Thêm người dùng mới thành công
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("KHThanh@gmail.com");
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
            assertEquals("KHThanh@gmail.com", rs.getString("Email"));
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
    
    @Test
    public void insertUser_2() {
        // Thêm người dùng với email đã tồn tại trong hệ thống
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("KHPhucNguyen@gmail.com");
        user.setPassword("123");
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            String sql = "SELECT Email, COUNT(*) FROM NguoiDung GROUP BY Email HAVING COUNT(*) > 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                int count = rs.getInt(2);
                System.out.println("Email trùng: " + email + " - Số lần: " + count);
                assertEquals(1, count);
            }
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
    
    @Test
    public void insertUser_3() {
        // Thêm người dùng với email trống
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("");
        user.setPassword("123");
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_ND) as ID_ND FROM NguoiDung");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userID = rs.getInt("ID_ND");
            System.out.println(userID);
            assertNotEquals(userID, user.getUserID());
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
    
    @Test
    public void insertUser_4() {
        // Thêm người dùng với mật khẩu trống
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("KHThanh@gmail.com");
        user.setPassword("");
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_ND) as ID_ND FROM NguoiDung");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userID = rs.getInt("ID_ND");
            System.out.println(userID);
            assertNotEquals(userID, user.getUserID());
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
    
    @Test
    public void insertUser_5() {
        // Thêm người dùng với cả email và mật khẩu trống
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("");
        user.setPassword("");
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_ND) as ID_ND FROM NguoiDung");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userID = rs.getInt("ID_ND");
            System.out.println(userID);
            assertNotEquals(userID, user.getUserID());
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
    
    @Test
    public void insertUser_6() {
        // Thêm người dùng với email null
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail(null);
        user.setPassword("123");
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_ND) as ID_ND FROM NguoiDung");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userID = rs.getInt("ID_ND");
            System.out.println(userID);
            assertNotEquals(userID, user.getUserID());
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
    
    @Test
    public void insertUser_7() {
        // Thêm người dùng với mật khẩu null
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail("KHThanh@gmail.com");
        user.setPassword(null);
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_ND) as ID_ND FROM NguoiDung");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userID = rs.getInt("ID_ND");
            System.out.println(userID);
            assertNotEquals(userID, user.getUserID());
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
    
    @Test
    public void insertUser_8() {
        // Thêm người dùng với cả email và mật khẩu null
        ModelNguoiDung user = new ModelNguoiDung();
        user.setEmail(null);
        user.setPassword(null);
        try {
            con.setAutoCommit(false);
            su.insertUser(user);
            PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_ND) as ID_ND FROM NguoiDung");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userID = rs.getInt("ID_ND");
            System.out.println(userID);
            assertNotEquals(userID, user.getUserID());
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
     * Test of generateVerifiyCode method, of class ServiceUser.
     */
    @Test
    public void generateVerifiyCode_1() {
        // Sinh mã xác minh không trùng
        try {
            con.setAutoCommit(false);
            String code = su.generateVerifiyCode();
            assertNotEquals("", code);
            assertNotEquals(null, code);
            assertTrue( "Code phải là số nguyên dương", code.matches("\\d+"));
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
     * Test of checkDuplicateEmail method, of class ServiceUser.
     */
    @Test
    public void checkDuplicateEmail_1() {
        // check Email tồn tại và có Trangthai='Verified'
        String email = "KHPhucNguyen@gmail.com";
        try {
            con.setAutoCommit(false);
            boolean res = su.checkDuplicateEmail(email);
            assertTrue(res);
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
    
    @Test
    public void checkDuplicateEmail_2() {
        // check Email tồn tại nhưng Trangthai không phải 'Verified'
        String email = "KHThanh@gmail.com";
        try {
            con.setAutoCommit(false);
            boolean res = su.checkDuplicateEmail(email);
            assertFalse(res);
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
    
    @Test
    public void checkDuplicateEmail_3() {
        // check Email không tồn tại trong DB
        String email = "KHDuyThanh@gmail.com";
        try {
            con.setAutoCommit(false);
            boolean res = su.checkDuplicateEmail(email);
            assertFalse(res);
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
     * Test of doneVerify method, of class ServiceUser.
     */
    @Test
    public void doneVerify_1() {
        // Xác nhận với userID và name hợp lệ
        int userID = 114;
        String name = "Thanh";
        try {
            con.setAutoCommit(false);
            su.doneVerify(userID, name);
            
            // 1. Kiểm tra người dùng đã được cập nhật thành Verified
            PreparedStatement ps1 = con.prepareStatement("SELECT Trangthai, VerifyCode FROM NguoiDung WHERE ID_ND = ?");
            ps1.setInt(1, userID);
            ResultSet rs1 = ps1.executeQuery();
            assertTrue(rs1.next());
            assertEquals("Verified", rs1.getString("Trangthai"));
            assertEquals(null, rs1.getString("VerifyCode"));

            int id=0;
            String sql_ID="SELECT MAX(ID_KH) as ID FROM KhachHang";
            PreparedStatement p_id = con.prepareStatement(sql_ID);
            ResultSet r=p_id.executeQuery();
            if(r.next()){
                id=r.getInt("ID");
            }
            
            // 2. Kiểm tra KhachHang có được thêm vào không
            PreparedStatement ps2 = con.prepareStatement("SELECT TenKH FROM KhachHang WHERE ID_KH = ?");
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            assertTrue(rs2.next());
            assertEquals(name, rs2.getString("TenKH"));

            // Đóng tài nguyên
            rs1.close();
            r.close();
            rs2.close();
            ps1.close();
            p_id.close();
            ps2.close();
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
    
    @Test
    public void doneVerify_2() {
        // Xác nhận với userID đã được xác nhận
        int userID = 113;
        String name = "Thanh";
        try {
            con.setAutoCommit(false);
            su.doneVerify(userID, name);

            int id=0;
            String sql_ID="SELECT MAX(ID_KH) as ID FROM KhachHang";
            PreparedStatement p_id = con.prepareStatement(sql_ID);
            ResultSet r=p_id.executeQuery();
            if(r.next()){
                id=r.getInt("ID");
            }
            System.out.println(id);
            
            // 2. Kiểm tra KhachHang có được thêm vào không
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM KhachHang WHERE ID_KH = ? AND TenKH = ?");
            ps2.setInt(1, id);
            ps2.setString(2, name);
            ResultSet rs2 = ps2.executeQuery();
            assertFalse(rs2.next());
            
            // Đóng tài nguyên
            r.close();
            rs2.close();
            p_id.close();
            ps2.close();
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
    
    @Test
    public void doneVerify_3() {
        // Xác nhận với tên khách hàng để trống
        int userID = 114;
        String name = "";
        try {
            con.setAutoCommit(false);
            su.doneVerify(userID, name);
            
            // 1. Kiểm tra người dùng đã được cập nhật thành Verified
            PreparedStatement ps1 = con.prepareStatement("SELECT Trangthai, VerifyCode FROM NguoiDung WHERE ID_ND = ?");
            ps1.setInt(1, userID);
            ResultSet rs1 = ps1.executeQuery();
            assertTrue(rs1.next());
            assertEquals("Verified", rs1.getString("Trangthai"));
            assertEquals(null, rs1.getString("VerifyCode"));

            int id=0;
            String sql_ID="SELECT MAX(ID_ND) as ID FROM KhachHang";
            PreparedStatement p_id = con.prepareStatement(sql_ID);
            ResultSet r=p_id.executeQuery();
            if(r.next()){
                id=r.getInt("ID");
            }
            System.out.println(id);
            assertNotEquals(id, userID);

            // Đóng tài nguyên
            rs1.close();
            r.close();
            ps1.close();
            p_id.close();
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
     * Test of verifyCodeWithUser method, of class ServiceUser.
     */
    @Test
    public void verifyCodeWithUser_1() {
        // Kiểm tra khi có bản ghi đúng với userID và code
        int userID = 114;
        String code = "3032025";
        try {
            con.setAutoCommit(false);
            boolean res = su.verifyCodeWithUser(userID, code);
            assertTrue(res);
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
    
    @Test
    public void verifyCodeWithUser_2() {
        // Kiểm tra khi không có bản ghi khớp
        int userID = 114;
        String code = "203156";
        try {
            con.setAutoCommit(false);
            boolean res = su.verifyCodeWithUser(userID, code);
            assertFalse(res);
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
     * Test of changePassword method, of class ServiceUser.
     */
    @Test
    public void changePassword_1() {
        // Thay đổi mật khẩu với userID và mật khẩu mới hợp lệ
        int userID = 113;
        String newPass = "123456";
        try {
            con.setAutoCommit(false);
            su.changePassword(userID, newPass);
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM NguoiDung WHERE ID_ND = ?");
            ps2.setInt(1, userID);
            ResultSet rs2 = ps2.executeQuery();
            assertTrue(rs2.next());
            assertEquals(newPass, rs2.getString("MatKhau"));
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
    
    @Test
    public void changePassword_2() {
        // Đặt mật khẩu trống cho userID hợp lệ
        int userID = 113;
        String newPass = "";
        try {
            con.setAutoCommit(false);
            su.changePassword(userID, newPass);
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM NguoiDung WHERE ID_ND = ?");
            ps2.setInt(1, userID);
            ResultSet rs2 = ps2.executeQuery();
            assertTrue(rs2.next());
            assertNotEquals(rs2.getString("MatKhau"), null);
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
    
    @Test
    public void changePassword_3() {
        // Đặt mật khẩu null cho userID hợp lệ
        int userID = 113;
        String newPass = null;
        try {
            con.setAutoCommit(false);
            su.changePassword(userID, newPass);
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM NguoiDung WHERE ID_ND = ?");
            ps2.setInt(1, userID);
            ResultSet rs2 = ps2.executeQuery();
            assertTrue(rs2.next());
            assertNotEquals(rs2.getString("MatKhau"), null);
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
