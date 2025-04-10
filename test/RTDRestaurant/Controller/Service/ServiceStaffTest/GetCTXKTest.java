package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import RTDRestaurant.Model.ModelCTXK;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GetCTXKTest {
    private Connection con;
    private ServiceStaff ss;

    @Before
    public void setUp() throws Exception {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connectToDatabase();
        con = dbConnection.getConnection();
        ss = new ServiceStaff();
    }

    @Test
    public void testGetCTXK_ValidID100() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(100);
        assertNotNull(result);
        assertEquals("ID_XK = 100 nên có 3 dòng", 3, result.size());
    }

    @Test
    public void testGetCTXK_ValidID101() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(101);
        assertNotNull(result);
        assertEquals("ID_XK = 101 nên có 5 dòng", 5, result.size());
    }

    @Test
    public void testGetCTXK_ValidID102() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(102);
        assertNotNull(result);
        assertEquals("ID_XK = 102 nên có 4 dòng", 4, result.size());
    }

    @Test
    public void testGetCTXK_InvalidID999() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(999);
        assertNotNull(result);
        assertEquals("ID không tồn tại nên trả về 0 dòng", 0, result.size());
    }

    @Test
    public void testGetCTXK_NegativeID() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(-1);
        assertNotNull(result);
        assertEquals("ID âm không nên có dữ liệu", 0, result.size());
    }

    @Test
    public void testGetCTXK_ZeroID() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(0);
        assertNotNull(result);
        assertEquals("ID = 0 không nên có dữ liệu", 0, result.size());
    }

    @Test
    public void testGetCTXK_HugeID() throws Exception {
        ArrayList<ModelCTXK> result = ss.getCTXK(Integer.MAX_VALUE);
        assertNotNull(result);
        assertEquals("ID rất lớn không nên có dữ liệu", 0, result.size());
    }
}
