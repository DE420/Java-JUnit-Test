/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Model.ModelBan;
import RTDRestaurant.Model.ModelCTHD;
import RTDRestaurant.Model.ModelHoaDon;
import RTDRestaurant.Model.ModelKhachHang;
import RTDRestaurant.Model.ModelMonAn;
import RTDRestaurant.Model.ModelVoucher;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import RTDRestaurant.Controller.Connection.DatabaseConnection;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class ServiceCustomerTest {
    private Connection con;
    private ServiceCustomer sc;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        sc = new ServiceCustomer();
    }

    /**
     * Test of MenuFood method, of class ServiceCustomer.
     */
    @Test
    public void testMenuFood_tonTai() throws Exception{
        //lay ra danh sach mon an theo loai mon an co ton tai
        String type = "Aries";  //1 loai mon an hop le
        ArrayList<ModelMonAn> listMA = sc.MenuFood(type);
        assertNotNull(listMA);  //co tra ve danh sach
        assertEquals(12, listMA.size());    //CSDL hien tai co 12 mon an thuoc loai Aries
    }
    
    @Test
    public void testMenuFood_koTonTai() throws Exception{
        //lay ra danh sach mon an theo loai mon an khong ton tai
        String type = "A";  //loai mon an khong ton tai
        ArrayList<ModelMonAn> listMA = sc.MenuFood(type);
        assertNull(listMA); //khong tra ve DS
    }

    /**
     * Test of MenuFoodOrder method, of class ServiceCustomer.
     */
    @Test
    public void testMenuFoodOrder_orderByName() throws Exception {
        //lay ra danh sach mon an theo loai mon an co ton tai theo thu tu bang chu cai
        String type = "Aries";  //1 loai mon an hop le
        String orderBy = "Tên A->Z";    //sap xep theo thu tu bang chu cai
        ArrayList<ModelMonAn> listMA = sc.MenuFoodOrder(type, orderBy);
        assertNotNull(listMA);  //co tra ve danh sach
        assertEquals(12, listMA.size());    //CSDL hien tai co 12 mon an thuoc loai Aries
        //kiem tra danh sach dung thu tu bang chu cai
        assertEquals("BAP CUU NUONG CAY", listMA.get(0).getTitle());
        assertEquals("BE SUON CUU NUONG GIAY BAC MONG CO", listMA.get(1).getTitle());
        assertEquals("CUU KUNGBAO", listMA.get(2).getTitle());
        assertEquals("CUU VIEN HAM CAY", listMA.get(3).getTitle());
        assertEquals("CUU XOC LA CA RI", listMA.get(4).getTitle());
        assertEquals("DUI CUU LON NUONG TAI BAN", listMA.get(5).getTitle());
        assertEquals("DUI CUU NUONG TIEU XANH", listMA.get(6).getTitle());
        assertEquals("DUI CUU NUONG TRUNG DONG", listMA.get(7).getTitle());
        assertEquals("DUI CUU NUONG XE NHO", listMA.get(8).getTitle());
        assertEquals("SUON CONG NUONG MONG CO", listMA.get(9).getTitle());
        assertEquals("SUON CUU SOT PHO MAI", listMA.get(10).getTitle());
        assertEquals("SUONG CUU NUONG SOT NAM", listMA.get(11).getTitle());
    }
    
    @Test
    public void testMenuFoodOrder_orderByPriceUp() throws Exception {
        //lay ra danh sach mon an theo loai mon an co ton tai theo thu tu gia tang dan
        String type = "Aries";  //1 loai mon an hop le
        String orderBy = "Giá tăng dần";    //sap xep theo thu tu gia tang dan
        ArrayList<ModelMonAn> listMA = sc.MenuFoodOrder(type, orderBy);
        assertNotNull(listMA);  //co tra ve danh sach
        assertEquals(12, listMA.size());    //CSDL hien tai co 12 mon an thuoc loai Aries
        //kiem tra danh sach dung thu tu gia tang dan
        assertEquals(19000, listMA.get(0).getValue());                     //gia 19000
        assertEquals(129000, listMA.get(1).getValue());                     //gia 129000
        assertEquals(230000, listMA.get(2).getValue());   //gia 230000
        assertEquals(250000, listMA.get(3).getValue());                          //gia 250000
        assertEquals(250000, listMA.get(4).getValue());                 //gia 250000
        assertEquals(250000, listMA.get(5).getValue());                    //gia 250000
        assertEquals(250000, listMA.get(6).getValue());              //gia 250000
        assertEquals(285000, listMA.get(7).getValue());              //gia 285000
        assertEquals(350000, listMA.get(8).getValue());             //gia 350000
        assertEquals(450000, listMA.get(9).getValue());              //gia 450000
        assertEquals(450000, listMA.get(10).getValue());                //gia 450000
        assertEquals(750000, listMA.get(11).getValue());           //gia 750000
    }
    
    @Test
    public void testMenuFoodOrder_orderByPriceDown() throws Exception {
        //lay ra danh sach mon an theo loai mon an co ton tai theo thu tu gia giam dan
        String type = "Aries";  //1 loai mon an hop le
        String orderBy = "Giá giảm dần";    //sap xep theo thu tu gia giam dan
        ArrayList<ModelMonAn> listMA = sc.MenuFoodOrder(type, orderBy);
        assertNotNull(listMA);  //co tra ve danh sach
        assertEquals(12, listMA.size());    //CSDL hien tai co 12 mon an thuoc loai Aries
        //kiem tra danh sach dung thu tu gia giam dan
        assertEquals("DUI CUU LON NUONG TAI BAN", listMA.get(0).getTitle());            //gia 750000
        assertEquals("SUON CUU SOT PHO MAI", listMA.get(1).getTitle());                 //gia 450000
        assertEquals("SUONG CUU NUONG SOT NAM", listMA.get(2).getTitle());              //gia 450000
        assertEquals("DUI CUU NUONG TRUNG DONG", listMA.get(3).getTitle());             //gia 350000
        assertEquals("DUI CUU NUONG TIEU XANH", listMA.get(4).getTitle());              //gia 285000
        assertEquals("SUON CONG NUONG MONG CO", listMA.get(5).getTitle());              //gia 250000
        assertEquals("CUU KUNGBAO", listMA.get(6).getTitle());                          //gia 250000
        assertEquals("DUI CUU NUONG XE NHO", listMA.get(7).getTitle());                 //gia 250000
        assertEquals("BAP CUU NUONG CAY", listMA.get(8).getTitle());                    //gia 250000
        assertEquals("BE SUON CUU NUONG GIAY BAC MONG CO", listMA.get(9).getTitle());   //gia 230000
        assertEquals("CUU XOC LA CA RI", listMA.get(10).getTitle());                    //gia 129000
        assertEquals("CUU VIEN HAM CAY", listMA.get(11).getTitle());                    //gia 19000
    }
    
    @Test
    public void testMenuFoodOrder_orderRong() throws Exception {
        //lay ra danh sach mon an theo loai mon an và đe trong order
        String type = "Aries";  //1 loai mon an hop le
        String orderBy = "";    //sorder de trong
        ArrayList<ModelMonAn> listMA = sc.MenuFoodOrder(type, orderBy);
        assertNotNull(listMA);  //co tra ve danh sach
        assertEquals(12, listMA.size());    //CSDL hien tai co 12 mon an thuoc loai Aries
        //neu khong co order thi danh sach lay ra theo thư tu id (mac dinh)
        assertEquals("DUI CUU NUONG XE NHO", listMA.get(0).getTitle());                 //id 1
        assertEquals("BE SUON CUU NUONG GIAY BAC MONG CO", listMA.get(1).getTitle());   //id 2
        assertEquals("DUI CUU NUONG TRUNG DONG", listMA.get(2).getTitle());             //id 3
        assertEquals("CUU XOC LA CA RI", listMA.get(3).getTitle());                     //id 4
        assertEquals("CUU KUNGBAO", listMA.get(4).getTitle());                          //id 5
        assertEquals("BAP CUU NUONG CAY", listMA.get(5).getTitle());                    //id 6
        assertEquals("CUU VIEN HAM CAY", listMA.get(6).getTitle());                     //id 7
        assertEquals("SUON CONG NUONG MONG CO", listMA.get(7).getTitle());              //id 8
        assertEquals("DUI CUU LON NUONG TAI BAN", listMA.get(8).getTitle());            //id 9
        assertEquals("SUONG CUU NUONG SOT NAM", listMA.get(9).getTitle());              //id 10
        assertEquals("DUI CUU NUONG TIEU XANH", listMA.get(10).getTitle());             //id 11
        assertEquals("SUON CUU SOT PHO MAI", listMA.get(11).getTitle());                //id 12
    }
    
    @Test
    public void testMenuFoodOrder_orderSai() throws Exception {
        //de orderBy sai
        String type = "Aries";  //1 loai mon an hop le
        String orderBy = "A";    //orderBy sai
        ArrayList<ModelMonAn> listMA = sc.MenuFoodOrder(type, orderBy);
        assertNull(listMA);    //khong tra ve DS
    }
    
    @Test
    public void testMenuFoodOrder_typeSai() throws Exception {
        //de type sai
        String type = "A";  //1 loai mon an sai
        String orderBy = "Tên A->Z";    //sap xep theo thu tu bang chu cai
        ArrayList<ModelMonAn> listMA = sc.MenuFoodOrder(type, orderBy);
        assertNull(listMA);    //khong tra ve DS
    }

    /**
     * Test of MenuTable method, of class ServiceCustomer.
     */
    @Test
    public void testMenuTable_tonTai() throws Exception {
        //lay DS ban tư tang co ton tai
        String floor = "Tang 1"; // tang co ton tai
        ArrayList<ModelBan> listBan = sc.MenuTable(floor);
        assertEquals(12, listBan.size());   //co 12 ban o tang 1 trong CSDL
    }
    
    @Test
    public void testMenuTable_koTonTai() throws Exception {
        //lay DS ban tư tang khong ton tai
        String floor = "Tang 0"; // tang khong ton tai
        ArrayList<ModelBan> listBan = sc.MenuTable(floor);
        assertNull(listBan);   //khong tra ve DS
    }

    /**
     * Test of MenuTableState method, of class ServiceCustomer.
     */
    @Test
    public void testMenuTableState_tatCa() throws Exception {
        //lay DS tat ca cac ban tu tang hop le
        String floor = "Tang 1"; // tang co ton tai
        String state = "Tất cả";    //tat ca cac ban
        ArrayList<ModelBan> listBan = sc.MenuTableState(floor, state);
        assertEquals(12, listBan.size());   //co 12 ban o tang 1 trong CSDL
    }

    @Test
    public void testMenuTableState_conTrong() throws Exception {
        //lay DS cac ban con trong tu tang hop le
        String floor = "Tang 1"; // tang co ton tai
        String state = "Còn trống";    //cac ban con trong
        ArrayList<ModelBan> listBan = sc.MenuTableState(floor, state);
        assertEquals(4, listBan.size());   //co 4 ban con trong o tang 1 trong CSDL
        for(int i = 0; i < listBan.size(); i++){
            assertEquals("Con trong", listBan.get(i).getStatus());
        }   //kiem tra trang thai cac ban
    }
    
    @Test
    public void testMenuTableState_daDatTruoc() throws Exception {
        //lay DS cac ban da dat truoc tu tang hop le
        String floor = "Tang 1"; // tang co ton tai
        String state = "Đã đặt trước";    //cac ban da dat truoc
        ArrayList<ModelBan> listBan = sc.MenuTableState(floor, state);
        assertEquals(1, listBan.size());   //co 1 ban da dat truoc o tang 1 trong CSDL
        for(int i = 0; i < listBan.size(); i++){
            assertEquals("Da dat truoc", listBan.get(i).getStatus());
        }   //kiem tra trang thai cac ban
    }
    
    @Test
    public void testMenuTableState_dangDungBua() throws Exception {
        //lay DS cac ban dang dung bua tu tang hop le
        String floor = "Tang 1"; // tang co ton tai
        String state = "Đang dùng bữa";    //cac ban dang dung bua
        ArrayList<ModelBan> listBan = sc.MenuTableState(floor, state);
        assertEquals(7, listBan.size());   //co 7 ban dang dung bua o tang 1 trong CSDL
        for(int i = 0; i < listBan.size(); i++){
            assertEquals("Dang dung bua", listBan.get(i).getStatus());
        }   //kiem tra trang thai cac ban
    }
    
    @Test
    public void testMenuTableState_stateSai() throws Exception {
        //state sai, tang hop le
        String floor = "Tang 1"; // tang co ton tai
        String state = "A";    //trang thai khong hop le
        ArrayList<ModelBan> listBan = sc.MenuTableState(floor, state);
        assertNull(listBan);    //khong tra ve DS
    }
    
    @Test
    public void testMenuTableState_floorSai() throws Exception {
        //state hop le, tang sai
        String floor = "Tang 0"; // tang khong ton tai
        String state = "Tất cả";    //trang thai hop le
        ArrayList<ModelBan> listBan = sc.MenuTableState(floor, state);
        assertNull(listBan);    //khong tra ve DS
    }
    
    /**
     * Test of getCustomer method, of class ServiceCustomer.
     */
    @Test
    public void testGetCustomer_tonTai() throws Exception {
        //lay thong tin khach hang voi ID co ton tai
        int userID = 100;   //lay id khach hang ton tai
        ModelKhachHang kh = sc.getCustomer(userID);
        assertNotNull(kh);
        assertEquals(100, kh.getID_KH());   //kiem tra id
    }

    @Test
    public void testGetCustomer_koTonTai() throws Exception {
        //lay thong tin khach hang voi ID co ton tai
        int userID = 1; //lay id khach hang khong ton tai
        ModelKhachHang kh = sc.getCustomer(userID);
        assertNull(kh); //khong lay ra ban ghi nao
    }
    
    /**
     * Test of reNameCustomer method, of class ServiceCustomer.
     */
    @Test
    public void testReNameCustomer_hopLe() throws Exception {
        //doi ten khach hang co ID_KH la 100 thanh Le Van A
        ModelKhachHang kh = new ModelKhachHang(100, "Le Van A", "10-MAY-23", 0, 0);
        try{
            con.setAutoCommit(false);
            sc.reNameCustomer(kh);
            //kiem tra
            //ham getCustomer() lay dau vao la ID nguoi dung thay vi ID khach hang
            //ID nguoi dung 104 ung voi ID khach hang 100
            assertEquals(100, sc.getCustomer(104).getID_KH());
            assertEquals("Le Van A", sc.getCustomer(104).getName());
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testReNameCustomer_khHopLe() throws Exception {
        //doi ten khach hang co ID_KH la 100 thanh ten co chua so va ki tu dac biet
        //truong hop ten rong cung tinh vao ten khong hop le
        ModelKhachHang kh = new ModelKhachHang(100, "L3 V4n !", "10-MAY-23", 0, 0);
        try{
            con.setAutoCommit(false);
            sc.reNameCustomer(kh);
            //kiem tra
            //ham getCustomer() lay dau vao la ID nguoi dung thay vi ID khach hang
            //ID nguoi dung 104 ung voi ID khach hang 100
            assertEquals(100, sc.getCustomer(104).getID_KH());
            assertEquals("Ha Thao Duong", sc.getCustomer(104).getName());   //giu lai ten goc, khong doi
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test of MenuVoucher method, of class ServiceCustomer.
     */
    @Test
    public void testMenuVoucher() throws Exception {
        //lay DS voucher, ham nay khong co input
        ArrayList<ModelVoucher> listV = sc.MenuVoucher();
        assertEquals(12, listV.size()); //co 12 voucher trong CSDL
    }

    /**
     * Test of MenuVoucherbyPoint method, of class ServiceCustomer.
     */
    @Test
    public void testMenuVoucherbyPoint_tatCa() throws Exception {
        //lay DS tat ca voucher
        String bypoint = "Tất cả";
        ArrayList<ModelVoucher> listV = sc.MenuVoucherbyPoint(bypoint);
        assertEquals(12, listV.size()); //co tat ca 12 voucher trong CSDL
    }
    
    @Test
    public void testMenuVoucherbyPoint_duoi300() throws Exception {
        //lay DS voucher duoi 300 diem
        String bypoint = "Dưới 300 xu";
        ArrayList<ModelVoucher> listV = sc.MenuVoucherbyPoint(bypoint);
        assertEquals(6, listV.size()); //co tat ca 6 voucher < 300 diem trong CSDL
        for(int i = 0; i < listV.size(); i++){
            assertTrue(listV.get(i).getPoint() < 300);  //kiem tra diem duoi 300
        }
    }
    
    @Test
    public void testMenuVoucherbyPoint_giua300500() throws Exception {
        //lay DS voucher tu 300 den 500 diem
        String bypoint = "Từ 300 đến 500 xu";
        ArrayList<ModelVoucher> listV = sc.MenuVoucherbyPoint(bypoint);
        assertEquals(4, listV.size()); //co tat ca 4 voucher tu 300 den 500 diem trong CSDL
        for(int i = 0; i < listV.size(); i++){
            assertTrue(listV.get(i).getPoint() >= 300);  //kiem tra diem tu 300 - 500
            assertTrue(listV.get(i).getPoint() <= 500); 
        }
    }
    
    @Test
    public void testMenuVoucherbyPoint_tren500() throws Exception {
        //lay DS voucher tren 500 diem
        String bypoint = "Trên 500 xu";
        ArrayList<ModelVoucher> listV = sc.MenuVoucherbyPoint(bypoint);
        assertEquals(2, listV.size()); //co tat ca 2 voucher tren 500 diem trong CSDL
        for(int i = 0; i < listV.size(); i++){
            assertTrue(listV.get(i).getPoint() > 500);  //kiem tra diem tren 500
        }
    }
    
    @Test
    public void testMenuVoucherbyPoint_sai() throws Exception {
        //lay bypoint khong hop le
        String bypoint = "A";
        ArrayList<ModelVoucher> listV = sc.MenuVoucherbyPoint(bypoint);
        assertNull(listV);  //khong tra ve DS
    }

    /**
     * Test of InsertHoaDon method, of class ServiceCustomer.
     */
    @Test
    public void testInsertHoaDon_hopLe() throws Exception {
        //truong hop lay ban va khach hang hop le
        //ham tao hoa don tu dong khi khach hang dang ki ban
        ModelBan b = new ModelBan(108, "Ban T1.9");
        ModelKhachHang kh = new ModelKhachHang(110, "Trung", "03-MAR-25", 0, 800);
        try{
            con.setAutoCommit(false);
            sc.InsertHoaDon(b, kh);
            //kiem tra
            ModelHoaDon hd = sc.FindHoaDon(kh); //ham lay hoa don vua tao
            //hoa don tao moi nhat se lay id cua hoa don truoc +1, o day khi tao hoa don se den id 123
            assertEquals(123, hd.getIdHoaDon());
            assertEquals(108, hd.getIdBan());   //kiem tra id ban
            assertEquals(110, hd.getIdKH());    //kiem tra id KH
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertHoaDon_banDangSuDung() throws Exception {
        //truong hop lay ban dang duoc su dung va khach hang hop le
        //ban dang duoc su dung (dang dung bua / dat truoc) thi khach hang moi khong dang ki duoc ban do
        ModelBan b = new ModelBan(100, "Ban T1.1"); //ban dang duoc su dung
        ModelKhachHang kh = new ModelKhachHang(110, "Trung", "03-MAR-25", 0, 800);  //khach hang hop le
        try{
            con.setAutoCommit(false);
            sc.InsertHoaDon(b, kh);
            //kiem tra
            ModelHoaDon hd = sc.FindHoaDon(kh); //ham lay hoa don vua tao
            assertNull(hd); //khong co hoa don nao duoc tao do ban dang duoc su dung
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertHoaDon_khachHangConHoaDon() throws Exception {
        //truong hop lay ban hop le và khach hang dang con hoa don chua duoc thanh toan
        //khach hang khong the tao hoa don moi neu nhu van con hoa don chua thanh toan
        ModelBan b = new ModelBan(108, "Ban T1.9"); //ban hop le
        ModelKhachHang kh = new ModelKhachHang(100, "Ha Thao Duong", "10-MAY-23", 0, 0);    //khach hang van con hoa don chua thanh toan
        try{
            con.setAutoCommit(false);
            sc.InsertHoaDon(b, kh);
            //kiem tra
            ModelHoaDon hd = sc.FindHoaDon(kh); //ham lay hoa don vua tao
            assertNull(hd); //khong co hoa don duoc tao do khach hang chua thanh toan het hoa don
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertHoaDon_banKoTonTai() throws Exception {
        //truong hop lay ban dang duoc su dung va khach hang hop le
        //ban dang duoc su dung (dang dung bua / dat truoc) thi khach hang moi khong dang ki duoc ban do
        ModelBan b = new ModelBan(1, "Ban T0.0"); //ban khong ton tai
        ModelKhachHang kh = new ModelKhachHang(110, "Trung", "03-MAR-25", 0, 800);  //khach hang hop le
        try{
            con.setAutoCommit(false);
            sc.InsertHoaDon(b, kh);
            //kiem tra
            ModelHoaDon hd = sc.FindHoaDon(kh); //ham lay hoa don vua tao
            assertNull(hd); //khong co hoa don nao duoc tao do ban khong ton tai
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertHoaDon_khachHangKoTonTai() throws Exception {
        //truong hop lay ban hop le và khach hang dang con hoa don chua duoc thanh toan
        //khach hang khong the tao hoa don moi neu nhu van con hoa don chua thanh toan
        ModelBan b = new ModelBan(108, "Ban T1.9"); //ban hop le
        ModelKhachHang kh = new ModelKhachHang(1, "A", "03-MAR-25", 0, 0);    //khach hang khong ton tai
        try{
            con.setAutoCommit(false);
            sc.InsertHoaDon(b, kh);
            //kiem tra
            ModelHoaDon hd = sc.FindHoaDon(kh); //ham lay hoa don vua tao
            assertNull(hd); //khong co hoa don duoc tao do khach hang khong ton tai
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{ 
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test of FindHoaDon method, of class ServiceCustomer.
     */
    @Test
    public void testFindHoaDon_conHoaDon() throws Exception {
        //lay hoa don chua thanh toan moi nhat cua khach hang
        //truong hop khach hang co hoa don chua thanh toan
        ModelKhachHang kh = new ModelKhachHang(101, "Truong Tan Hieu", "10-MAY-23", 0, 0);
        ModelHoaDon hd = sc.FindHoaDon(kh);
        //kiem tra
        assertNotNull(hd);  //ton tai hoa don chua thanh toan
        assertEquals(104, hd.getIdHoaDon());    //thong tin hoa don trong CSDL
        assertEquals(101, hd.getIdKH());    //kiem tra ID khach hang tuong ung
        assertEquals("Chua thanh toan", hd.getTrangthai()); //kiem tra trang thai chua thanh toan
    }
    
    @Test
    public void testFindHoaDon_hetHoaDon() throws Exception {
        //truong hop khach hang khong co hoa don chua thanh toan
        ModelKhachHang kh = new ModelKhachHang(110, "Trung", "03-MAR-25", 0, 800);
        ModelHoaDon hd = sc.FindHoaDon(kh);
        //kiem tra
        assertNull(hd); //khong ton tai hoa don chua thanh toan
    }
    
    @Test
    public void testFindHoaDon_koTonTai() throws Exception {
        //truong hop doi tuong khach hang khong ton tai
        ModelKhachHang kh = new ModelKhachHang(1, "A", "10-MAY-23", 0, 0);
        ModelHoaDon hd = sc.FindHoaDon(kh);
        //kiem tra
        assertNull(hd); //khong tra ve do khong ton tai khach hang
    }

    /**
     * Test of InsertCTHD method, of class ServiceCustomer.
     */
    @Test
    public void testInsertCTHD_themMonAn() throws Exception {
        //ham cap nhat hoa don
        //truong hop hoa don hop le, mon an hop le & chua co trong hoa don, so luong hop le
        int ID_HoaDon = 109;    //id hoa don hop le
        int ID_MonAn = 1;       //id mon an chua ton tai trong hoa don
        int soluong = 1;        //so luong hop le
        try{
            con.setAutoCommit(false);
            sc.InsertCTHD(ID_HoaDon, ID_MonAn, soluong);
            //kiem tra
            ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);  //lay DS chi tiet hoa don
            assertEquals(2, listCTHD.size());  //ban dau hoa don chi co 1 mon an, sau khi them thi len 2
            assertEquals(1, listCTHD.get(0).getID_MonAn()); //kiem tra ID mon an moi them vao
            assertEquals(1, listCTHD.get(0).getSoluong());  //kiem tra so luong them vao
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertCTHD_capNhatSL() throws Exception {
        //truong hop hoa don hop le, mon an hop le & da co trong hoa don, so luong hop le
        int ID_HoaDon = 109;    //id hoa don hop le
        int ID_MonAn = 45;       //id mon an da ton tai trong hoa don
        int soluong = 1;        //so luong hop le
        try{
            con.setAutoCommit(false);
            sc.InsertCTHD(ID_HoaDon, ID_MonAn, soluong);
            //kiem tra
            ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);  //lay DS chi tiet hoa don
            assertEquals(1, listCTHD.size());  //hoa don chi co 1 mon an
            assertEquals(45, listCTHD.get(0).getID_MonAn()); //kiem tra ID mon an
            assertEquals(5, listCTHD.get(0).getSoluong());  //ban dau hoa don co 4 mon, sau khi them len 5
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertCTHD_soLuongSai() throws Exception {
        //truong hop hoa don hop le, mon an hop le, so luong khong hop le
        int ID_HoaDon = 109;    //id hoa don hop le
        int ID_MonAn = 1;       //id mon an hop le
        int soluong = 0;        //so luong <= 0
        try{
            con.setAutoCommit(false);
            sc.InsertCTHD(ID_HoaDon, ID_MonAn, soluong);
            //kiem tra
            ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);  //lay DS chi tiet hoa don
            assertEquals(1, listCTHD.size());  //so luong mon an khong doi = 1
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertCTHD_monAnSai() throws Exception {
        //truong hop hoa don hop le, mon an khong hop le, so luong hop le
        int ID_HoaDon = 109;    //id hoa don hop le
        int ID_MonAn = 0;       //id mon an khong hop le
        int soluong = 1;        //so luong hop le
        try{
            con.setAutoCommit(false);
            sc.InsertCTHD(ID_HoaDon, ID_MonAn, soluong);
            //kiem tra
            ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);  //lay DS chi tiet hoa don
            assertEquals(1, listCTHD.size());  //so luong mon an khong doi = 1
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testInsertCTHD_hoaDonSai() throws Exception {
        //truong hop hoa don khong hop le, mon an hop le, so luong hop le
        int ID_HoaDon = 1;    //id hoa don khong hop le
        int ID_MonAn = 1;       //id mon an hop le
        int soluong = 1;        //so luong hop le
        try{
            con.setAutoCommit(false);
            sc.InsertCTHD(ID_HoaDon, ID_MonAn, soluong);
            //kiem tra
            ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);  //lay DS chi tiet hoa don
            assertNull(listCTHD);   //hoa don khong ton tai
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test of getCTHD method, of class ServiceCustomer.
     */
    @Test
    public void testGetCTHD_tonTai() throws Exception {
        //ham lay DS chi tiet hoa don cua 1 hoa don
        //truong hop hoa don ton tai
        int ID_HoaDon = 101;    //id hoa don ton tai
        ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);
        //kiem tra
        assertEquals(3, listCTHD.size());   //hoa don 101 co 3 mon an
        for(int i = 0; i < listCTHD.size(); i++){
            assertEquals(ID_HoaDon, listCTHD.get(i).getID_HD());    //kiem tra CTHD thuoc ve hoa don 101
        }
    }
    
    @Test
    public void testGetCTHD_koTonTai() throws Exception {
        //truong hop hoa don khong ton tai
        int ID_HoaDon = 1;    //id hoa don khong ton tai
        ArrayList<ModelCTHD> listCTHD = sc.getCTHD(ID_HoaDon);
        //kiem tra
        assertNull(listCTHD);   //khong tra ve DS do hoa don khong ton tai
    }

    /**
     * Test of getListHD method, of class ServiceCustomer.
     */
    @Test
    public void testGetListHD_coHoaDon() throws Exception {
        //ham lay DS hoa don theo ID khach hang
        //truong hop khach hang co hoa don
        int ID_KH = 101;    //khach hang co hoa don
        ArrayList<ModelHoaDon> listHD = sc.getListHD(ID_KH);
        //kiem tra
        assertEquals(1, listHD.size()); //khach hang 101 chi co 1 hoa don
        assertEquals(ID_KH, listHD.get(0).getIdKH());   //kiem tra ID khach hang trong hoa don
    }
    
    @Test
    public void testGetListHD_koCoHoaDon() throws Exception {
        //truong hop khach hang khong co hoa don
        int ID_KH = 110;    //khach hang khong co hoa don
        ArrayList<ModelHoaDon> listHD = sc.getListHD(ID_KH);
        //kiem tra
        assertEquals(0, listHD); //tra ve DS rong do khach hang khong co hoa don
    }
    
    @Test
    public void testGetListHD_khongTonTai() throws Exception {
        //truong hop khach hang khong ton tai
        int ID_KH = 1;
        ArrayList<ModelHoaDon> listHD = sc.getListHD(ID_KH);
        //kiem tra
        assertNull(listHD); //khong tra ve do khach hang khong ton tai
    }

    /**
     * Test of getListHDOrder method, of class ServiceCustomer.
     */
    @Test
    public void testGetListHDOrder_tatCa() throws Exception {
        //ham lay DS hoa don cua khach hang theo moc tien
        //truong hop lay tat ca hoa don
        int ID_KH = 104;    //id khach hang ton tai
        String order = "Tất cả";    //lay tat ca hoa don
        ArrayList<ModelHoaDon> listHD = sc.getListHDOrder(ID_KH, order);
        //kiem tra
        assertEquals(2, listHD.size()); //khach hang co 2 hoa don
        for(int i = 0; i < listHD.size(); i++){
            assertEquals(ID_KH, listHD.get(i).getIdKH());   //kiem tra ID khach hang trong hoa don
        }
    }
    
    @Test
    public void testGetListHDOrder_duoi1M() throws Exception {
        //truong hop lay hoa don < 1.000.000 d
        int ID_KH = 104;    //id khach hang ton tai
        String order = "Dưới 1.000.000đ";    //lay hoa don < 1.000.000 d
        ArrayList<ModelHoaDon> listHD = sc.getListHDOrder(ID_KH, order);
        //kiem tra
        assertEquals(1, listHD.size()); //khach hang co 1 hoa don < 1.000.000 d
        assertEquals(ID_KH, listHD.get(0).getIdKH());   //kiem tra ID khach hang trong hoa don
        assertTrue(listHD.get(0).getTongtien() < 1000000);  //kiem tra gia tri hoa don
    }
    
    @Test
    public void testGetListHDOrder_giua1M5M() throws Exception {
        //truong hop lay hoa don giua 1.000.000 d va 5.000.000 d
        int ID_KH = 103;    //id khach hang ton tai
        String order = "Từ 1 đến 5.000.000đ";    //lay tat ca hoa don
        ArrayList<ModelHoaDon> listHD = sc.getListHDOrder(ID_KH, order);
        //kiem tra
        assertEquals(2, listHD.size()); //khach hang co 1 hoa don < 1.000.000 d
        for(int i = 0; i < listHD.size(); i++){
            assertEquals(ID_KH, listHD.get(i).getIdKH());   //kiem tra ID khach hang trong hoa don
            assertTrue(listHD.get(i).getTongtien() >= 1000000);  //kiem tra gia tri hoa don
            assertTrue(listHD.get(i).getTongtien() <= 5000000);
        }
    }
    
    @Test
    public void testGetListHDOrder_tren5M() throws Exception {
        //truong hop lay hoa don > 5.000.000 d
        int ID_KH = 104;    //id khach hang ton tai
        String order = "Trên 5.000.000đ";    //lay hoa don > 5.000.000 d
        ArrayList<ModelHoaDon> listHD = sc.getListHDOrder(ID_KH, order);
        //kiem tra
        assertEquals(1, listHD.size()); //khach hang co 1 hoa don > 5.000.000 d
        assertEquals(ID_KH, listHD.get(0).getIdKH());   //kiem tra ID khach hang trong hoa don
        assertTrue(listHD.get(0).getTongtien() > 5000000);  //kiem tra gia tri hoa don
    }
    
    @Test
    public void testGetListHDOrder_orderSai() throws Exception {
        //truong hop input order khong hop le
        int ID_KH = 104;    //id khach hang ton tai
        String order = "A";    //khong hop le
        ArrayList<ModelHoaDon> listHD = sc.getListHDOrder(ID_KH, order);
        //kiem tra
        assertNull(listHD); //khong tra ve DS do input order khong hop le
    }
    
    @Test
    public void testGetListHDOrder_KHSai() throws Exception {
        //truong hop khach hang khong ton tai
        int ID_KH = 1;    //id khach hang khong ton tai
        String order = "Tất cả";
        ArrayList<ModelHoaDon> listHD = sc.getListHDOrder(ID_KH, order);
        //kiem tra
        assertNull(listHD); //khong tra ve DS do khach hang khong ton tai
    }

    /**
     * Test of exchangeVoucher method, of class ServiceCustomer.
     */
    @Test
    public void testExchangeVoucher_cungLoai() throws Exception {
        //ham ap dung voucher de giam gia cho nhung mon an cung loai
        //truong hop voucher co the giam gia cho do an trong hoa don
        int ID_HoaDon = 103;    //hoa don co mon an loai Aries
        String Code_Voucher = "loQy";   //voucher giam 20% cho mon loai Aries
        try{
            con.setAutoCommit(false);
            sc.exchangeVoucher(ID_HoaDon, Code_Voucher);
            //kiem tra
            // do khong co ham tim hoa don truc tiep theo ID_HD nen chuyen qua tim theo nguoi dung
            ArrayList<ModelHoaDon> listHD = sc.getListHD(105);  //hd cua khach hang 105
            ModelHoaDon hd = listHD.get(0); //hoa don can tim
            assertEquals(ID_HoaDon, hd.getIdHoaDon());  //kiem tra lai ID
            assertEquals(900000, hd.getTienMonAn());  //kiem tra tien goc
            assertEquals(Code_Voucher, hd.getCode_voucher());  //kiem tra voucher
            assertEquals(180000, hd.getTienGiam());  //900000 * 20% = 180000
            assertEquals(720000, hd.getTongtien());  //900000 - 180000 = 720000
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testExchangeVoucher_khacLoai() throws Exception {
        //truong hop voucher khong giam gia cho do an trong hoa don
        int ID_HoaDon = 103;    //hoa don co mon an loai Aries
        String Code_Voucher = "Pwyn";   //voucher giam 20% cho mon loai Cancer
        try{
            con.setAutoCommit(false);
            sc.exchangeVoucher(ID_HoaDon, Code_Voucher);
            //kiem tra
            // do khong co ham tim hoa don truc tiep theo ID_HD nen chuyen qua tim theo nguoi dung
            ArrayList<ModelHoaDon> listHD = sc.getListHD(105);  //hd cua khach hang 105
            ModelHoaDon hd = listHD.get(0); //hoa don can tim
            assertEquals(ID_HoaDon, hd.getIdHoaDon());  //kiem tra lai ID
            assertEquals(900000, hd.getTienMonAn());  //kiem tra tien goc
            assertEquals(Code_Voucher, hd.getCode_voucher());  //kiem tra voucher
            assertEquals(0, hd.getTienGiam());  //khong giam gia do khac loai voucher
            assertEquals(900000, hd.getTongtien());
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testExchangeVoucher_koTonTai() throws Exception {
        //truong hop voucher khong ton tai
        int ID_HoaDon = 103;    //hoa don co mon an loai Aries
        String Code_Voucher = "xxxx";   //voucher khong ton tai
        try{
            con.setAutoCommit(false);
            sc.exchangeVoucher(ID_HoaDon, Code_Voucher);
            //kiem tra
            // do khong co ham tim hoa don truc tiep theo ID_HD nen chuyen qua tim theo nguoi dung
            ArrayList<ModelHoaDon> listHD = sc.getListHD(105);  //hd cua khach hang 105
            ModelHoaDon hd = listHD.get(0); //hoa don can tim
            assertEquals(ID_HoaDon, hd.getIdHoaDon());  //kiem tra lai ID
            assertEquals(900000, hd.getTienMonAn());  //kiem tra tien goc
            assertEquals(null, hd.getCode_voucher());  //voucher khong duoc ap dung
            assertEquals(0, hd.getTienGiam());  //voucher khong giam gia
            assertEquals(900000, hd.getTongtien());
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
