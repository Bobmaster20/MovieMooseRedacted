package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import beans.Person;

 
public class DBUtils {
 
  public static Person findPerson(Connection conn, String SSN) throws SQLException {
 
      String sql = "Select P.FirstName, P.LastName, P.Address, P.ZipCode, P.Telephone from Person P "
              + " where P.SSN = ? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, SSN);
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          Person person = new Person();
          person.setSSN(SSN);
          person.setFirstName(rs.getString("FirstName"));
          person.setLastName(rs.getString("LastName"));
          person.setAddress(rs.getString("Address"));
          person.setZipCode(rs.getInt("ZipCode"));
          person.setAddress(rs.getString("Telephone"));
          return person;
      }
      return null;
  }
 
 
}
