/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelNguyenLieu;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.Test;
import junit.framework.Assert;
import org.junit.Before;

/**
 *
 * @author Admin
 */
public class InsertNLTest {
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
     * Test of InsertNL method, of class ServiceStaff.
     */
    @Test
    public void testInsertNL_themMoi(){
        //them nguyen lieu moi hoan toan
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(116);
        nl.setTenNL("Thit cho");
        nl.setDonGia(80000);
        nl.setDvt("kg");
        try{
            con.setAutoCommit(false);
            ss.InsertNL(nl);
            //kiem tra
            ArrayList<ModelNguyenLieu> listNL = ss.MenuNL();
            Assert.assertNotNull(listNL);
            Assert.assertEquals(17, listNL.size());
            Assert.assertEquals(nl.getId(), listNL.get(16).getId());
            Assert.assertEquals(nl.getTenNL(), listNL.get(16).getTenNL());
            Assert.assertEquals(nl.getDonGia(), listNL.get(16).getDonGia());
            Assert.assertEquals(nl.getDvt(), listNL.get(16).getDvt());
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
    public void testInsertNL_themTrung(){
        //them nguyen da ton tai
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(115);  //ban ghi da ton tai trong he thong
        nl.setTenNL("Thit de");
        nl.setDonGia(130000);
        nl.setDvt("kg");
        try{
            con.setAutoCommit(false);
            ss.InsertNL(nl);
            //kiem tra
            ArrayList<ModelNguyenLieu> listNL = ss.MenuNL();
            Assert.assertNotNull(listNL);
            Assert.assertEquals(16, listNL.size());
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
    public void testInsertNL_themRong(){
        //them nguyen lieu rong
        ModelNguyenLieu nl = null;
        try{
            con.setAutoCommit(false);
            ss.InsertNL(nl);
            //kiem tra
            ArrayList<ModelNguyenLieu> listNL = ss.MenuNL();
            Assert.assertNotNull(listNL);
            Assert.assertEquals(16, listNL.size());
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
    public void testInsertNL_nhapKhuyet(){
        //nhap khuyet vai truong
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(117);
        nl.setTenNL("");
        nl.setDonGia(0);
        nl.setDvt("");
        try{
            con.setAutoCommit(false);
            ss.InsertNL(nl);
            //kiem tra
            ArrayList<ModelNguyenLieu> listNL = ss.MenuNL();
            Assert.assertNotNull(listNL);
            Assert.assertEquals(16, listNL.size());
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
    public void testInsertNL_nhapSai(){
        //nhap sai dinh dang
        ModelNguyenLieu nl = new ModelNguyenLieu();
        nl.setId(116);
        nl.setTenNL("Th!t ch0");     //ki tu dac biet
        nl.setDonGia(-80000);       //so am
        nl.setDvt("mg");               //don vị khong ton tai
        try{
            con.setAutoCommit(false);
            ss.InsertNL(nl);
            //kiem tra
            ArrayList<ModelNguyenLieu> listNL = ss.MenuNL();
            Assert.assertNotNull(listNL);
            Assert.assertEquals(16, listNL.size());
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
