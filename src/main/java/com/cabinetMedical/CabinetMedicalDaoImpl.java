package com.cabinetMedical;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.assistant.Assistant;
import com.dao.AbstractDAOA;
import com.listes.Domaine;
import com.listes.Ville;
import com.medecin.Médecin;

@ManagedBean
public class CabinetMedicalDaoImpl extends AbstractDAOA implements cabinetMedicalIdao {
	

	@Override
    public void add(CabinetMedical obj) {
        PreparedStatement pst = null;
        String sql = "insert into CabinetMedical (codeSiren, nomCabinet, nomVille, adresse, carteIdentiteMedecin, carteIdentiteAssistant, telephone, domaine) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, obj.getCodeSiren());
            pst.setString(2, obj.getNomCabinet());
            pst.setString(3, obj.getNomVille().name());
            pst.setString(4, obj.getAdresse());                        
            pst.setString(5, obj.getMedecin().getCarteIdentite());                     
            pst.setString(6, obj.getAssistant().getCarteIdentite());
            pst.setString(7, obj.getTelephone());
            pst.setString(8, obj.getDomaine().name());
            
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long codeSiren) {
        PreparedStatement pst = null;
        String sql = "delete *from CabinetMedical where codeSiren= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, codeSiren);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public CabinetMedical getOne(long codeSiren) {
    	
    	int codeSiren1 = (int) codeSiren;
    	
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT *\r\n"
        		+ "FROM Medecin as m1, Assistant as a1, CabinetMedical as cm1\r\n"
        		+ "WHERE cm1.CarteIdentiteMedecin = m1.carteIdentite\r\n"
        		+ "AND cm1.CarteIdentiteAssistant = a1.carteIdentite"
        		+ "AND cm1.codeSiren= ?";
        
        
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, codeSiren1);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getLong("codeSiren") + "" + rs.getString("nomCabinet"));
                Domaine domaine = Domaine.valueOf(rs.getString("cm1.domaine"));
                Ville ville = Ville.valueOf(rs.getString("cm1.nomVille"));
                return new CabinetMedical(rs.getLong("cm1.codeSiren"), rs.getString("cm1.nomCabinet"), ville, rs.getString("cm1.adresse"), 
                		new Médecin(rs.getString("m1.carteIdentite"), rs.getString("m1.email"), rs.getString("m1.password"), rs.getString("m1.nomMedecin"), rs.getString("m1.prenomMedecin"), rs.getString("m1.specialite"), rs.getDate("m1.dateNaissance").toLocalDate(), rs.getString("m1.telephone")),
            			new Assistant(rs.getString("a1.carteIdentite"), rs.getString("a1.email"), rs.getString("a1.password"), rs.getString("a1.nomAssistant"), rs.getString("a1.prenomAssistant"), rs.getDate("a1.dateNaissance").toLocalDate(), rs.getString("a1.telephone")), 
            			rs.getString("cm1.telephone"), domaine);
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }
    
    

    @Override
    public List<CabinetMedical> getAll() {
        List<CabinetMedical> list = new ArrayList<CabinetMedical>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT *\r\n"
        		+ "FROM Medecin as m1, Assistant as a1, CabinetMedical as cm1\r\n"
        		+ "WHERE cm1.CarteIdentiteMedecin = m1.carteIdentite\r\n"
        		+ "AND cm1.CarteIdentiteAssistant = a1.carteIdentite";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("codeSiren") + "" + rs.getString("nomCabinet"));
                //Date date = rs.getDate("date");
                Domaine domaine = Domaine.valueOf(rs.getString("cm1.domaine"));
                Ville ville = Ville.valueOf(rs.getString("cm1.nomVille"));
                list.add(new CabinetMedical(rs.getLong("cm1.codeSiren"), rs.getString("cm1.nomCabinet"), ville, rs.getString("cm1.adresse"), 
                		new Médecin(rs.getString("m1.carteIdentite"), rs.getString("m1.email"), rs.getString("m1.password"), rs.getString("m1.nomMedecin"), rs.getString("m1.prenomMedecin"), rs.getString("m1.specialite"), rs.getDate("m1.dateNaissance").toLocalDate(), rs.getString("m1.telephone")),
            			new Assistant(rs.getString("a1.carteIdentite"), rs.getString("a1.email"), rs.getString("a1.password"), rs.getString("a1.nomAssistant"), rs.getString("a1.prenomAssistant"), rs.getDate("a1.dateNaissance").toLocalDate(), rs.getString("a1.telephone")), 
            			rs.getString("cm1.telephone"), domaine));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<CabinetMedical> getAll(String nomCabinet) {
        List<CabinetMedical> list = new ArrayList<CabinetMedical>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from CabinetMedical where nomCabinet like ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nomCabinet + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("codeSiren") + "" + rs.getString("nomCabinet"));
                //Date date = rs.getDate("date");
                Domaine domaine = Domaine.valueOf(rs.getString("cm1.domaine"));
                Ville ville = Ville.valueOf(rs.getString("cm1.nomVille"));
                list.add(new CabinetMedical(rs.getLong("cm1.codeSiren"), rs.getString("cm1.nomCabinet"), ville, rs.getString("cm1.adresse"), 
                		new Médecin(rs.getString("m1.carteIdentite"), rs.getString("m1.email"), rs.getString("m1.password"), rs.getString("m1.nomMedecin"), rs.getString("m1.prenomMedecin"), rs.getString("m1.specialite"), rs.getDate("m1.dateNaissance").toLocalDate(), rs.getString("m1.telephone")),
            			new Assistant(rs.getString("a1.carteIdentite"), rs.getString("a1.email"), rs.getString("a1.password"), rs.getString("a1.nomAssistant"), rs.getString("a1.prenomAssistant"), rs.getDate("a1.dateNaissance").toLocalDate(), rs.getString("a1.telephone")), 
            			rs.getString("cm1.telephone"), domaine));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
    
    
    public List<CabinetMedical> getAll(String nomCabinet, Domaine domaine, Ville ville) {
        List<CabinetMedical> list = new ArrayList<CabinetMedical>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from CabinetMedical where nomCabinet like ? and domaine = ? and nomVille = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nomCabinet + "%");
            pst.setString(2, domaine.toString());
            pst.setString(3, ville.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("codeSiren") + "" + rs.getString("nomCabinet"));
                //Date date = rs.getDate("date");
                list.add(new CabinetMedical(rs.getLong("cm1.codeSiren"), rs.getString("cm1.nomCabinet"), ville, rs.getString("cm1.adresse"), 
                		new Médecin(rs.getString("m1.carteIdentite"), rs.getString("m1.email"), rs.getString("m1.password"), rs.getString("m1.nomMedecin"), rs.getString("m1.prenomMedecin"), rs.getString("m1.specialite"), rs.getDate("m1.dateNaissance").toLocalDate(), rs.getString("m1.telephone")),
            			new Assistant(rs.getString("a1.carteIdentite"), rs.getString("a1.email"), rs.getString("a1.password"), rs.getString("a1.nomAssistant"), rs.getString("a1.prenomAssistant"), rs.getDate("a1.dateNaissance").toLocalDate(), rs.getString("a1.telephone")), 
            			rs.getString("cm1.telephone"), domaine));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
    
    
    
    
}