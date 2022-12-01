package com.patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.dao.AbstractDAOA;

@ManagedBean
public class PatientDaoImpl extends AbstractDAOA implements patientIdao {
	

    @Override
    public void add(Patient obj) {
        PreparedStatement pst = null;
        String sql = "insert into Patient (carteIdentite, email, password, nomPatient, prenomPatient, dateNaissance, telephone) values (?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getCarteIdentite());
            pst.setString(2, obj.getEmail());
            pst.setString(3, obj.getPassword());
            pst.setString(4, obj.getNomPatient());
            pst.setString(5, obj.getPrenomPatient());
            Date date = Date.valueOf(obj.getDateNaissance());
            pst.setDate(6, date);
		    //pst.setDate(6, (Date) obj.getDateNaissance());
		    pst.setString(7, obj.getTelephone());
 
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    
    public void delete(String carteIdentite) {
        PreparedStatement pst = null;
        String sql = "delete *from Patient where carteIdentite= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, carteIdentite);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    
    public Patient getOne(String carteIdentite) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Patient where carteIdentite= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, carteIdentite);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                return new Patient(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomPatient"), rs.getString("prenomPatient"), dateNaissance.toLocalDate(),  rs.getString("telephone"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }
    
    public Patient getPatientLogged(String email) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Patient where email= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                return new Patient(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomPatient"), rs.getString("prenomPatient"), dateNaissance.toLocalDate(),  rs.getString("telephone"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> list = new ArrayList<Patient>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Patient";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                 System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                list.add(new Patient(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomPatient"), rs.getString("prenomPatient"), dateNaissance.toLocalDate(),  rs.getString("telephone")));

            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Patient> getAll(String des) {
        List<Patient> list = new ArrayList<Patient>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Patient where nomPatient like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                list.add(new Patient(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomPatient"), rs.getString("prenomPatient"), dateNaissance.toLocalDate(),  rs.getString("telephone")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static boolean validate(String email, String password) {
		PreparedStatement pst = null;
		String sql = "Select email, password from Patient where email = ? and password = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/digicab";
			String user = "root";
			String pwd = "root";
			Connection con = DriverManager.getConnection(url, user, pwd);
			
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, email);
				pst.setString(2, password);

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					//result found, means valid inputs
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} finally {
				con.close();
			}
			
			return false;
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return false;
	}



	
}
