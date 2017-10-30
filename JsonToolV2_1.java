/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import org.json.JSONException;


/**
 *
 * @author JRY
 */
public class JsonToolV2_1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {

        String deli = "|";
//        while (deli.isEmpty()) {
//            deli = JOptionPane.showInputDialog("Määritä erotin");//Delimiter
//           if(deli==null){
//                System.exit(0);
//            } 
//        }

        String locaInYtj = "C:\\Users\\John-Robert\\Google Drive\\Firrafirma 2.0\\Scriptit\\Data\\ytj";
//        while(locaInYtj.isEmpty()){           
//            locaInYtj = JOptionPane.showInputDialog("Liitä tiedostosijainti YTJ datalle, esim." + "\n" + "C:\\Users\\John-Robert\\Documents\\JSONTEST");
//            if(locaInYtj==null){
//                System.exit(0);
//            }            
//        }

        String locaInAs = "C:\\Users\\John-Robert\\Google Drive\\Firrafirma 2.0\\Scriptit\\Data\\asiakastieto";
//        while(locaInAs.isEmpty()){           
//            locaInAs = JOptionPane.showInputDialog("Liitä tiedostosijainti Asiakastieto datalle, esim." + "\n" + "C:\\Users\\John-Robert\\Documents\\JSONTEST");
//            if(locaInAs==null){
//                System.exit(0);
//            }            
//        }

        String locaOut = "C:\\Users\\John-Robert\\Google Drive\\Firrafirma 2.0\\Scriptit";
//        while(locaOut.isEmpty()){
//            locaOut = JOptionPane.showInputDialog("Liitä tiedostosijainti koottavalle tekstitiedostolle, esim." + "\n" + "C:\\Users\\John-Robert\\Documents");
//            if(locaOut==null){
//                System.exit(0);
//            }            
//        }

        int suod;
        boolean suodatus = true;
        suod = JOptionPane.showOptionDialog(null, "Suodatetaanko haku?", "Suodatus", 1, 0, null, null, null);
        if (suod == 2) {
            System.exit(0);
        }
        if (suod == 1) {
            suodatus = false;
        }
        if (suod == 0) {
            suodatus = true;
        }

        ArrayList<String> listaCSV = new ArrayList();
        listaCSV.add("FileName" + deli
                + "Pääliiketoimintakoodi"
                + deli + "Y-tunnus"
                + deli + "Nimi"
                + deli + "Yritysmuoto"
                + deli + "Pääliiketoiminta"
                + deli + "Verohallinto"
                + deli + "Alv-velvollisuus"
                + deli + "KuntaK"
                + deli + "PostinumeroK"
                + deli + "KatuosoiteK"
                + deli + "KuntaP"
                + deli + "PostinumeroP"
                + deli + "KatuosoiteP"
                + deli + "WWW-Osoite"
                + deli + "Matkapuhelin"
                + deli + "Liikevaihto 2016"
                + deli + "Liiketoiminnan tulos 2016"
                + deli + "Henkilöstö 2016"
                + deli + "Liikevoitto 2016"
                + deli + "Omavaraisuus 2016"
                + deli + "Liikevaihto 2015"
                + deli + "Liiketoiminnan tulos 2015"
                + deli + "Henkilöstö 2015"
                + deli + "Liikevoitto 2015"
                + deli + "Omavaraisuus 2015"
                + deli + "Liikevaihto 2014"
                + deli + "Liiketoiminnan tulos 2014"
                + deli + "Henkilöstö 2014"
                + deli + "Liikevoitto 2014"
                + deli + "Omavaraisuus 2014"
                + deli + "Liikevaihto 2013"
                + deli + "Liiketoiminnan tulos 2013"
                + deli + "Henkilöstö 2013"
                + deli + "Liikevoitto 2013"
                + deli + "Omavaraisuus 2013"
                + deli + "Liikevaihto 2012"
                + deli + "Liiketoiminnan tulos 2012"
                + deli + "Henkilöstö 2012"
                + deli + "Liikevoitto 2012"
                + deli + "Omavaraisuus 2012"
        );
        FileIterator iteYtj = new FileIterator();
        FileIterator iteAsiakasT = new FileIterator();

        ArrayList<YFile> ytjLista;
        ArrayList<YFile> asiakasLista;

        ytjLista = iteYtj.iterator(locaInYtj);
        asiakasLista = iteAsiakasT.iterator(locaInAs);

