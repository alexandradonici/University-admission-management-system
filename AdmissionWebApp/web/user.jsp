<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 22.05.2020
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="service.*, java.sql.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Candidat</title>
    <style>
        body  {
            background-image: url("bg.jpg");
            background-repeat: no-repeat;
            background-position: center;
            background-attachment: fixed;
            background-size: cover;

            margin:0;
            padding:0;
        }
        @font-face {
            font-family: creta;
            src: url("Creta_TRIAL.otf");
        }
        * {
            font-family:"Comic Sans MS";
            color: white;
        }
        input
        {
            color: black;
        }
    </style>
</head>
<body style="background-color: darkgrey"><a href="index.jsp"><input type="button" value="Navigati catre pagina de administrator"></a>
<div style = "display: flex;">
<div style=" width: 50%; margin-top: 35px; text-align: center">
    <p style="font-size:30px; margin-left: 20px; font-family: creta;"> Introduceti o noua aplicatie: </p>
    <form method="post" action="user.jsp" style=" font-family: creta;font-size:15px">
        Nume:<br>
        <input type="text" name="nume_candidat" >
        <br><br>
        Prenume:<br>
        <input type="text" name="prenume_candidat" >
        <br><br>
        Id specializare:<br>
        <input type="text" name="id_specializare" >
        <br><br>
        Nota bacalaureat - proba 1:<br>
        <input type="text" name="notaP1" >
        <br><br>
        Nota bacalaureat - proba 2:<br>
        <input type="text" name="notaP2" >
        <br><br>
        Nota bacalaureat - proba 3:<br>
        <input type="text" name="notaP3" >
        <br><br>
        <br>
        <input type="submit" value="Adauga">
    </form>
    <br><br>

    <%
        String nume_candidat=request.getParameter("nume_candidat");
        String prenume_candidat=request.getParameter("prenume_candidat");
        String id_specializare = request.getParameter("id_specializare");

        int id_s = 0;
        if(id_specializare != null)
        {
            id_s = Integer.parseInt(id_specializare);
        }

        String notaP1 = request.getParameter("notaP1");
        double n1 = 0;
        if(notaP1 != null)
        {
            n1 = Double.parseDouble(notaP1);
        }

        String notaP2 = request.getParameter("notaP2");
        double n2 = 0;
        if(notaP2 != null)
        {
            n2 = Double.parseDouble(notaP2);
        }

        String notaP3 = request.getParameter("notaP3");
        double n3 = 0;
        if(notaP3 != null)
        {
            n3 = Double.parseDouble(notaP3);
        }

        BacalaureatService bacalaureatService = BacalaureatService.getInstance();
        ExamenService examenService = ExamenService.getInstance();


        if(nume_candidat != null && prenume_candidat != null)
            CandidatService.getInstance().add(new Candidat(nume_candidat, prenume_candidat));

        if(n1 != 0 && n2!=0 && n3!=0) {
            bacalaureatService.add(new Bacalaureat(n1, n2, n3));
            examenService.add(new ExamenAdmitere());
        }


        Specializare specializare = SpecializareService.getInstance().getSpecializareID(id_s);
        Bacalaureat bacalaureat = bacalaureatService.getBacID(bacalaureatService.getLastID());
        ExamenAdmitere examenAdmitere = examenService.getAdmitereID(examenService.getLastID());

        if(specializare != null && bacalaureat != null && examenAdmitere != null)
            RepartitieService.getInstance().add(new Repartitie(CandidatService.getInstance().getCandidatNume(nume_candidat, prenume_candidat), specializare, bacalaureat, examenAdmitere));

    %>
    </div>
    <div style=" width: 50%; margin-top: 190px;text-align: center">
    <p style="font-size:30px; font-family: creta;">Alege una dintre urmatoarele optiuni:</p>

    <div><a href ="buget.jsp"><input type="button" value="Candidati admisi la buget la specializarea aleasa"></a></div><br>
    <div><a href ="taxa.jsp"><input type="button" value="Candidati admisi la taxa la specializarea aleasa"></a></div><br>
    <div><a href ="respinsi.jsp"><input type="button" value="Candidati respinsi la specializarea aleasa"></a></div><br>
    </div>
</div>
</div>
</body>
</html>
