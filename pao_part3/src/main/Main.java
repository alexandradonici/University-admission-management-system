package main;

import model.*;

import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
public static void main(String[] args) {

    FacultateService facultatiService = FacultateService.getInstance();
    CandidatService candidatService = CandidatService.getInstance();
    SpecializareService specializareService = SpecializareService.getInstance();
    RepartitieService repartitieService = RepartitieService.getInstance();
    BacalaureatService bacalaureatService = BacalaureatService.getInstance();
    ExamenService examenService = ExamenService.getInstance();



    int optiune;

    do
    {
        System.out.println("\nAlege urmatoarea optiune:\n" +
                "0. Exit\n" +
                "1. Adaugarea unui candidat nou\n" +
                "2. Adaugarea unei facultati cu admitere\n" +
                "3. Adaugarea unei facultati fara admitere\n" +
                "4. Adaugarea unei specializari noi\n" +
                "5. Adaugarea unei optiuni noi\n"+
                "6. Afisare candidati\n" +
                "7. Stergere candidat dupa id\n"+
                "8. Afisare facultati\n" +
                "9. Afisare facultati cu admitere\n" +
                "10. Afisare facultati fara admitere\n"+
                "11. Stergere facultate dupa id\n"+
                "12. Afisare specializari\n" +
                "13. Afisare specializari dupa facultate\n" +
                "14. Stergere specializare dupa id\n"+
                "15. Stergere optiune dupa ID\n" +
                "16. Afisare candidati inscrisi la o anumita specializare\n" +
                "17. Afisare candidati inscrisi la o anumita specializare in ordine alfabetica\n" +
                "18. Afisare candidati inscrisi la o anumita specializare in ordine descrescatoare a mediilor\n" +
                "19. Afisare candidati admisi la buget la o anumita specializare\n" +
                "20. Afisare candidati admisi la taxa la o anumita specializare\n" +
                "21. Afisare candidati respinsi de la o anumita specializare\n" +
                "22. Afisare optiune ale unui candidat\n" +
                "23. Afisare optiuni\n" +
                "24. Update medie\n" +
                "25. Update nume candidat\n" +
                "26. Update nume facultate\n" +
                "27. Update nume specializare\n");

        Scanner in = new Scanner(System.in);
            optiune = in.nextInt();
            in.nextLine();

            switch (optiune)
            {
                case 1:
                    System.out.println("Adauga un nou candidat:");
                    System.out.println("Nume: ");
                    String numeC = in.nextLine();
                    System.out.println("Prenume: ");
                    String prenumeC = in.nextLine();
                    candidatService.add(new Candidat(numeC, prenumeC));
                    break;

                case 2:
                    System.out.println("Adauga o  facultate cu admitere:");
                    System.out.println("Nume: ");
                    String nume = in.nextLine();
                    facultatiService.add(new Facultate(nume,1));
                    break;

                case 3:
                    System.out.println("Adauga o  facultate fara admitere:");
                    System.out.println("Nume: ");
                    String numeF = in.nextLine();
                    facultatiService.add(new Facultate(numeF,0));
                    break;

                case 4:
                    System.out.println("Adauga o noua specializare:");
                    System.out.println("Nume: ");
                    String numeS = in.nextLine();
                    System.out.println("Id facultate: ");
                    int facultateId = in.nextInt();
                    System.out.println("Numar locuri buget: ");
                    int locuriBuget = in.nextInt();
                    System.out.println("Numar locuri taxa: ");
                    int locuriTaxa = in.nextInt();
                    specializareService.add(new Specializare(numeS, facultatiService.getFacultateID(facultateId), locuriBuget, locuriTaxa));
                    break;

                case 5:
                    System.out.println("Adauga o noua optiune:");
                    System.out.println("Nume: ");
                    String nume_candidat = in.nextLine();
                    System.out.println("Prenume: ");
                    String prenume_candidat = in.nextLine();

                    Candidat c = new Candidat(nume_candidat, prenume_candidat);
                    candidatService.add(c);

                    System.out.println("Id specializare: ");
                    int specializareId = in.nextInt();

                    System.out.println("Nota bac P1: ");
                    double notaP1 = in.nextDouble();
                    System.out.println("Nota bac P2: ");
                    double notaP2 = in.nextDouble();
                    System.out.println("Nota bac P3: ");
                    double notaP3 = in.nextDouble();
                    System.out.println("Sala examen: ");
                    in.nextLine();
                    String salaExamen = in.nextLine();

                    Bacalaureat b = new Bacalaureat(notaP1, notaP2, notaP3);
                    bacalaureatService.add(b);
                    ExamenAdmitere e = new ExamenAdmitere(salaExamen, 0);
                    examenService.add(e);
                    repartitieService.add(new Repartitie(candidatService.getCandidatNume(nume_candidat, prenume_candidat),specializareService.getSpecializareID(specializareId),bacalaureatService.getBacID(bacalaureatService.getLastID()),examenService.getAdmitereID(examenService.getLastID())));
                    break;

                case 6:
                    System.out.println("Afisare candidati:");
                    candidatService.AfisCandidati();
                    break;

                case 7:
                    System.out.println("Stergere candidat dupa id: ");
                    System.out.println("Id candidat: ");
                    int candidatId1 = in.nextInt();
                    candidatService.remove(candidatId1);
                    break;

                case 8:
                    System.out.println("Afisare facultati:");
                    ArrayList<Facultate> facultati = facultatiService.getFacultati();

                    if(facultati.size() == 0)
                        System.out.println("Nu exista facultati adaugate!");

                    for(Facultate facultate : facultati)
                        System.out.println(facultate);
                    System.out.println();
                    break;

                case 11:
                    System.out.println("Sterge o facultate dupa id: ");
                    int facultateId1 = in.nextInt();
                    facultatiService.remove(facultateId1);
                    break;

                case 12:
                    System.out.println("Afisare specializari");
                    specializareService.AfisSpecializari();
                    break;

                case 13:
                    System.out.println("Afisare specializari ale unei anumite facultati");
                    System.out.println("Id facultate");
                    int facultateId2 = in.nextInt();
                    ArrayList<Specializare> specializari = specializareService.getSpecializareFacultate(facultatiService.getFacultateID(facultateId2));

                    if(specializari.size() == 0)
                        System.out.println("Nu exista specializari pentru aceasta facultate!");

                    for(Specializare specializare : specializari)
                        System.out.println(specializare);
                    break;

                case 14:
                    System.out.println("Sterge o specializare dupa id: ");
                    int specializareId1 = in.nextInt();
                    specializareService.remove(specializareId1);
                    break;

                case 15:
                    System.out.println("Sterge o optiune dupa id: ");
                    int optiuneId = in.nextInt();
                    repartitieService.remove(optiuneId);
                    break;

                case 16:
                    System.out.println("Afisare candidati inscrisi la o anumita specializare: " );
                    System.out.println("Id specializare");
                    int specializareId2 = in.nextInt();
                    ArrayList<Repartitie> repartizare= repartitieService.getCandidatiPeSpecializari(specializareId2);

                    if(repartizare.size() == 0)
                        System.out.println("Nu exista candidati inscrisi la aceasta specializare!");

                    for(Repartitie repartitie : repartizare)
                        System.out.println(repartitie);
                    break;


                case 17:
                    System.out.println("Afisare candidati inscrisi la o anumita specializare in ordine alfabetica: " );
                    System.out.println("Id specializare");
                    int specializareId3 = in.nextInt();
                    ArrayList<Repartitie> repartizare2= repartitieService.getCandidatiAlfabetic(specializareId3);

                    if(repartizare2.size() == 0)
                        System.out.println("Nu exista candidati inscrisi la aceasta specializare!");

                    for(Repartitie repartitie : repartizare2)
                        System.out.println(repartitie);
                    break;

                case 18:
                    System.out.println("Afisare candidati inscrisi la o anumita specializare in ordine descrescatoare a mediilor: " );
                    System.out.println("Id specializare");
                    int specializareId4 = in.nextInt();
                    ArrayList<Repartitie> repartizare3= repartitieService.getCandidatiMedieDescrescator(specializareId4);

                    if(repartizare3.size() == 0)
                        System.out.println("Nu exista candidati inscrisi la aceasta specializare!");

                    for(Repartitie repartitie : repartizare3)
                        System.out.println(repartitie);
                    break;

                case 19:
                    System.out.println("Afisare candidati admisi la buget la o anumita specializare: " );
                    System.out.println("Id specializare");
                    int specializareId5 = in.nextInt();
                    ArrayList<Repartitie> repartizare4= repartitieService.getAdmisiBuget(specializareId5);

                    if(repartizare4.size() == 0)
                        System.out.println("Nu exista candidati admisi la buget!");

                    for(Repartitie repartitie : repartizare4)
                        System.out.println(repartitie);
                    break;

                case 20:
                    System.out.println("Afisare candidati admisi la taxa la o anumita specializare: " );
                    System.out.println("Id specializare");
                    int specializareId6 = in.nextInt();
                    ArrayList<Repartitie> repartizare5= repartitieService.getAdmisiTaxa(specializareId6);

                    if(repartizare5.size() == 0)
                        System.out.println("Nu exista candidati admisi la taxa!");

                    for(Repartitie repartitie : repartizare5)
                        System.out.println(repartitie);
                    break;

                case 21:
                    System.out.println("Afisare candidati respinsi la o anumita specializare: " );
                    System.out.println("Id specializare");
                    int specializareId7 = in.nextInt();
                    ArrayList<Repartitie> repartizare6= repartitieService.getRespinsi(specializareId7);

                    if(repartizare6.size() == 0)
                        System.out.println("Nu exista candidati respinsi!");

                    for(Repartitie repartitie : repartizare6)
                        System.out.println(repartitie);
                    break;

                case 22:
                    System.out.println("Afisare optiuni ale unui candidat: " );
                    System.out.println("Id candidat");
                    int candidatId2 = in.nextInt();
                    ArrayList<Repartitie> repartizare7= repartitieService.getOptiuniCandidat(candidatId2);

                    if(repartizare7.size() == 0)
                        System.out.println("Nu exista optiuni de afisat!");

                    for(Repartitie repartitie : repartizare7)
                        System.out.println(repartitie);
                    break;

                case 23:
                    System.out.println("Afisare optiuni: " );
                    ArrayList<Repartitie> repartizare8= repartitieService.getRepartizare();

                    if(repartizare8.size() == 0)
                        System.out.println("Nu exista optiuni de afisat!");

                    for(Repartitie repartitie : repartizare8)
                        System.out.println(repartitie);
                    break;

                case 24:
                    System.out.println("Update medie admitere");
                    System.out.println("Id optiune: ");
                    int optiuneId1 = in.nextInt();
                    System.out.println("Nota admitere:");
                    double notaAdmitere = in.nextDouble();
                    repartitieService.updateMedieAdmitere(optiuneId1, notaAdmitere);
                    break;

                case 25:
                     System.out.println("Update nume si prenume candidat");
                     System.out.println("Id candidat: ");
                     int candidatId3 = in.nextInt();
                     in.nextLine();

                     System.out.println("Nume candidat: ");
                     String numec = in.nextLine();
                     System.out.println("Prenume candidat: ");
                     String prenumec = in.nextLine();
                     candidatService.updateNume(candidatId3, numec, prenumec);
                     break;

                case 26:
                      System.out.println("Update nume facultate");
                      System.out.println("Id facultate: ");
                      int facultateId8 = in.nextInt();
                      System.out.println("Nume facultate: ");
                      String numef = in.nextLine();
                      facultatiService.updateNume(facultateId8, numef);
                      break;

                case 27:
                      System.out.println("Update nume specializare");
                      System.out.println("Id specializare: ");
                      int specializareId8 = in.nextInt();
                      System.out.println("Nume specializare: ");
                      String numes = in.nextLine();
                      facultatiService.updateNume(specializareId8, numes);
                      break;



            }

    }
    while(optiune!=0);


}
}
