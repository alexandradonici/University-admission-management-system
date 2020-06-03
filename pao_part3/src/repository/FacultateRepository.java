package repository;

import model.Facultate;

import java.sql.*;
import java.util.ArrayList;

public class FacultateRepository
{

    private static final String url = "jdbc:mysql://localhost:3306/admitere_database";
    private static final String user = "root";
    private static final String passwrod = "";

    private static FacultateRepository instance = null;

    public FacultateRepository(){}

    public static FacultateRepository getInstance()
    {
        if(instance == null)
            instance = new FacultateRepository();

        return instance;
    }

    public void add(Facultate facultate)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        Statement statement_select;

            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connect = DriverManager.getConnection(url, user, passwrod);
                String sql = "INSERT INTO facultati (id_facultate, nume, tip_facultate) VALUES (?, ?, ?)";
                statement = connect.prepareStatement(sql);

                statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet1 = statement_select.executeQuery("select * from facultati");

                if(resultSet1.last())
                {
                    statement.setInt(1, resultSet1.getInt("id_facultate") + 1);
                }
                else
                {
                    statement.setInt(1, 1);
                }
                statement.setString(2,facultate.getNume());
                statement.setInt(3, facultate.getTip_facultate());
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

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "delete from facultati where id_facultate = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            statement.execute();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Facultate getFacultateID(int id)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "select * from facultati where id_facultate= ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                return new Facultate(resultSet.getInt("id_facultate"),resultSet.getString("nume"), resultSet.getInt("tip_facultate"));



        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public ArrayList<Facultate> getFacultati()
    {
        ArrayList<Facultate> f = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from facultati");

            while(resultSet.next())
                f.add(new Facultate(resultSet.getInt("id_facultate"), resultSet.getString("nume"), resultSet.getInt("tip_facultate") ));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return f;
    }

    public ArrayList<Facultate> getFacultatiCuAdmitere()
    {
        ArrayList<Facultate> facultati_cu_admitere = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from facultati where tip_facultate = 1");

            while(resultSet.next())
                facultati_cu_admitere.add(new Facultate(resultSet.getInt("id_facultate"), resultSet.getString("nume"), resultSet.getInt("tip_facultate") ));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return facultati_cu_admitere;
    }

    public ArrayList<Facultate> getFacultatiFaraAdmitere()
    {
        ArrayList<Facultate> facultati_fara_admitere = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from facultati where tip_facultate = 0");

            while(resultSet.next())
                facultati_fara_admitere.add(new Facultate(resultSet.getInt("id_facultate"), resultSet.getString("nume"), resultSet.getInt("tip_facultate") ));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return facultati_fara_admitere;
    }

    public void AfisFacultati()
    {
        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from facultati");

            while(resultSet.next()) System.out.println(resultSet.getString("nume") + " "+ resultSet.getInt("tip_facultate") + " " + resultSet.getInt("id_facultate"));
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateNume (int id_facultate, String nume)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "UPDATE facultati SET nume = ?  WHERE id_facultate = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setInt(2, id_facultate);
            statement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
}