        for (int i = 0; i < ytjLista.size(); i++) {

            if (ytjLista.get(i).getFileName().length() == 14) {

                JSONValueAsiakas a = new JSONValueAsiakas(deli);
                boolean asiakasData = false;
                int r = 0;
                try {

                    for (int l = 0; l < asiakasLista.size(); l++) {
                        r = l;

                        if (ytjLista.get(i).getFileName().equals(asiakasLista.get(l).getFileName())) {
//                            System.out.println(ytjLista.get(i).getFileName() + " " + asiakasLista.get(l).getFileName());

                            if (asiakasLista.get(l).getFileCont().charAt(0) == '{') {
//                                System.out.println(ytjLista.get(i).getFileName() + " " + asiakasLista.get(l).getFileName());
                                a.getMap(asiakasLista.get(l).getFileCont());
                                asiakasData = true;
                            }else{
//                                System.out.println("virhe" + asiakasLista.get(l).getFileName());
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(asiakasLista.get(r).getFileName());
                    System.out.println("Virhea asiakasdata" + e);
                }

                ArrayList<String> listaT = new ArrayList();
                try {

                    JSONValueYtj y = new JSONValueYtj(ytjLista.get(i).getFileCont(), deli);

                    if (suodatus) {
                        if (y.checkTaxAdmReg()) {

                            listaT.add(ytjLista.get(i).getFileName());
                            listaT.add(y.getMainBusinessCode());
                            listaT.add(y.getBusinessId());

                            listaT.add(y.getName());
                            listaT.add(y.getCompanyForm());
                            listaT.add(y.getMainBusiness());
                            listaT.add(y.getTaxAdmReg());
                            listaT.add(y.getVatAdmReg());
                            listaT.add(y.getCityK());
                            listaT.add(y.getPostNoK());
                            listaT.add(y.getStreetK());
                            listaT.add(y.getCityP());
                            listaT.add(y.getPostNoP());
                            listaT.add(y.getStreetP());
                            listaT.add(y.getWww());
                            listaT.add(y.getMobile());
                            if (asiakasData) {
                                try {
                                    listaT.add(a.data.get("2016").toString());
                                } catch (Exception e) {
                                    listaT.add("||||");
                                }
                                try {
                                    listaT.add(a.data.get("2015").toString());
                                } catch (Exception e) {
                                    listaT.add("||||");
                                }
                                try {
                                    listaT.add(a.data.get("2014").toString());
                                } catch (Exception e) {
                                    listaT.add("||||");
                                }
                                try {
                                    listaT.add(a.data.get("2013").toString());
                                } catch (Exception e) {
                                    listaT.add("||||");
                                }
                                try {
                                    listaT.add(a.data.get("2012").toString());
                                } catch (Exception e) {
                                    listaT.add("||||");
                                }

                            }

                            String tiedot = "";
                            for (int t = 0; t < listaT.size(); t++) {
                                if (t == listaT.size() - 1) {
                                    tiedot = tiedot + listaT.get(t);
                                } else {
                                    tiedot = tiedot + listaT.get(t) + deli;
                                }
                            }
                            listaCSV.add(tiedot);
                        }
                    } else {
                        listaT.add(y.getMainBusinessCode());
                        listaT.add(y.getBusinessId());
                        listaT.add(y.getName());
                        listaT.add(y.getCompanyForm());
                        listaT.add(y.getMainBusiness());
                        listaT.add(y.getTaxAdmReg());
                        listaT.add(y.getVatAdmReg());
                        listaT.add(y.getCityK());
                        listaT.add(y.getPostNoK());
                        listaT.add(y.getStreetK());
                        listaT.add(y.getCityP());
                        listaT.add(y.getPostNoP());
                        listaT.add(y.getStreetP());
                        listaT.add(y.getWww());
                        listaT.add(y.getMobile());

                        String tiedot = "";
                        for (int t = 0; t < listaT.size(); t++) {
                            if (t == listaT.size() - 1) {
                                tiedot = tiedot + listaT.get(t);
                            } else {
                                tiedot = tiedot + listaT.get(t) + deli;
                            }
                        }
                        listaCSV.add(tiedot);
                    }

                } catch (JSONException e) {
                    System.out.println("virhe");
                }
            }else{
                System.out.println(ytjLista.get(i).getFileName());
            }
        }
//        System.out.println(lista);
        StringFileHandler fileen = new StringFileHandler();
        if (suodatus) {
            fileen.writeCSV(locaOut + "\\listaus med asiakastieto3.txt", listaCSV);
        } else {
            fileen.writeCSV(locaOut + "\\listausALL.txt", listaCSV);
        }

    }
}
