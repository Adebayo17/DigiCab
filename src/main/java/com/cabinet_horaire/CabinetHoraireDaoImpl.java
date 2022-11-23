package com.cabinet_horaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cabinetMedical.CabinetMedical;
import com.dao.AbstractDAOA;
import com.horaire.Horaire;
import com.horaire.JourSemaine;

public class CabinetHoraireDaoImpl extends AbstractDAOA implements cabinetHoraireIdao {

    @Override
    public void add(CabinetHoraire obj) {
        PreparedStatement pst = null;
        String sql = "insert into CabinetHoraire (horaire_id, codeSirenCabinet) values (?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, obj.getHoraire().getHoraire_id());
            pst.setLong(2, obj.getCabinet().getCodeSiren());
            
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long codeSirenCabinet) {
        PreparedStatement pst = null;
        String sql = "delete *from CabinetHoraire where codeSirenCabinet= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, codeSirenCabinet);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public CabinetHoraire getOne(long codeSirenCabinet) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * \r\n"
        		+ "FROM CabinetHoraire as ch, CabinetMedical as cm, Horaire as h\r\n"
        		+ "WHERE ch.codeSirenCabinet = cm.codeSiren\r\n"
        		+ "AND ch.horaire_id = h.horaire_id\r\n"
        		+ "AND ch.codeSirenCabinet = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, codeSirenCabinet);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getLong("codeSirenCabinet"));
                JourSemaine jour = JourSemaine.valueOf(rs.getString("jourSemaine"));
                return new CabinetHoraire(new CabinetMedical(codeSirenCabinet, rs.getString("cm.nomCabinet")),
                		new Horaire(rs.getLong("h.horaire_id"), jour, rs.getTime("h.heureDebut"), rs.getTime("h.heureFin")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<CabinetHoraire> getAll() {
        List<CabinetHoraire> list = new ArrayList<CabinetHoraire>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * \r\n"
        		+ "FROM CabinetHoraire as ch, CabinetMedical as cm, Horaire as h\r\n"
        		+ "WHERE ch.codeSirenCabinet = cm.codeSiren\r\n"
        		+ "AND ch.horaire_id = h.horaire_id";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                JourSemaine jour = JourSemaine.valueOf(rs.getString("jourSemaine"));
                list.add(new CabinetHoraire(new CabinetMedical(rs.getLong("cm.codeSiren"), rs.getString("cm.nomCabinet")),
                		new Horaire(rs.getLong("h.horaire_id"), jour, rs.getTime("h.heureDebut"), rs.getTime("h.heureFin"))));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<CabinetHoraire> getAll(String des) {
        List<CabinetHoraire> list = new ArrayList<CabinetHoraire>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * \r\n"
        		+ "FROM CabinetHoraire as ch, CabinetMedical as cm, Horaire as h\r\n"
        		+ "WHERE ch.codeSirenCabinet = cm.codeSiren\r\n"
        		+ "AND ch.horaire_id = h.horaire_id "
        		+ "and where cm.nomCabinet like ? ";
        //"select *from CabinetHoraire and where designation like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("cm.codeSiren") + "" + rs.getString("cm.nomCabinet"));
                JourSemaine jour = JourSemaine.valueOf(rs.getString("jourSemaine"));
                list.add(new CabinetHoraire(new CabinetMedical(rs.getLong("cm.codeSiren"), rs.getString("cm.nomCabinet")),
                		new Horaire(rs.getLong("h.horaire_id"), jour, rs.getTime("h.heureDebut"), rs.getTime("h.heureFin"))));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
}