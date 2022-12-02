package com.medecin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.cabinetMedical.CabinetMedical;
import com.dao.AbstractDAOA;
import com.listes.Domaine;
import com.listes.Ville;

@ManagedBean
public class MedecinDaoImpl extends AbstractDAOA implements medecinIdao {

    @Override
    public void add(Médecin obj) {
        PreparedStatement pst = null;
        String sql = "insert into Medecin(carteIdentite,email, password, nomMedecin,prenomMedecin,specialite,dateNaissance,telephone) values (?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getCarteIdentite());
            pst.setString(2, obj.getEmail());
            pst.setString(3, obj.getPassword());
            pst.setString(4, obj.getNomMedecin());
            pst.setString(5, obj.getPrenomMedecin());
            pst.setString(6, obj.getSpecialite());
            Date date = Date.valueOf(obj.getDateNaissance());
            pst.setDate(7, date);
            pst.setString(8, obj.getTelephone());

            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        
    }
    
    public void delete(String carteIdentite) {
        PreparedStatement pst = null;
        String sql = "delete *from Medecin where carteIdentite= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, carteIdentite);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public Médecin getOne(long id) {
        return null;
    }
    
    public Médecin getOne(String carteIdentite) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Medecin where carteIdentite= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, carteIdentite);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                return new Médecin(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), rs.getString("specialite"), dateNaissance.toLocalDate(),  rs.getString("telephone"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }
    
    public Médecin getMedecinlogged(String email) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Medecin where email= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                return new Médecin(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), rs.getString("specialite"), dateNaissance.toLocalDate(),  rs.getString("telephone"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<Médecin> getAll() {
        List<Médecin> list = new ArrayList<Médecin>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Medecin";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                Date dateNaissance = rs.getDate("dateNaissance");
                list.add(new Médecin(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), rs.getString("specialite"), dateNaissance.toLocalDate(),  rs.getString("telephone")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Médecin> getAll(String des) {
        List<Médecin> list = new ArrayList<Médecin>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Medecin where nomMedecin like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("nomMedecin"));
                Date dateNaissance = rs.getDate("dateNaissance");
                list.add(new Médecin(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), rs.getString("specialite"), dateNaissance.toLocalDate(),  rs.getString("telephone")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
    
    
    public static boolean validate(String email, String password) {
		PreparedStatement pst = null;
		String sql = "Select email, password from Medecin where email = ? and password = ?";
		
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
    
    public CabinetMedical getMedlogged(String email) {
    	
    	
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * FROM Medecin as m1, CabinetMedical as cm1 WHERE cm1.CarteIdentiteMedecin = m1.carteIdentite AND m1.email= ?";
        
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getLong("codeSiren") + "" + rs.getString("nomCabinet"));
                Domaine domaine = Domaine.valueOf(rs.getString("cm1.domaine"));
                Ville ville = Ville.valueOf(rs.getString("cm1.nomVille"));
                return new CabinetMedical(rs.getLong("cm1.codeSiren"), rs.getString("cm1.nomCabinet"), ville, rs.getString("cm1.adresse"), 
                							rs.getString("cm1.telephone"), domaine);
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

	
}