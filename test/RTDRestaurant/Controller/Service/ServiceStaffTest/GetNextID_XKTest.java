    private int getCurrentMaxID_XK() throws SQLException, SQLException, SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(ID_XK) AS max_id FROM PhieuXK");
        int max = 0;
        if (rs.next()) {
            max = rs.getInt("max_id");
        }
        rs.close();
        st.close();
        return max;
    }
}