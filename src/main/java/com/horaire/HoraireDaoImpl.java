package com.horaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.AbstractDAOA;

public class HoraireDaoImpl extends AbstractDAOA implements horaireIdao {

    @Override
    public void add(Horaire obj) {
        PreparedStatement pst = null;
        String sql = "insert into Horaire (horaire_id, jourSemaine, heureDebut, heureFin) values (?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, obj.getHoraire_id());
            pst.setString(2, obj.getJour().name());
            pst.setTime(3, obj.getHeureDebut());
            pst.setTime(4, obj.getHeureFin());

            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long horaire_id) {
        PreparedStatement pst = null;
        String sql = "delete *from Horaire where horaire_id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, horaire_id);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public Horaire getOne(long horaire_id) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Horaire where horaire_id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, horaire_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getLong("horaire_id") + "" + rs.getString("jourSemaine"));
                JourSemaine jour = JourSemaine.valueOf(rs.getString("jourSemaine"));
                return new Horaire(rs.getLong("horaire_id"), jour, rs.getTime("heureDebut"), rs.getTime("heureFin"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<Horaire> getAll() {
        List<Horaire> list = new ArrayList<Horaire>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Horaire";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                JourSemaine jour = JourSemaine.valueOf(rs.getString("jourSemaine"));
                list.add(new Horaire(rs.getLong("horaire_id"), jour, rs.getTime("heureDebut"), rs.getTime("heureFin")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Horaire> getAll(String des) {
        List<Horaire> list = new ArrayList<Horaire>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select *from Horaire where jourSemaine like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                JourSemaine jour = JourSemaine.valueOf(rs.getString("jourSemaine"));
                list.add(new Horaire(rs.getLong("horaire_id"), jour, rs.getTime("heureDebut"), rs.getTime("heureFin")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
}