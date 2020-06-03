<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 18.05.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="service.*, java.sql.*" %>
<%@ page import="model.*" %>

<html>
  <head>
    <title>Facultate</title>
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
  <body style="background-color: darkgrey">
  <a href="user.jsp"><input type="button" value="Navigati catre pagina de candidat"></a>
  <div style="display: flex; ">
  <div style="width: 33%; margin-left: 20px">
  <div >
    <br>
  <p style="font-size:30px; margin-left: 20px; font-family: creta;"> Introduceti o noua facultate: </p>
  <p style="margin-left: 20px; margin-right: 50px; font-family: creta;">Observatie: pentru facultatile fara admitere introduceti 0 in campul Tip facultate si 1 pentru cele cu admitere!</p>
  <form method="post" action="index.jsp" style="margin-left: 30px; margin-bottom: 30px; font-family: creta;">
    Nume:<br>
    <input type="text" name="nume" >
    <br><br>
    Tip facultate:<br>
    <input type="text" name="tip_facultate" >
    <br><br>
    <input type="submit" value="Adauga">
  </form>
  </div>
    <br>
<%
  String nume=request.getParameter("nume");
  String tip_facultate =request.getParameter("tip_facultate");
  int t = 0;
  if(tip_facultate!= null)
  {
    t = Integer.parseInt(tip_facultate);
  }

  if(nume != null)
  FacultateService.getInstance().add(new Facultate(nume,t));
  %>
    <div>
      <p style="margin-left:20px; font-size:30px; font-family: creta;">Actualizeaza nota admitere:</p>
      <form style="margin-left: 30px; font-family: creta;">
        Id aplicatie: <br>
        <input type="text" name="id_aplicatie1" >
        <br><br>
        Nota admitere: <br>
        <input type="text" name="nota_admitere1" >
        <br><br>
        <input type="submit" value="Actualizeaza">
      </form>
      <br>
    </div>
  </div>
    <%
      String id_aplicatie = request.getParameter("id_aplicatie1");
      int id_a = 0;
      if(id_aplicatie != null)
        id_a = Integer.parseInt(id_aplicatie);

      String nota_admitere = request.getParameter("nota_admitere1");
      double admitere = 0.0;
      if(nota_admitere != null)
        admitere = Double.parseDouble(nota_admitere);

      if(RepartitieService.getInstance().getRepartitieID(id_a) != null)
        RepartitieService.getInstance().updateMedieAdmitere(id_a, admitere);



    %>


    <div style=" width: 67%; display: flex">
      <div style="width: 50%; ">
      <div>
      <p style="font-size:30px; font-family: creta;">Stergeti un anumit candidat:</p>
      <p style=" font-family: creta;margin-left: 30px;">Introduceti id-ul candidatului:</p>
      <form style="margin-left: 30px;font-family: creta;">
        <input type="text" name="numeCS" >
        <br><br>
        <input type="submit" value="Sterge">
      </form>
        <br>
    </div>

    <%
      String id_candidat_sters = request.getParameter("numeCS");
      int candidat_sters = 0;
      if(id_candidat_sters != null)
        candidat_sters = Integer.parseInt(id_candidat_sters);

      CandidatService.getInstance().remove(candidat_sters);

    %>
        <div>
          <p style="font-size:30px; font-family: creta;">Stergeti o anumita aplicatie:</p>
          <p style=" font-family: creta;margin-left: 30px;">Introduceti id-ul aplicatiei:</p>
          <form style="margin-left: 30px; font-family: creta;">
            <input type="text" name="id_aplicatie_stearsa" >
            <br><br>
            <input type="submit" value="Sterge">
          </form>
        </div>

      <%
        String id_aplicatie_stearsa = request.getParameter("id_aplicatie_stearsa");
        int aplic_stearsa = 0;
        if(id_aplicatie_stearsa != null)
          aplic_stearsa = Integer.parseInt(id_aplicatie_stearsa);

        RepartitieService.getInstance().remove(aplic_stearsa);

      %>
        <br>
        <div>
          <p style="font-size:30px; font-family: creta;">Actualizeaza sala admitere:</p>
          <form style="margin-left: 30px; font-family: creta;">
            Id aplicatie: <br>
            <input type="text" name="id_aplicatie2" >
            <br><br>
            Sala admitere: <br>
            <input type="text" name="sala_admitere" >
            <br><br>
            <input type="submit" value="Actualizeaza">
          </form>
        </div>

      </div>
      <%
        String id_aplicatie2 = request.getParameter("id_aplicatie2");
        int id_a2 = 0;
        if(id_aplicatie2 != null)
          id_a2 = Integer.parseInt(id_aplicatie2);

        String sala_admitere = request.getParameter("sala_admitere");

        if(RepartitieService.getInstance().getRepartitieID(id_a2) != null)
          RepartitieService.getInstance().updateSalaAdmitere(id_a2, sala_admitere);

      %>



      <div style="width: 50%">
        <div>
          <br>
          <p style="font-size:30px; margin-left: 20px; font-family: creta;"> Introduceti o noua specializare: </p>
          <form method="post" action="index.jsp" style="margin-left: 30px; font-family: creta;">
            Nume:<br>
            <input type="text" name="numeS" >
            <br><br>
            Id facultate:<br>
            <input type="text" name="id_facultate" >
            <br><br>
            Locuri buget:<br>
            <input type="text" name="locuri_buget" >
            <br><br>
            Locuri taxa:<br>
            <input type="text" name="locuri_taxa" >
            <br>
            <br>
            <input type="submit" value="Adauga">
          </form>
        </div>
        <%
          String numeS=request.getParameter("numeS");
          String id_facultate = request.getParameter("id_facultate");

          int id_f = 0;
          if(id_facultate != null)
          {
            id_f = Integer.parseInt(id_facultate);
          }
          String locuri_buget = request.getParameter("locuri_buget");
          int l_buget = 0;
          if(locuri_buget != null)
          {
            l_buget = Integer.parseInt(locuri_buget);
          }

          String locuri_taxa =request.getParameter("locuri_taxa");
          int l_taxa = 0;
          if(locuri_taxa != null)
          {
            l_taxa = Integer.parseInt(locuri_taxa);
          }


          Facultate f = FacultateService.getInstance().getFacultateID(id_f);
          if(f!=null)
            SpecializareService.getInstance().add(new Specializare(numeS, f, l_buget, l_taxa));
        %>
        <br>
        <div>
          <p style="font-size:30px; font-family: creta;">Stergeti o anumita specializare:</p>
          <p style=" font-family: creta;margin-left: 30px;">Introduceti id-ul specializarii:</p>
          <form style="margin-left: 30px; font-family: creta;">
            <input type="text" name="id_spec" >
            <br><br>
            <input type="submit" value="Sterge">
          </form>
        </div>

      </div>

    <%
      String id_specializare_stearsa = request.getParameter("id_spec");
      int spec_stearsa = 0;
      if(id_specializare_stearsa != null)
        spec_stearsa = Integer.parseInt(id_specializare_stearsa);

      SpecializareService.getInstance().remove(spec_stearsa);

    %>
  </div>
  </div>
  </body>
</html>
