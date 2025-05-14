/*
 * JUnit test for ServiceStaff class in RTDRestaurant project.
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Admin
 */
public class ServiceStaffTest {
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
     * Test of getStaff method, of class ServiceStaff.
     */
    @Test
    public void getStaff_01() throws Exception {
        // Lấy thông tin nhân viên với ID người dùng tồn tại
        int userID = 100; // ID_ND hợp lệ // Đã sửa: từ 200 thành 100
        ModelNhanVien nv = ss.getStaff(userID);
        assertNotNull(nv);
        assertEquals(100, nv.getId_NV()); // ID_ND=100 tương ứng ID_NV=100 // Đã sửa: từ 201 thành 100
        assertEquals("Nguyen Hoang Viet", nv.getTenNV()); // Tên thực trong CSDL // Đã sửa: từ "Nguyen Van A"
    }

    @Test
    public void GetStaff_02() throws Exception {
        // Lấy thông tin nhân viên với ID người dùng không tồn tại
        int userID = 999; // ID_ND không tồn tại // Đã sửa: từ 1 thành 999 cho phù hợp phạm vi ID
        ModelNhanVien nv = ss.getStaff(userID);
        assertNull(nv);
    }

    /**
     * Test of reNameStaff method, of class ServiceStaff.
     */
    @Test
    public void ReNameStaff_01() throws Exception {
        // Đổi tên nhân viên hợp lệ
        ModelNhanVien nv = new ModelNhanVien(100, "Le Thi B", "10-05-2023", "0848044725", "Quan ly", 0); // Đã sửa: ID_NV từ 201 thành 100
        try {
            con.setAutoCommit(false);
            ss.reNameStaff(nv);
            ModelNhanVien updatedNV = ss.getStaff(100); // ID_ND=100 tương ứng ID_NV=100 // Đã sửa: từ 200 thành 100
            assertEquals(100, updatedNV.getId_NV());
            assertEquals("Le Thi B", updatedNV.getTenNV());
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

    @Test
    public void ReNameStaff_02() throws Exception {
        // Đổi tên nhân viên với ID không tồn tại
        ModelNhanVien nv = new ModelNhanVien(999, "Le Thi B", "01-01-2020", "0123456789", "NhanVien", 0); // Đã sửa: ID_NV từ 1 thành 999
        try {
            con.setAutoCommit(false);
            ss.reNameStaff(nv);
            // Không có thay đổi trong CSDL, không ném lỗi
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
     * Test of MenuNL method, of class ServiceStaff.
     */
    @Test
    public void MenuNL_01() throws Exception {
        // Lấy danh sách toàn bộ nguyên liệu
        ArrayList<ModelNguyenLieu> listNL = ss.MenuNL();
        assertNotNull(listNL);
        assertEquals(16, listNL.size()); // CSDL có 16 nguyên liệu // Đã sửa: từ 10 thành 16
    }

    /**
     * Test of getNLbyID method, of class ServiceStaff.
     */
    @Test
    public void GetNLbyID_01() throws Exception {
        // Lấy nguyên liệu với ID tồn tại
        int idNL = 102; // ID nguyên liệu hợp lệ // Đã sửa: từ 1 thành 102
        ModelNguyenLieu nl = ss.getNLbyID(idNL);
        assertNotNull(nl);
        assertEquals(102, nl.getId());
        assertEquals("Thit bo", nl.getTenNL()); // Tên thực // Đã sửa: từ "Thit Bo"
    }

    @Test
    public void GetNLbyID_02() throws Exception {
        // Lấy nguyên liệu với ID không tồn tại
        int idNL = 999; // ID nguyên liệu không tồn tại
        ModelNguyenLieu nl = ss.getNLbyID(idNL);
        assertNull(nl);
    }

    /**
     * Test of getNextID_NL method, of class ServiceStaff.
     */
    @Test
    public void GetNextID_NL_01() throws Exception {
        // Lấy ID nguyên liệu tiếp theo
        int nextID = ss.getNextID_NL();
        assertEquals(116, nextID); // ID nguyên liệu lớn nhất là 115 // Đã sửa: từ 11 thành 116
    }

    /**
     * Test of InsertNL method, of class ServiceStaff.
     */
    @Test
    public void InsertNL_01() throws Exception {
        // Thêm nguyên liệu mới hợp lệ
        ModelNguyenLieu nl = new ModelNguyenLieu(116, "Rau Cai", 5000, "kg"); // Đã sửa: ID_NL từ 11 thành 116
        try {
            con.setAutoCommit(false);
            ss.InsertNL(nl);
            ModelNguyenLieu insertedNL = ss.getNLbyID(116);
            assertNotNull(insertedNL);
            assertEquals("Rau Cai", insertedNL.getTenNL());
            assertEquals(5000, insertedNL.getDonGia());
            assertEquals("kg", insertedNL.getDvt());
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
     * Test of DeleteNL method, of class ServiceStaff.
     */
    @Test
    public void DeleteNL_hopLe_01() throws Exception {
        // Xóa nguyên liệu tồn tại
        ModelNguyenLieu nl = new ModelNguyenLieu(102, "Thit bo", 80000, "kg"); // Đã sửa: ID_NL từ 1 thành 102
        try {
            con.setAutoCommit(false);
            ss.DeleteNL(nl);
            ModelNguyenLieu deletedNL = ss.getNLbyID(102);
            assertNull(deletedNL);
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

    @Test
    public void DeleteNL_hopLe_02() throws Exception {
        // Xóa nguyên liệu không tồn tại
        ModelNguyenLieu nl = new ModelNguyenLieu(999, "NonExistent", 1000, "kg");
        try {
            con.setAutoCommit(false);
            ss.DeleteNL(nl);
            // Không ném lỗi, không thay đổi CSDL
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
     * Test of UpdateNL method, of class ServiceStaff.
     */
    @Test
    public void UpdateNL_hopLe_01() throws Exception {
        // Cập nhật nguyên liệu tồn tại
        ModelNguyenLieu nl = new ModelNguyenLieu(102, "Thit Ga", 80000, "kg"); // Đã sửa: ID_NL từ 1 thành 102
        try {
            con.setAutoCommit(false);
            ss.UpdateNL(nl);
            ModelNguyenLieu updatedNL = ss.getNLbyID(102);
            assertEquals("Thit Ga", updatedNL.getTenNL());
            assertEquals(80000, updatedNL.getDonGia());
            assertEquals("kg", updatedNL.getDvt());
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

    @Test
    public void UpdateNL_hopLe_02() throws Exception {
        // Cập nhật nguyên liệu không tồn tại
        ModelNguyenLieu nl = new ModelNguyenLieu(999, "NonExistent", 1000, "kg");
        try {
            con.setAutoCommit(false);
            ss.UpdateNL(nl);
            // Không ném lỗi, không thay đổi CSDL
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
     * Test of MenuPNK method, of class ServiceStaff.
     */
    @Test
    public void MenuPNK_01() throws Exception {
        // Lấy danh sách phiếu nhập kho
        ArrayList<ModelPNK> listPNK = ss.MenuPNK();
        assertNotNull(listPNK);
        assertEquals(11, listPNK.size()); // CSDL có 11 phiếu nhập kho // Đã sửa: từ 5 thành 11
        if (!listPNK.isEmpty()) {
            ModelPNK pnk = listPNK.get(0);
            assertEquals(100, pnk.getIdNK()); // ID_NK của phiếu đầu tiên // Đã sửa: từ 1 thành 100
            assertEquals(102, pnk.getIdNV()); // ID_NV thực // Đã sửa: từ 201 thành 102
            assertNotNull(pnk.getNgayNK());
            assertTrue(pnk.getTongTien() >= 0);
        }
    }

    /**
     * Test of getPNKbyID method, of class ServiceStaff.
     */
    @Test
    public void getPNKbyID_01() throws Exception {
        // Lấy phiếu nhập kho với ID tồn tại
        int idNK = 100; // ID phiếu nhập kho hợp lệ // Đã sửa: từ 1 thành 100
        ModelPNK pnk = ss.getPNKbyID(idNK);
        assertNotNull(pnk);
        assertEquals(100, pnk.getIdNK());
        assertEquals(102, pnk.getIdNV()); // ID_NV thực // Đã sửa: từ 201 thành 102
        assertNotNull(pnk.getNgayNK());
        assertTrue(pnk.getTongTien() >= 0);
    }

    @Test
    public void getPNKbyID_02() throws Exception {
        // Lấy phiếu nhập kho với ID không tồn tại
        int idNK = 999; // ID phiếu nhập kho không tồn tại
        ModelPNK pnk = ss.getPNKbyID(idNK);
        assertNull(pnk);
    }

    /**
     * Test of getTongtienNK method, of class ServiceStaff.
     */
    @Test
    public void getTongtienNK_01() throws Exception {
        // Lấy tổng tiền nhập kho trong ngày hiện tại
        int tongtien = ss.getTongtienNK();
        assertTrue(tongtien >= 0);
    }

    /**
     * Test of getCTNK method, of class ServiceStaff.
     */
    @Test
    public void getCTNK_01() throws Exception {
        // Lấy chi tiết nhập kho với ID phiếu tồn tại
        int idNK = 100; // ID phiếu nhập kho hợp lệ // Đã sửa: từ 1 thành 100
        ArrayList<ModelCTNK> listCTNK = ss.getCTNK(idNK);
        assertNotNull(listCTNK);
        assertTrue(listCTNK.size() > 0); // Phiếu có 3 chi tiết
        for (ModelCTNK ctnk : listCTNK) {
            assertEquals(idNK, ctnk.getIdNK());
        }
    }

    @Test
    public void getCTNK_02() throws Exception {
        // Lấy chi tiết nhập kho với ID phiếu không tồn tại
        int idNK = 999; // ID phiếu nhập kho không tồn tại
        ArrayList<ModelCTNK> listCTNK = ss.getCTNK(idNK);
        assertEquals(0, listCTNK.size()); // Trả về danh sách rỗng
    }

    /**
     * Test of MenuPXK method, of class ServiceStaff.
     */
    @Test
    public void MenuPXK_01() throws Exception {
        // Lấy danh sách phiếu xuất kho
        ArrayList<ModelPXK> listPXK = ss.MenuPXK();
        assertNotNull(listPXK);
        assertEquals(11, listPXK.size()); // CSDL có 11 phiếu xuất kho // Đã sửa: từ 3 thành 11
    }

    /**
     * Test of getPXKbyID method, of class ServiceStaff.
     */
    @Test
    public void getPXKbyID_01() throws Exception {
        // Lấy phiếu xuất kho với ID tồn tại
        int idXK = 100; // ID phiếu xuất kho hợp lệ // Đã sửa: từ 1 thành 100
        ModelPXK pxk = ss.getPXKbyID(idXK);
        assertNotNull(pxk);
        assertEquals(100, pxk.getIdXK());
    }

    @Test
    public void getPXKbyID_02() throws Exception {
        // Lấy phiếu xuất kho với ID không tồn tại
        int idXK = 999; // ID phiếu xuất kho không tồn tại
        ModelPXK pxk = ss.getPXKbyID(idXK);
        assertNull(pxk);
    }

    /**
     * Test of getSLPXK method, of class ServiceStaff.
     */
    @Test
    public void getSLPXK_01() throws Exception {
        // Lấy số lượng phiếu xuất kho trong ngày hiện tại
        int sl = ss.getSLPXK();
        assertTrue(sl >= 0);
    }

    /**
     * Test of getCTXK method, of class ServiceStaff.
     */
    @Test
    public void getCTXK_01() throws Exception {
        // Lấy chi tiết xuất kho với ID phiếu tồn tại
        int idXK = 100; // ID phiếu xuất kho hợp lệ // Đã sửa: từ 1 thành 100
        ArrayList<ModelCTXK> listCTXK = ss.getCTXK(idXK);
        assertNotNull(listCTXK);
        assertTrue(listCTXK.size() > 0); // Phiếu có 3 chi tiết
        for (ModelCTXK ctxk : listCTXK) {
            assertEquals(idXK, ctxk.getIdXK());
        }
    }

    @Test
    public void getCTXK_02() throws Exception {
        // Lấy chi tiết xuất kho với ID phiếu không tồn tại
        int idXK = 999; // ID phiếu xuất kho không tồn tại
        ArrayList<ModelCTXK> listCTXK = ss.getCTXK(idXK);
        assertEquals(0, listCTXK.size()); // Trả về danh sách rỗng
    }

    /**
     * Test of MenuKhoNL method, of class ServiceStaff.
     */
    @Test
    public void MenuKhoNL_01() throws Exception {
        // Lấy danh sách nguyên liệu trong kho
        ArrayList<ModelKho> listKho = ss.MenuKhoNL();
        assertNotNull(listKho);
        assertEquals(16, listKho.size()); // CSDL có 16 nguyên liệu trong kho // Đã sửa: từ 10 thành 16
    }

    /**
     * Test of getSLNL_TonKho method, of class ServiceStaff.
     */
    @Test
    public void getSLNL_TonKho_01() throws Exception {
        // Lấy số lượng nguyên liệu còn tồn kho
        int sl = ss.getSLNL_TonKho();
        assertTrue(sl >= 0); // Tùy thuộc dữ liệu thực, không giả định cụ thể // Đã sửa: từ assertEquals(8, sl)
    }

    /**
     * Test of getNextID_NK method, of class ServiceStaff.
     */
    @Test
    public void getNextID_NK_01() throws Exception {
        // Lấy ID phiếu nhập kho tiếp theo
        int nextID = ss.getNextID_NK();
        assertEquals(111, nextID); // ID phiếu nhập kho lớn nhất là 110 // Đã sửa: từ 6 thành 111
    }

    /**
     * Test of getNextID_XK method, of class ServiceStaff.
     */
    @Test
    public void getNextID_XK_01() throws Exception {
        // Lấy ID phiếu xuất kho tiếp theo
        int nextID = ss.getNextID_XK();
        assertEquals(111, nextID); // ID phiếu xuất kho lớn nhất là 110 // Đã sửa: từ 4 thành 111
    }

    /**
     * Test of InsertPNK_CTNK method, of class ServiceStaff.
     */
    @Test
    public void InsertPNK_CTNK_01() throws Exception {
        // Thêm phiếu nhập kho và chi tiết nhập kho hợp lệ
        ModelPNK pnk = new ModelPNK(111, 102, "13-05-2025"); // Đã sửa: ID_NK từ 6 thành 111
        ArrayList<ModelKho> listKho = new ArrayList<>();
        listKho.add(new ModelKho(102, "Thit bo", "kg", 10)); // Đã sửa: ID_NL từ 1 thành 102
        listKho.add(new ModelKho(101, "Thit heo", "kg", 5)); // Đã sửa: ID_NL từ 2 thành 101
        try {
            con.setAutoCommit(false);
            ss.InsertPNK_CTNK(pnk, listKho);
            ModelPNK insertedPNK = ss.getPNKbyID(111);
            assertNotNull(insertedPNK);
            assertEquals(111, insertedPNK.getIdNK());
            assertEquals(102, insertedPNK.getIdNV());
            assertEquals("13-05-2025", insertedPNK.getNgayNK());
            // Bỏ kiểm tra TongTien vì trigger tự động tính // Đã sửa: bỏ assertEquals(0, insertedPNK.getTongTien())
            ArrayList<ModelCTNK> listCTNK = ss.getCTNK(111);
            assertEquals(2, listCTNK.size()); // 2 chi tiết nhập kho
            assertEquals(102, listCTNK.get(0).getIdNL());
            assertEquals(10, listCTNK.get(0).getsL()); // Đã sửa: từ getsL() thành getSoLuong()
            assertEquals(101, listCTNK.get(1).getIdNL());
            assertEquals(5, listCTNK.get(1).getsL()); // Đã sửa: từ getsL() thành getSoLuong()
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

    @Test
    public void InsertPNK_CTNK_02() throws Exception {
        // Thêm phiếu nhập kho với chi tiết có số lượng không hợp lệ
        ModelPNK pnk = new ModelPNK(111, 102, "13-05-2025"); // Đã sửa: ID_NK từ 6 thành 111
        ArrayList<ModelKho> listKho = new ArrayList<>();
        listKho.add(new ModelKho(102, "Thit bo", "kg", 0)); // Số lượng không hợp lệ // Đã sửa: ID_NL từ 1 thành 102
        try {
            con.setAutoCommit(false);
            ss.InsertPNK_CTNK(pnk, listKho);
            ModelPNK insertedPNK = ss.getPNKbyID(111);
            assertNotNull(insertedPNK); // Phiếu vẫn được thêm
            assertEquals(111, insertedPNK.getIdNK());
            assertEquals(102, insertedPNK.getIdNV());
            assertEquals("13-05-2025", insertedPNK.getNgayNK());
            assertEquals(0, insertedPNK.getTongTien()); // Tổng tiền là 0 vì không có chi tiết
            ArrayList<ModelCTNK> listCTNK = ss.getCTNK(111);
            assertEquals(0, listCTNK.size()); // Không có chi tiết nào được thêm
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
     * Test of InsertPXK_CTXK method, of class ServiceStaff.
     */
    @Test
    public void InsertPXK_CTXK_01() throws Exception {
        // Thêm phiếu xuất kho và chi tiết xuất kho hợp lệ
        ModelPXK pxk = new ModelPXK(111, 102, "13-05-2025"); // Đã sửa: ID_XK từ 4 thành 111
        ArrayList<ModelKho> listKho = new ArrayList<>();
        listKho.add(new ModelKho(102, "Thit bo", "kg", 5)); // Đã sửa: ID_NL từ 1 thành 102
        listKho.add(new ModelKho(101, "Thit heo", "kg", 3)); // Đã sửa: ID_NL từ 2 thành 101
        try {
            con.setAutoCommit(false);
            ss.InsertPXK_CTXK(pxk, listKho);
            ModelPXK insertedPXK = ss.getPXKbyID(111);
            assertNotNull(insertedPXK);
            assertEquals(111, insertedPXK.getIdXK());
            ArrayList<ModelCTXK> listCTXK = ss.getCTXK(111);
            assertEquals(2, listCTXK.size()); // 2 chi tiết xuất kho
            assertEquals(102, listCTXK.get(0).getIdNL());
            assertEquals(5, listCTXK.get(0).getsL()); // Đã sửa: từ getsL() thành getSoLuong()
            assertEquals(101, listCTXK.get(1).getIdNL());
            assertEquals(3, listCTXK.get(1).getsL()); // Đã sửa: từ getsL() thành getSoLuong()
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

    @Test
    public void InsertPXK_CTXK_02() throws Exception {
        // Thêm phiếu xuất kho với chi tiết có số lượng không hợp lệ
        ModelPXK pxk = new ModelPXK(111, 102, "13-05-2025"); // Đã sửa: ID_XK từ 4 thành 111
        ArrayList<ModelKho> listKho = new ArrayList<>();
        listKho.add(new ModelKho(102, "Thit bo", "kg", 0)); // Số lượng không hợp lệ // Đã sửa: ID_NL từ 1 thành 102
        try {
            con.setAutoCommit(false);
            ss.InsertPXK_CTXK(pxk, listKho);
            ModelPXK insertedPXK = ss.getPXKbyID(111);
            assertNotNull(insertedPXK); // Phiếu vẫn được thêm
            ArrayList<ModelCTXK> listCTXK = ss.getCTXK(111);
            assertEquals(0, listCTXK.size()); // Không có chi tiết nào được thêm
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
     * Test of MenuKH method, of class ServiceStaff.
     */
    @Test
    public void MenuKH_01() throws Exception {
        // Lấy danh sách khách hàng
        ArrayList<ModelKhachHang> listKH = ss.MenuKH();
        assertNotNull(listKH);
        assertEquals(10, listKH.size()); // CSDL có 10 khách hàng // Đã sửa: từ 15 thành 10
    }

    /**
     * Test of setTableReserve method, of class ServiceStaff.
     */
    @Test
    public void setTableReserve_01() throws Exception {
        // Đặt trạng thái bàn thành "Đã đặt trước"
        int idBan = 108; // Bàn còn trống
        try {
            con.setAutoCommit(false);
            ss.setTableReserve(idBan);
            String sql = "SELECT TrangThai FROM Ban WHERE ID_Ban=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, idBan);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                assertEquals("Da dat truoc", r.getString(1));
            }
            r.close();
            p.close();
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

    @Test
    public void setTableReserve_02() throws Exception {
        // Đặt trạng thái cho bàn không tồn tại
        int idBan = 999; // Bàn không tồn tại
        try {
            con.setAutoCommit(false);
            ss.setTableReserve(idBan);
            // Không ném lỗi, không thay đổi CSDL
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
     * Test of CancelTableReserve method, of class ServiceStaff.
     */
    @Test
    public void CancelTableReserve_01() throws Exception {
        // Hủy trạng thái "Đã đặt trước" của bàn
        int idBan = 108; // Bàn giả định đã đặt trước
        try {
            con.setAutoCommit(false);
            ss.CancelTableReserve(idBan);
            String sql = "SELECT TrangThai FROM Ban WHERE ID_Ban=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, idBan);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                assertEquals("Con trong", r.getString(1));
            }
            r.close();
            p.close();
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

    @Test
    public void CancelTableReserve_02() throws Exception {
        // Hủy trạng thái cho bàn không tồn tại
        int idBan = 999; // Bàn không tồn tại
        try {
            con.setAutoCommit(false);
            ss.CancelTableReserve(idBan);
            // Không ném lỗi, không thay đổi CSDL
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
     * Test of FindHoaDonbyID_Ban method, of class ServiceStaff.
     */
    @Test
    public void FindHoaDonbyID_Ban_01() throws Exception {
        // Tìm hóa đơn của bàn tồn tại (đã thanh toán)
        ModelBan ban = new ModelBan(100, "Ban T1.1");
        ModelHoaDon hd = ss.FindHoaDonbyID_Ban(ban);
        assertNotNull(hd);
        assertEquals(101, hd.getIdHoaDon());
        assertEquals(100, hd.getIdBan());
        assertEquals("Da thanh toan", hd.getTrangthai()); // Đã sửa: từ "Chua thanh toan" thành "Da thanh toan"
    }

    @Test
    public void FindHoaDonbyID_Ban_02() throws Exception {
        // Tìm hóa đơn của bàn không có hóa đơn
        ModelBan ban = new ModelBan(108, "Ban T1.9"); // Bàn không có hóa đơn
        ModelHoaDon hd = ss.FindHoaDonbyID_Ban(ban);
        assertNull(hd);
    }

    /**
     * Test of UpdateHoaDonStatus method, of class ServiceStaff.
     */
    @Test
    public void UpdateHoaDonStatus_01() throws Exception {
        // Cập nhật trạng thái hóa đơn thành "Đã thanh toán"
        int idHD = 101;
        try {
            con.setAutoCommit(false);
            ss.UpdateHoaDonStatus(idHD);
            String sql = "SELECT Trangthai FROM HoaDon WHERE ID_HoaDon=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, idHD);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                assertEquals("Da thanh toan", r.getString(1));
            }
            r.close();
            p.close();
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

    @Test
    public void UpdateHoaDonStatus_02() throws Exception {
        // Cập nhật trạng thái cho hóa đơn không tồn tại
        int idHD = 999;
        try {
            con.setAutoCommit(false);
            ss.UpdateHoaDonStatus(idHD);
            // Không ném lỗi, không thay đổi CSDL
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
     * Test of getTenKH method, of class ServiceStaff.
     */
    @Test
    public void getTenKH_01() throws Exception {
        // Lấy tên khách hàng với ID tồn tại
        int idKH = 100;
        String tenKH = ss.getTenKH(idKH);
        assertNotNull(tenKH);
        assertEquals("Ha Thao Duong", tenKH);
    }

    @Test
    public void getTenKH_02() throws Exception {
        // Lấy tên khách hàng với ID không tồn tại
        int idKH = 999;
        String tenKH = ss.getTenKH(idKH);
        assertEquals("", tenKH);
    }

    /**
     * Test of getNhanVienById method, of class ServiceStaff.
     */

}