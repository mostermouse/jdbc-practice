package org.jdbc;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdate(User user, String sql, PreparedStatemntSetter pss)throws SQLException{
        Connection con = null;
        PreparedStatement pstmt =null;

        try{
            con = ConnectionManger.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);
            pstmt.executeUpdate();
        }finally {
            if(pstmt != null){
                pstmt.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
}
