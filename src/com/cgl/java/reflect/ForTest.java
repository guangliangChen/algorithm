package com.cgl.java.reflect;

/**
 * Created by chenguangliang on 9/18/15.
 */
public class ForTest {
    private int cnt;
    private String str;

    public String getCntCompareResult(int cntParam) {
        if(this.cnt < cntParam) {
            return "paramater is bigger";
        } else if (this.cnt == cntParam) {
            return "equal";
        } else {
            return "paramater is smaller";
        }
    }

    public boolean getStrCompareResult(String strParam) {
        if(this.str.equals(strParam)) {
            return true;
        } else {
            return false;
        }
    }

    private int incrCntBy(int incre) {
        return this.cnt + incre;
    }
}
