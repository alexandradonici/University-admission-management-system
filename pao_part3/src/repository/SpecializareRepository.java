package repository;

import model.Facultate;
import model.Specializare;

import java.sql.*;
import java.util.ArrayList;

public class SpecializareRepository
{


    private static final String url = "jdbc:mysql://localhost:3306/admitere_database";
    private static final String user = "root";
    private static final String passwrod = "";

    private FacultateRepository facultateRepository = FacultateRepository.getInstance();

    private static SpecializareRepository instance = null;

    public SpecializareRepository(){}

    public static SpecializareRepository getInstance()
    {
        if(instance == null)
            instance = new SpecializareRepository();

        return instance;
    }

    public void add(Specializare specializare)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        Statement statement_select;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "INSERT INTO specializari (id_specializare, id_facultate, nume, locuri_buget, locuri_taxa) VALUES (?, ?, ?, ?, ?)";
            statement = connect.prepareStatement(sql);

            statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet1 = statement_select.executeQuery("select * from specializari");

            if(resultSet1.last())
                statement.setInt(1, resultSet1.getInt("id_specializare") + 1);
            else
                statement.setInt(1, 1);

            statement.setInt(2,specializare.getFacultate().getId_facultate());
            statement.setString(3,specializare.getNume());
            statement.setInt(4,specializare.getLocuri_buget());
            statement.setInt(5,specializare.getLocuri_taxa());
            statement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void remove(int id)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        PreparedStatement statement_aplicatie = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "delete from specializari where id_specializare = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            statement.execute();

            String sql1 = "delete from aplicatii where id_specializare = ?";
            statement_aplicatie = connect.prepareStatement(sql1);
            statement_aplicatie.setInt(1, id);
            statement_aplicatie.execute();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeFacultate(int id_facultate)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "delete from specializari where id_facultate = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id_facultate);
            statement.execute();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }


    public Specializare getSpecializareID(int id)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "select * from specializari where id_specializare= ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                return new Specializare(resultSet.getInt("id_specializare"),resultSet.getString("nume"), facultateRepository.getFacultateID(resultSet.getInt("id_facultate")), resultSet.getInt("locuri_buget"), resultSet.getInt("locuri_taxa"));



        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Specializare> getSpecializareFacultate(Facultate facultate)
    {
        ArrayList<Specializare> s = new ArrayList<>();

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from specializari where id_facultate = ?";
            statement = connect.prepareStatement(sql);

            statement.setInt(1, facultate.getId_facultate());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                s.add(new Specializare(resultSet.getInt("id_specializare"),
                        resultSet.getString("nume"),
                        facultateRepository.getFacultateID(resultSet.getInt("id_facultate")),
                        resultSet.getInt("locuri_buget"),
                        resultSet.getInt("locuri_taxa")));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return s;
    }

    public ArrayList<Specializare> getSpecializari()
    {
        ArrayList<Specializare> s = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from specializari");

            while(resultSet.next())
                s.add(new Specializare(resultSet.getInt("id_specializare"),resultSet.getString("nume"), facultateRepository.getFacultateID(resultSet.getInt("id_facultate")), resultSet.getInt("locuri_buget"), resultSet.getInt("locuri_taxa")));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return s;
    }


    public void AfisSpecializari()
    {
        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from specializari");

            while(resultSet.next()) System.out.println("Nume: " + resultSet.getString("nume") + "\nLocuri buget: "+ resultSet.getInt("locuri_buget") + "\nLocuri taxa: " + resultSet.getInt("locuri_taxa") + "\nId facultate: " + resultSet.getInt("id_facultate") + "\n\n");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateNume (int id_specializare, String nume)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "UPDATE specializari SET nume = ?  WHERE id_specializare = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setInt(2, id_specializare);
            statement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

}
