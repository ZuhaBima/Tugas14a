import java.sql.*;

public interface Database 
{
    void view() throws SQLException;
    void update() throws SQLException;
    void delete() throws SQLException;
    void save() throws SQLException;
    void search() throws SQLException;
}