/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author John-Robert
 */
public class JSONValueYtj {

    JSONObject yritys;
    String delim;

    public JSONValueYtj(String s, String delim) {
        try {
            this.delim = delim;
            yritys = new JSONObject(s);
        } catch (JSONException e) {
            
        }
    }
public String ifNull(String val){
    if(val.equals("null")){
        return "";
    }
//    if(val==null){
//        return"";
//    }
    return val;
}

    public String getBusinessId() throws JSONException {
        String bId;
        try {
           bId = ifNull(yritys.getJSONArray("results").getJSONObject(0).get("businessId").toString());           
        } catch (JSONException e) {
            return "N/A";
        }
        return bId;
    }
    public String getName() throws JSONException {
        String name;
        try {
           name = ifNull(yritys.getJSONArray("results").getJSONObject(0).getJSONArray("names").getJSONObject(0).get("name").toString()); 
        } catch (JSONException e) {
            return "N/A";
        }
        return name;
    }

    /**
     * Verohallinnon rekisteri
     *
     * @return Tekstin avaimella description verohallinnon rekisterikentästä tai
     * tyhjän kentän
     * @throws JSONException
     */
    public String getTaxAdmReg() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("registeredEntries");
        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("authority").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("register").toString().equals("4")) {
                        if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                            if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                                return ifNull(regEnt.getJSONObject(i).get("description").toString());
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Verohallinnon rekisteri pääliiketoiminta
     *
     * @return Palauttaa pääliiketoiminnan verohallinnon rekisterikentästä tai
     * tyhjän kentän .replaceAll(";", ",")
     * @throws JSONException
     */
    public String getMainBusiness() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("businessLines");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("order").toString().equals("0")) {
                    if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                        if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                            if (regEnt.getJSONObject(i).get("source").toString().equals("2")) {
                                return ifNull(regEnt.getJSONObject(i).get("name").toString());
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Verohallinnon rekisteri pääliiketoiminta
     *
     * @return Palauttaa pääliiketoiminnan verohallinnon rekisterikentästä tai
     * tyhjän kentän
     * @throws JSONException
     */
    public String getMainBusinessCode() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("businessLines");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("order").toString().equals("0")) {
                    if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                        if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                            if (regEnt.getJSONObject(i).get("source").toString().equals("2")) {
                                return ifNull(regEnt.getJSONObject(i).get("code").toString());
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Verohallinnon rekisteri
     *
     * @return Tarkistaa onko yritys verohallinnon rekisterissä ja palauttaa
     * toden jos on(false jos ei)
     *
     * @throws JSONException
     */
    public boolean checkTaxAdmReg() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("registeredEntries");
        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("authority").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("register").toString().equals("4")) {
                        if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                            if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return false;
        }

        return false;
    }

    /**
     * Verohallinnon alv rekisteri
     *
     * @return Tekstin avaimella description verohallinnon alv-rekisterikentästä
     * tai tyhjän kentän
     * @throws JSONException
     */
    public String getVatAdmReg() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("registeredEntries");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("authority").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("register").toString().equals("6")) {
                        if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                            if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                                return ifNull(regEnt.getJSONObject(i).get("description").toString());
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * WWW-osoite
     *
     * @return Palauttaa www-osoitteen tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getWww() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("contactDetails");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("Kotisivun www-osoite")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("value").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Matkapuhelin
     *
     * @return Palauttaa matkapuhelinNumeron tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getMobile() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("contactDetails");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("Matkapuhelin")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("value").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Käyntiosoite (Version 1 voimassa oleva, 2 vanhempia) ja (type "Osoitteen
     * laji, käyntiosoite 1 , postiosoite 2") Postinumero
     *
     * @return Palauttaa Postinumeron tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getPostNoK() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("addresses");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("1")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("postCode").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Käyntiosoite (Version 1 voimassa oleva, 2 vanhempia) ja (type "Osoitteen
     * laji, käyntiosoite 1 , postiosoite 2") Kunta
     *
     * @return Palauttaa Kunnan tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getCityK() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("addresses");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("1")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("city").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Käyntiosoite (Version 1 voimassa oleva, 2 vanhempia) ja (type "Osoitteen
     * laji, käyntiosoite 1 , postiosoite 2") Kunta
     *
     * @return Palauttaa Kunnan tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getStreetK() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("addresses");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("1")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("street").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Postiosoite (Version 1 voimassa oleva, 2 vanhempia) ja (type "Osoitteen
     * laji, käyntiosoite 1 , postiosoite 2") Postinumero
     *
     * @return Palauttaa Postinumeron tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getPostNoP() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("addresses");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("2")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("postCode").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Postiosoite (Version 1 voimassa oleva, 2 vanhempia) ja (type "Osoitteen
     * laji, käyntiosoite 1 , postiosoite 2") Kunta
     *
     * @return Palauttaa Kunnan tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getCityP() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("addresses");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("2")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("city").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    /**
     * Postiosoite (Version 1 voimassa oleva, 2 vanhempia) ja (type "Osoitteen
     * laji, käyntiosoite 1 , postiosoite 2") Katu
     *
     * @return Palauttaa Kunnan tai tyhjän tai tyhjän kentän
     * @throws JSONException
     */
    public String getStreetP() throws JSONException {
        JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("addresses");

        try {
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("version").toString().equals("1")) {
                    if (regEnt.getJSONObject(i).get("type").toString().equals("2")) {
                        if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                            return ifNull(regEnt.getJSONObject(i).get("street").toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            return "N/A";
        }

        return "";
    }

    public String getRegisteredEntries(int arrNo, String Key) throws IOException, Exception {
        JSONArray results = yritys.getJSONArray("results");
        JSONArray addresses = results.getJSONObject(0).getJSONArray("registeredEntries");
        JSONObject address = addresses.getJSONObject(arrNo);
        return ifNull(address.get(Key).toString());
    }

    public String getCompanyForm() throws JSONException {
        try {
            JSONArray regEnt = yritys.getJSONArray("results").getJSONObject(0).getJSONArray("companyForms");
            for (int i = 0; i < regEnt.length(); i++) {
                if (regEnt.getJSONObject(i).get("endDate").toString().equals("null")) {
                    if (regEnt.getJSONObject(i).get("language").toString().equals("FI")) {
                        return ifNull(regEnt.getJSONObject(i).get("name").toString() + " " + regEnt.getJSONObject(i).get("type").toString());
                    }
                }
            }
            return "";
        } catch (JSONException e) {
            return "";
        }
    }

}
