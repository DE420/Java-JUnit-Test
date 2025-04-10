package RTDRestaurant.Controller.Service;

import RTDRestaurant.Controller.Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GetNextID_NKTest {
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
    public void testGetNextIDNK_WithExistingData() throws Exception {
        // Giả định bảng đã có dữ liệu với MAX(ID_NK) = 110
        int expectedMax = getCurrentMaxID_NK();
        int expectedNext = expectedMax + 1;

        int nextID = ss.getNextID_NK();
        assertEquals("Phải trả về ID kế tiếp", expectedNext, nextID);
    }

    @Test
    public void testGetNextIDNK_WhenTableEmpty() throws Exception {
        con.setAutoCommit(false);
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM PhieuNK");

            int nextID = ss.getNextID_NK();
            assertEquals("Nếu bảng rỗng thì ID tiếp theo là 1", 1, nextID);
        } finally {
            con.rollback();  // rollback lại dữ liệu cũ
            con.setAutoCommit(true);
        }
    }

    @Test
    public void testGetNextIDNK_WhenMaxIDNearIntegerLimit() throws Exception {
        con.setAutoCommit(false);
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM PhieuNK");
            st.executeUpdate("INSERT INTO PhieuNK(ID_NK, ID_NV, NgayNK) VALUES (" + Integer.MAX_VALUE + ", 1, SYSDATE)");

            int nextID = ss.getNextID_NK();
            assertEquals("Nếu MAX = Integer.MAX_VALUE thì +1 sẽ bị tràn số", Integer.MIN_VALUE, nextID);  // sẽ bị overflow
        } finally {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    // Hàm phụ để lấy MAX ID_NK hiện tại trong bảng
    private int getCurrentMaxID_NK() throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(ID_NK) AS max_id FROM PhieuNK");
        int maxID = 0;
        if (rs.next()) {
            maxID = rs.getInt("max_id");
        }
        rs.close();
        st.close();
        return maxID;
    }
}
