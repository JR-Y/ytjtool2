/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

/**
 *
 * @author John-Robert
 */
public class TalousData {

    public String revenue;
    public String profit;
    public String personnel;
    public String operatingMargin;
    public String equityRatio;
    public String deli;

//    public TalousData(){
//        this.revenue = null;
//        this.profit = null;
//        this.personnel = null;
//        this.operatingMargin = null;
//        this.equityRatio = null;
//    }
    public TalousData(String revenues, String profits, String personnel, String operatingProfits, String equityRatios, String deli) {
        this.revenue = revenues;
        this.profit = profits;
        this.personnel = personnel;
        this.operatingMargin = operatingProfits;
        this.equityRatio = equityRatios;
        this.deli = deli;
//        System.out.println(this.toString());
    }

    public String ifNull(String val) {
        if (val.equals("null")) {
            return "";
        }
        if (val.equals("0")) {
            return "";
        }
//    if(val==null){
//        return"";
//    }
        return val;
    }

    public String getRevenue() {
        return ifNull(this.revenue);
    }

    public String getProfit() {
        return ifNull(this.profit);
    }

    public String getPersonnel() {
        return ifNull(this.personnel);
    }

    public String getOperatingMargin() {
        return ifNull(this.operatingMargin);
    }

    public String getEquityRatio() {
        return ifNull(this.equityRatio);
    }

    @Override
    public String toString() {
        return ifNull(this.revenue) + deli + ifNull(this.profit) + deli + ifNull(this.personnel) + deli + ifNull(this.operatingMargin) + deli + ifNull(this.equityRatio);
    }
}
