package repository;

import model.ExamenAdmitere;
import java.sql.*;
import java.util.ArrayList;

public class ExamenRepository
{
    private static final String url = "jdbc:mysql://localhost:3306/admitere_database";
    private static final String user = "root";
    private static final String passwrod = "";

    private static ExamenRepository instance = null;

    public ExamenRepository(){}

    public static ExamenRepository getInstance()
    {
        if(instance == null)
            instance = new ExamenRepository();

        return instance;
    }

    public void add(ExamenAdmitere admitere)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        Statement statement_select;
        Statement statement_id;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "INSERT INTO note_admitere (id_examen, nota_examen, sala_examen, id_aplicatie ) VALUES (?, ?, ?, ?)";
            statement = connect.prepareStatement(sql);

            statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet1 = statement_select.executeQuery("select * from note_admitere");



           if(resultSet1.last())
                statement.setInt(1, resultSet1.getInt("id_examen") + 1);
            else
                statement.setInt(1, 1);
            statement.setDouble(2,admitere.getNotaExamen());
            statement.setString(3,admitere.getSalaExamen());

            statement_id = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet2 = statement_id.executeQuery("select * from aplicatii");

            if(resultSet2.last())
                statement.setInt(4,resultSet2.getInt("id_aplicatie") + 1);
            else
                statement.setInt(4,1);

            statement.executeUpdate();

            connect.close();
            statement.close();
            statement_id.close();
            statement_select.close();

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
            String get = "delete from note_admitere where id_aplicatie = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            statement.execute();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }


    public ExamenAdmitere getAdmitereID(int id_aplicatie)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from note_admitere where id_aplicatie = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_aplicatie);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                return new ExamenAdmitere(resultSet.getString("sala_examen"),resultSet.getDouble("nota_examen"),resultSet.getInt("id_examen"),resultSet.getInt("id_aplicatie"));


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<ExamenAdmitere> getNote_admitere()
    {
        ArrayList<ExamenAdmitere> note_admitere = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from note_admitere";
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
                note_admitere.add(new ExamenAdmitere(resultSet.getString("sala_examen"),resultSet.getDouble("nota_examen"),resultSet.getInt("id_examen"),resultSet.getInt("id_aplicatie")));


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return note_admitere;
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
            ResultSet resultSet1 = statement_select.executeQuery("select * from note_admitere");



            if(resultSet1.last())
                return resultSet1.getInt("id_examen");


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return 0;

    }


}
