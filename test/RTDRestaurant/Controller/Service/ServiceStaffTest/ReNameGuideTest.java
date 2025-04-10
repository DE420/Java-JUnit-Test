/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Model.ModelBan;
import RTDRestaurant.Model.ModelCTNK;
import RTDRestaurant.Model.ModelCTXK;
import RTDRestaurant.Model.ModelHoaDon;
import RTDRestaurant.Model.ModelKhachHang;
import RTDRestaurant.Model.ModelKho;
import RTDRestaurant.Model.ModelNguyenLieu;
import RTDRestaurant.Model.ModelNhanVien;
import RTDRestaurant.Model.ModelPNK;
import RTDRestaurant.Model.ModelPXK;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manhc
 */
public class ReNameGuideTest {
    
    public ReNameGuideTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStaff method, of class ServiceStaff.
     */
    @Test
    public void testGetStaff() throws Exception {
        System.out.println("getStaff");
        int userID = 0;
        ServiceStaff instance = new ServiceStaff();
        ModelNhanVien expResult = null;
        ModelNhanVien result = instance.getStaff(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reNameStaff method, of class ServiceStaff.
     */
    @Test
    public void testReNameStaff() throws Exception {
        System.out.println("reNameStaff");
        ModelNhanVien data = null;
        ServiceStaff instance = new ServiceStaff();
        instance.reNameStaff(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MenuNL method, of class ServiceStaff.
     */
    @Test
    public void testMenuNL() throws Exception {
        System.out.println("MenuNL");
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelNguyenLieu> expResult = null;
        ArrayList<ModelNguyenLieu> result = instance.MenuNL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNLbyID method, of class ServiceStaff.
     */
    @Test
    public void testGetNLbyID() throws Exception {
        System.out.println("getNLbyID");
        int idNL = 0;
        ServiceStaff instance = new ServiceStaff();
        ModelNguyenLieu expResult = null;
        ModelNguyenLieu result = instance.getNLbyID(idNL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextID_NL method, of class ServiceStaff.
     */
    @Test
    public void testGetNextID_NL() throws Exception {
        System.out.println("getNextID_NL");
        ServiceStaff instance = new ServiceStaff();
        int expResult = 0;
        int result = instance.getNextID_NL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of InsertNL method, of class ServiceStaff.
     */
    @Test
    public void testInsertNL() throws Exception {
        System.out.println("InsertNL");
        ModelNguyenLieu data = null;
        ServiceStaff instance = new ServiceStaff();
        instance.InsertNL(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DeleteNL method, of class ServiceStaff.
     */
    @Test
    public void testDeleteNL() throws Exception {
        System.out.println("DeleteNL");
        ModelNguyenLieu data = null;
        ServiceStaff instance = new ServiceStaff();
        instance.DeleteNL(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdateNL method, of class ServiceStaff.
     */
    @Test
    public void testUpdateNL() throws Exception {
        System.out.println("UpdateNL");
        ModelNguyenLieu data = null;
        ServiceStaff instance = new ServiceStaff();
        instance.UpdateNL(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MenuPNK method, of class ServiceStaff.
     */
    @Test
    public void testMenuPNK() throws Exception {
        System.out.println("MenuPNK");
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelPNK> expResult = null;
        ArrayList<ModelPNK> result = instance.MenuPNK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPNKbyID method, of class ServiceStaff.
     */
    @Test
    public void testGetPNKbyID() throws Exception {
        System.out.println("getPNKbyID");
        int id = 0;
        ServiceStaff instance = new ServiceStaff();
        ModelPNK expResult = null;
        ModelPNK result = instance.getPNKbyID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTongtienNK method, of class ServiceStaff.
     */
    @Test
    public void testGetTongtienNK() throws Exception {
        System.out.println("getTongtienNK");
        ServiceStaff instance = new ServiceStaff();
        int expResult = 0;
        int result = instance.getTongtienNK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCTNK method, of class ServiceStaff.
     */
    @Test
    public void testGetCTNK() throws Exception {
        System.out.println("getCTNK");
        int idnk = 0;
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelCTNK> expResult = null;
        ArrayList<ModelCTNK> result = instance.getCTNK(idnk);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MenuPXK method, of class ServiceStaff.
     */
    @Test
    public void testMenuPXK() throws Exception {
        System.out.println("MenuPXK");
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelPXK> expResult = null;
        ArrayList<ModelPXK> result = instance.MenuPXK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPXKbyID method, of class ServiceStaff.
     */
    @Test
    public void testGetPXKbyID() throws Exception {
        System.out.println("getPXKbyID");
        int id = 0;
        ServiceStaff instance = new ServiceStaff();
        ModelPXK expResult = null;
        ModelPXK result = instance.getPXKbyID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSLPXK method, of class ServiceStaff.
     */
    @Test
    public void testGetSLPXK() throws Exception {
        System.out.println("getSLPXK");
        ServiceStaff instance = new ServiceStaff();
        int expResult = 0;
        int result = instance.getSLPXK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCTXK method, of class ServiceStaff.
     */
    @Test
    public void testGetCTXK() throws Exception {
        System.out.println("getCTXK");
        int idxk = 0;
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelCTXK> expResult = null;
        ArrayList<ModelCTXK> result = instance.getCTXK(idxk);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MenuKhoNL method, of class ServiceStaff.
     */
    @Test
    public void testMenuKhoNL() throws Exception {
        System.out.println("MenuKhoNL");
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelKho> expResult = null;
        ArrayList<ModelKho> result = instance.MenuKhoNL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSLNL_TonKho method, of class ServiceStaff.
     */
    @Test
    public void testGetSLNL_TonKho() throws Exception {
        System.out.println("getSLNL_TonKho");
        ServiceStaff instance = new ServiceStaff();
        int expResult = 0;
        int result = instance.getSLNL_TonKho();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextID_NK method, of class ServiceStaff.
     */
    @Test
    public void testGetNextID_NK() throws Exception {
        System.out.println("getNextID_NK");
        ServiceStaff instance = new ServiceStaff();
        int expResult = 0;
        int result = instance.getNextID_NK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextID_XK method, of class ServiceStaff.
     */
    @Test
    public void testGetNextID_XK() throws Exception {
        System.out.println("getNextID_XK");
        ServiceStaff instance = new ServiceStaff();
        int expResult = 0;
        int result = instance.getNextID_XK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of InsertPNK_CTNK method, of class ServiceStaff.
     */
    @Test
    public void testInsertPNK_CTNK() throws Exception {
        System.out.println("InsertPNK_CTNK");
        ModelPNK pnk = null;
        ArrayList<ModelKho> list = null;
        ServiceStaff instance = new ServiceStaff();
        instance.InsertPNK_CTNK(pnk, list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of InsertPXK_CTXK method, of class ServiceStaff.
     */
    @Test
    public void testInsertPXK_CTXK() throws Exception {
        System.out.println("InsertPXK_CTXK");
        ModelPXK pxk = null;
        ArrayList<ModelKho> list = null;
        ServiceStaff instance = new ServiceStaff();
        instance.InsertPXK_CTXK(pxk, list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MenuKH method, of class ServiceStaff.
     */
    @Test
    public void testMenuKH() throws Exception {
        System.out.println("MenuKH");
        ServiceStaff instance = new ServiceStaff();
        ArrayList<ModelKhachHang> expResult = null;
        ArrayList<ModelKhachHang> result = instance.MenuKH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTableReserve method, of class ServiceStaff.
     */
    @Test
    public void testSetTableReserve() throws Exception {
        System.out.println("setTableReserve");
        int idBan = 0;
        ServiceStaff instance = new ServiceStaff();
        instance.setTableReserve(idBan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CancelTableReserve method, of class ServiceStaff.
     */
    @Test
    public void testCancelTableReserve() throws Exception {
        System.out.println("CancelTableReserve");
        int idBan = 0;
        ServiceStaff instance = new ServiceStaff();
        instance.CancelTableReserve(idBan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FindHoaDonbyID_Ban method, of class ServiceStaff.
     */
    @Test
    public void testFindHoaDonbyID_Ban() throws Exception {
        System.out.println("FindHoaDonbyID_Ban");
        ModelBan table = null;
        ServiceStaff instance = new ServiceStaff();
        ModelHoaDon expResult = null;
        ModelHoaDon result = instance.FindHoaDonbyID_Ban(table);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdateHoaDonStatus method, of class ServiceStaff.
     */
    @Test
    public void testUpdateHoaDonStatus() throws Exception {
        System.out.println("UpdateHoaDonStatus");
        int idHD = 0;
        ServiceStaff instance = new ServiceStaff();
        instance.UpdateHoaDonStatus(idHD);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTenKH method, of class ServiceStaff.
     */
    @Test
    public void testGetTenKH() throws Exception {
        System.out.println("getTenKH");
        int idKH = 0;
        ServiceStaff instance = new ServiceStaff();
        String expResult = "";
        String result = instance.getTenKH(idKH);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNhanVienById method, of class ServiceStaff.
     */
    @Test
    public void testGetNhanVienById() {
        System.out.println("getNhanVienById");
        int i = 0;
        ServiceStaff instance = new ServiceStaff();
        ModelNhanVien expResult = null;
        ModelNhanVien result = instance.getNhanVienById(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
