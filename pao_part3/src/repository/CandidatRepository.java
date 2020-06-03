package repository;

import java.sql.*;
import java.util.ArrayList;
import model.Candidat;
import service.RepartitieService;

public class CandidatRepository
{

    private static final String url = "jdbc:mysql://localhost:3306/admitere_database";
    private static final String user = "root";
    private static final String passwrod = "";

    private static CandidatRepository instance = null;

    public CandidatRepository(){}

    public static CandidatRepository getInstance()
    {
        if(instance == null)
            instance = new CandidatRepository();

        return instance;
    }

    public void add(Candidat candidat)
    {
        Candidat c = getCandidatNume(candidat.getNume(), candidat.getPrenume());

        Connection connect = null;
        PreparedStatement statement = null;
        Statement statement_select = null;

        if(c == null)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connect = DriverManager.getConnection(url, user, passwrod);
                String sql = "INSERT INTO candidati (nume, prenume, id_candidat) VALUES (?, ?, ?)";
                statement = connect.prepareStatement(sql);

                statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet1 = statement_select.executeQuery("select * from candidati");

                statement.setString(1, candidat.getNume());
                statement.setString(2, candidat.getPrenume());
                if(resultSet1.last())
                     statement.setInt(3, resultSet1.getInt("id_candidat")+1);
                else
                    statement.setInt(3, 1 );
                statement.executeUpdate();

            }
            catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }

        else
            System.out.println("Candidatul exista deja in baza de date!");

    }

    public void remove(int id)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        PreparedStatement statement_aplicatie = null;
        PreparedStatement statement_bac = null;
        PreparedStatement statement_examen = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "delete from candidati where id_candidat = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

            String sql1 = "select * from aplicatii where id_candidat = ?";
            statement_aplicatie = connect.prepareStatement(sql1);
            statement_aplicatie.setInt(1, id);
            ResultSet resultSet = statement_aplicatie.executeQuery();

            while(resultSet.next())
            {
                int id_a = resultSet.getInt("id_aplicatie");
                RepartitieService.getInstance().remove(id_a);
            }


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

    }

    public Candidat getCandidatNume(String nume, String prenume)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "select * from candidati where nume = ? and prenume = ?";
            statement = connect.prepareStatement(get);
            statement.setString(1, nume);
            statement.setString(2, prenume);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                return new Candidat(resultSet.getInt("id_candidat"),resultSet.getString("nume"), resultSet.getString("prenume"));



        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public Candidat getCandidatID(int id)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from candidati where id_candidat = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            return new Candidat(resultSet.getInt("id_candidat"),resultSet.getString("nume"), resultSet.getString("prenume"));



        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Candidat> getCandidati()
    {
        ArrayList<Candidat> c = new ArrayList<>();

        Connection connect = null;
         Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from candidati");

            while(resultSet.next())
                c.add(new Candidat(resultSet.getInt("id_candidat"), resultSet.getString("nume"), resultSet.getString("prenume") ));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return c;
    }

    public void AfisCandidati()
    {
         Connection connect = null;
         Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from candidati");

            while(resultSet.next()) System.out.println(resultSet.getString("nume") + " " + resultSet.getString("prenume") + " "  + resultSet.getInt("id_candidat"));
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateNume (int id_candidat, String nume, String prenume)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "UPDATE candidati SET nume = ? , prenume = ? WHERE id_candidat = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setInt(3, id_candidat);
            statement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }



}
