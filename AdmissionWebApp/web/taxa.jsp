<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 01.06.2020
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="service.*, java.sql.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="java.text.DecimalFormat" %>
<html>
<head>
    <title>Admisi taxa</title>
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
<body>
<div style=" margin-top: 40px;   text-align: center;">
    <a href="user.jsp"><input type="button"  value="Inapoi la pagina principala"></a>
    <p style="font-size:30px; font-family: creta;">Introduceti id-ul specializarii pentru care doriti sa vizionati lista de candidati admisi la taxa</p>
    <form style="margin-left: 30px; font-family: creta;">
        Id specializare: <br>
        <input type="text" name="id_specializare1" >
        <br><br>
        <input type="Submit" value="Afiseaza"></a>

    </form>
    <br><br>

        <%
            int i = 0;
            String id = request.getParameter("id_specializare1");
            int ids = 0;
            if(id != null)
                ids = Integer.parseInt(id);
            if(SpecializareService.getInstance().getSpecializareID(ids)!=null )
            {
                ArrayList<Repartitie> list = RepartitieService.getInstance().getAdmisiTaxa(ids);
                if(list.size() != 0)
                {

                %>
    <table border="1" style="margin-left:auto; margin-right:auto;">
        <thead>
        <tr>
            <th>#</th>
            <th>Nume</th>
            <th>Prenume</th>
            <th>Medie admitere</th>
        </tr>
        </thead>
        <tbody>
        <%
             DecimalFormat df2 = new DecimalFormat("#.##");
                for (Repartitie r : list) {
                    if(r != null){
                        df2.setRoundingMode(RoundingMode.DOWN);
        %>
        <tr>
            <td><%=i++%></td>
            <td><%=r.getCandidat().getNume()%></td>
            <td><%=r.getCandidat().getPrenume()%></td>
            <td><%=df2.format(r.getMedieAdmitere())%></td>
        </tr>
        <%
                    }}}
            if(list.size() == 0){
                %>
        <p style="font-family: creta; font-size: 20px">Nu exista date de afisat<p>
            <%
            }}
        %>
        </tbody>
    </table>
</div>
</body>
</html>
