package com.assistant;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.AbstractDAOA;

public class AssistantDaoImpl extends AbstractDAOA implements assistantIdao {

    @Override
    public void add(Assistant obj) {
        PreparedStatement pst = null;
        String sql = "insert into Assistant (carteIdentite, email, password, nomAssistant, prenomAssistant, dateNaissance, telephone) values (?,?,?,?,?,?,?);";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getCarteIdentite());
            pst.setString(2, obj.getEmail());
            pst.setString(3, obj.getPassword());
            pst.setString(4, obj.getNomAssistant());
            pst.setString(5, obj.getPrenomAssistant());
		    pst.setDate(6, (Date) obj.getDateNaissance());
		    pst.setString(7, obj.getTelephone());
 
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    
    public void delete(String carteIdentite) {
        PreparedStatement pst = null;
        String sql = "delete *from Assistant where carteIdentite= ? ;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, carteIdentite);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    
    public Assistant getOne(String carteIdentite) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Assistant where carteIdentite= ? ;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, carteIdentite);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                return new Assistant(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), dateNaissance,  rs.getString("telephone"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<Assistant> getAll() {
        List<Assistant> list = new ArrayList<Assistant>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Assistant";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                 System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                list.add(new Assistant(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), dateNaissance,  rs.getString("telephone")));

            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Assistant> getAll(String des) {
        List<Assistant> list = new ArrayList<Assistant>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Assistant where nomAssistant like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("carteIdentite") + "" + rs.getString("email"));
                Date dateNaissance = rs.getDate("dateNaissance");
                list.add(new Assistant(rs.getString("carteIdentite"), rs.getString("email"), rs.getString("password"), rs.getString("nomAssistant"), rs.getString("prenomAssistant"), dateNaissance,  rs.getString("telephone")));
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
	public Assistant getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
