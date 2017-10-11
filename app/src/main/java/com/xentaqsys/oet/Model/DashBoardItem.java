package com.xentaqsys.oet.Model;

/**
 * Created by Swatantra.Singh on 9/6/17.
 */

public class DashBoardItem {

    private String value1,value2,value3;


    public DashBoardItem(){

    }


    public DashBoardItem(String value1, String value2, String value3) {


        this.value1=value1;
        this.value2=value2;
        this.value3 = value3;
    }



    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }



}
