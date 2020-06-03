package repository;

import model.Bacalaureat;
import java.sql.*;
import java.util.ArrayList;

public class BacalaureatRepository
{
    private static final String url = "jdbc:mysql://localhost:3306/admitere_database";
    private static final String user = "root";
    private static final String passwrod = "";

    private static BacalaureatRepository instance = null;

    public BacalaureatRepository(){}

    public static BacalaureatRepository getInstance()
    {
        if(instance == null)
            instance = new BacalaureatRepository();

        return instance;
    }

    public void add(Bacalaureat bac)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        Statement statement_select;
        Statement statement_id;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "INSERT INTO note_bac (id_bac, id_aplicatie, probaP1, probaP2, probaP3, medie_bac ) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connect.prepareStatement(sql);

            statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet1 = statement_select.executeQuery("select * from note_bac");



            if(resultSet1.last())
            statement.setInt(1, resultSet1.getInt("id_bac") + 1);
            else
                statement.setInt(1, 1);

            statement_id = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet2 = statement_id.executeQuery("select * from aplicatii");

            if(resultSet2.last())
                 statement.setInt(2,resultSet2.getInt("id_aplicatie") + 1);
            else
                statement.setInt(2,1);

            statement.setDouble(3, bac.getNotaP1());
            statement.setDouble(4, bac.getNotaP2());
            statement.setDouble(5, bac.getNotaP3());
            statement.setDouble(6, bac.getMedieBac());
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
            String get = "delete from note_bac where id_aplicatie = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            statement.execute();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }


    public Bacalaureat getBacID(int id_aplicatie)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from note_bac where id_aplicatie = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_aplicatie);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                return new Bacalaureat(resultSet.getInt("id_bac"),resultSet.getDouble("probaP1"),resultSet.getDouble("probaP2"),resultSet.getDouble("probaP3"),resultSet.getInt("id_aplicatie"));


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Bacalaureat> getNote_bac()
    {
        ArrayList<Bacalaureat> note_bac = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from note_bac";
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
                note_bac.add(new Bacalaureat(resultSet.getInt("id_bac"),resultSet.getDouble("probaP1"),resultSet.getDouble("probaP2"),resultSet.getDouble("probaP3"),resultSet.getInt("id_aplicatie")));


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return note_bac;
    }

    public int getLastID()
    {
        Connection connect = null;
        Statement statement_select;


        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);

            statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet1 = statement_select.executeQuery("select * from note_bac");



            if(resultSet1.last())
              return resultSet1.getInt("id_bac");


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return 0;

    }


}
