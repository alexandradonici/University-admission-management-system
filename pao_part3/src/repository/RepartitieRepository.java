package repository;

import model.*;
import service.BacalaureatService;
import service.ExamenService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RepartitieRepository
{

    private static final String url = "jdbc:mysql://localhost:3306/admitere_database";
    private static final String user = "root";
    private static final String passwrod = "";

    private CandidatRepository candidatRepository = CandidatRepository.getInstance();
    private SpecializareRepository specializareRepository = SpecializareRepository.getInstance();
    private BacalaureatRepository bacalaureatRepository = BacalaureatRepository.getInstance();
    private ExamenRepository examenRepository = ExamenRepository.getInstance();

    private static RepartitieRepository instance = null;


    public RepartitieRepository(){}

    public static RepartitieRepository getInstance()
    {
        if(instance == null)
            instance = new RepartitieRepository();
        return instance;
    }

    public void add(Repartitie repartitie)
    {
        Connection connect = null;
        PreparedStatement statement = null;
        Statement statement_select;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "INSERT INTO aplicatii (id_aplicatie, id_candidat, id_specializare, id_bac, id_admitere, medie_admitere) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connect.prepareStatement(sql);

            statement_select = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet1 = statement_select.executeQuery("select * from aplicatii");

            if( resultSet1.last())
                statement.setInt(1, resultSet1.getInt("id_aplicatie") + 1);
            else
                statement.setInt(1, 1);

            statement.setInt(2,repartitie.getCandidat().getId_candidat());
            statement.setInt(3, repartitie.getSpecializare().getId_specializare());
            statement.setInt(4, repartitie.getBac().getId_bacalaureat());
            statement.setInt(5, repartitie.getExamen().getId_examen());
            statement.setDouble(6, repartitie.getMedieAdmitere());

            statement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Repartitie getRepartitieID(int id_repartitie)
    {

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from aplicatii where id_aplicatie = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_repartitie);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                return new Repartitie(resultSet.getInt("id_aplicatie"),
                        candidatRepository.getCandidatID(resultSet.getInt("id_candidat")),
                        specializareRepository.getSpecializareID(resultSet.getInt("id_specializare")),
                        bacalaureatRepository.getBacID(resultSet.getInt("id_bac")),
                        examenRepository.getAdmitereID(resultSet.getInt("id_admitere")));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public void remove(int id)
    {
        Connection connect = null;
        PreparedStatement statement = null;


        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "delete from aplicatii where id_aplicatie = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id);
            statement.execute();

            BacalaureatService.getInstance().remove(id);
            ExamenService.getInstance().remove(id);


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeCandidat(int id_candidat)
    {
        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String get = "delete from aplicatie where id_candidat = ?";
            statement = connect.prepareStatement(get);
            statement.setInt(1, id_candidat);
            statement.execute();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Repartitie> getCandidatiPeSpecializari(int id_specializare)
    {
        ArrayList<Repartitie> r = new ArrayList<>();

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from aplicatii where id_specializare = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_specializare);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                r.add(new Repartitie(resultSet.getInt("id_aplicatie"),
                        candidatRepository.getCandidatID(resultSet.getInt("id_candidat")),
                        specializareRepository.getSpecializareID(resultSet.getInt("id_specializare")),
                        bacalaureatRepository.getBacID(resultSet.getInt("id_bac")),
                        examenRepository.getAdmitereID(resultSet.getInt("id_admitere"))));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return r;
    }

    public static Comparator<Repartitie> NumeComparator = new Comparator<Repartitie>()
    {

        public int compare(Repartitie r1, Repartitie r2)
        {
            String Candidat1 = r1.getCandidat().getNume().toUpperCase();
            String Candidat2 = r2.getCandidat().getNume().toUpperCase();

            return Candidat1.compareTo(Candidat2);

        }
    };

    public static Comparator<Repartitie> MedieComparator = new Comparator<Repartitie>()
    {

        public int compare(Repartitie r1, Repartitie r2)
        {
            double Candidat1 = r1.getMedieAdmitere();
            double Candidat2 = r2.getMedieAdmitere();

            return Double.compare(Candidat2, Candidat1);

        }
    };


    public ArrayList<Repartitie> getCandidatiMedieDescrescator(int id_specializare)
    {
        ArrayList<Repartitie> r = new ArrayList<>();

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from aplicatii where id_specializare = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_specializare);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                r.add(new Repartitie(resultSet.getInt("id_aplicatie"),
                        candidatRepository.getCandidatID(resultSet.getInt("id_candidat")),
                        specializareRepository.getSpecializareID(resultSet.getInt("id_specializare")),
                        bacalaureatRepository.getBacID(resultSet.getInt("id_bac")),
                        examenRepository.getAdmitereID(resultSet.getInt("id_admitere"))));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        Collections.sort(r, MedieComparator);
        return r;
    }

    public ArrayList<Repartitie> getCandidatiAlfabetic(int id_specializare)
    {
        ArrayList<Repartitie> r = new ArrayList<>();

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from aplicatii where id_specializare = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_specializare);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                r.add(new Repartitie(resultSet.getInt("id_aplicatie"),
                        candidatRepository.getCandidatID(resultSet.getInt("id_candidat")),
                        specializareRepository.getSpecializareID(resultSet.getInt("id_specializare")),
                        bacalaureatRepository.getBacID(resultSet.getInt("id_bac")),
                        examenRepository.getAdmitereID(resultSet.getInt("id_admitere"))));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        Collections.sort(r, NumeComparator);
        return r;
    }

    public ArrayList<Repartitie> getAdmisiBuget(int id_specializare)
    {
        ArrayList<Repartitie> candidatiAdmisiBuget = new ArrayList<Repartitie>();
        ArrayList<Repartitie> candidatiPeSpecializari = getCandidatiMedieDescrescator(id_specializare);

        Specializare specializare = specializareRepository.getSpecializareID(id_specializare);

        int locuriBuget = specializare.getLocuri_buget();
        if(candidatiPeSpecializari.size()<locuriBuget)
            locuriBuget=candidatiPeSpecializari.size();

        for(int i=0; i<locuriBuget; i++)
            candidatiAdmisiBuget.add(candidatiPeSpecializari.get(i));

        return candidatiAdmisiBuget;

    }

    public ArrayList<Repartitie> getAdmisiTaxa(int  id_specializare)
    {
        ArrayList<Repartitie> candidatiAdmisiTaxa = new ArrayList<Repartitie>();
        ArrayList<Repartitie> candidatiPeSpecializari = getCandidatiMedieDescrescator(id_specializare);

        Specializare specializare = specializareRepository.getSpecializareID(id_specializare);

        int locuriBuget = specializare.getLocuri_buget();
        int locuriTaxa = specializare.getLocuri_taxa();
        int limita=locuriBuget+locuriTaxa;

        if(locuriBuget > candidatiPeSpecializari.size())
            return candidatiAdmisiTaxa;

        if(limita > candidatiPeSpecializari.size())
            limita = candidatiPeSpecializari.size();

        for(int i = locuriBuget; i < limita; i++)
            candidatiAdmisiTaxa.add(candidatiPeSpecializari.get(i));

        return candidatiAdmisiTaxa;

    }

    public ArrayList<Repartitie> getRespinsi(int id_specializare)
    {
        ArrayList<Repartitie> candidatiRespinsi = new ArrayList<Repartitie>();
        ArrayList<Repartitie> candidatiPeSpecializari = getCandidatiMedieDescrescator(id_specializare);

        Specializare specializare = specializareRepository.getSpecializareID(id_specializare);

        int locuriBuget = specializare.getLocuri_buget();
        int locuriTaxa = specializare.getLocuri_taxa();
        int limita=locuriBuget+locuriTaxa;

        if(limita > candidatiPeSpecializari.size())
            return candidatiRespinsi;

        for(int i = limita; i < candidatiPeSpecializari.size(); i++)
            candidatiRespinsi.add(candidatiPeSpecializari.get(i));

        return candidatiRespinsi;

    }

    public ArrayList<Repartitie> getOptiuniCandidat(int id_candidat)
    {
        ArrayList<Repartitie> r = new ArrayList<>();

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "select * from aplicatii where id_candidat = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_candidat);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                r.add(new Repartitie(resultSet.getInt("id_aplicatie"),
                        candidatRepository.getCandidatID(resultSet.getInt("id_candidat")),
                        specializareRepository.getSpecializareID(resultSet.getInt("id_specializare")),
                        bacalaureatRepository.getBacID(resultSet.getInt("id_bac")),
                        examenRepository.getAdmitereID(resultSet.getInt("id_admitere"))));

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return r;
    }


    public void AfisRepartizare()
    {
        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from aplicatii");

            while(resultSet.next()) System.out.println("Nume: " + candidatRepository.getCandidatID(resultSet.getInt("id_candidat")).getNume() +
                    "\nPrenume: "+ candidatRepository.getCandidatID(resultSet.getInt("id_candidat")).getNume() +
                    "\nSpecializare: "+specializareRepository.getSpecializareID(resultSet.getInt("id_Specializare")) +
                    "\nMedie admitere: " + resultSet.getDouble("medie_admitere") + "\n\n");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }



    public void updateMedieAdmitere(int id_aplicatie, double notaExamen)
    {

        Connection connect = null;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "UPDATE note_admitere SET nota_examen = ?  WHERE id_aplicatie = ?";
            statement = connect.prepareStatement(sql);
            statement.setDouble(1, notaExamen);
            statement.setInt(2, id_aplicatie);
            statement.executeUpdate();


            Repartitie repartitie = getRepartitieID(id_aplicatie);

            int tip_facultate = repartitie.getSpecializare().getFacultate().getTip_facultate();
            double ma;

            if(tip_facultate == 1)
                 ma = repartitie.getExamen().getNotaExamen()*80/100 + repartitie.getBac().getMedieBac()*20/100;
            else
                ma = repartitie.getBac().getMedieBac();

            String sql1 = "UPDATE aplicatii SET medie_admitere = ?  WHERE id_aplicatie = ?";
            statement1 = connect.prepareStatement(sql1);
            statement1.setDouble(1, ma);
            statement1.setInt(2, id_aplicatie);
            statement1.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }


    }
    public void updateSalaAdmitere(int id_aplicatie, String salaExamen)
    {

        Connection connect = null;
        PreparedStatement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            String sql = "UPDATE note_admitere SET sala_examen = ?  WHERE id_aplicatie = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, salaExamen);
            statement.setInt(2, id_aplicatie);
            statement.executeUpdate();



        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }


    }

    public ArrayList<Repartitie> getRepartizare()
    {
        ArrayList<Repartitie> r = new ArrayList<>();

        Connection connect = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, passwrod);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from aplicatii");

            while(resultSet.next())
                r.add(new Repartitie(resultSet.getInt("id_aplicatie"),
                        candidatRepository.getCandidatID(resultSet.getInt("id_candidat")),
                        specializareRepository.getSpecializareID(resultSet.getInt("id_specializare")),
                        bacalaureatRepository.getBacID(resultSet.getInt("id_bac")),
                        examenRepository.getAdmitereID(resultSet.getInt("id_admitere"))));
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return r;
    }

}
