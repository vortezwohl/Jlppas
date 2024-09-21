package org.lppa.bean;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;


import java.io.Serializable;

public class Properties implements Serializable {
    private static final long serialVersionUID = 11223013131256310L;
    private Pairing pairing;
    private  Field G1;
    private  Field Zr;

    private Element g;
    private Element x;
    //公钥g^x
    private Element g_x;
    private Element u;

    public Properties(Pairing pairing, Field g1, Field zr, Element g, Element x, Element g_x, Element u) {
        this.pairing = pairing;
        G1 = g1;
        Zr = zr;
        this.g = g;
        this.x = x;
        this.g_x = g_x;
        this.u = u;
    }
}
