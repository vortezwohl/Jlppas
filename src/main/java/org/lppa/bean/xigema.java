package org.lppa.bean;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.util.ArrayList;

public class xigema {
    public Element xi;
    public void xigen(ArrayList<Element> tags, ArrayList<Element> chal, Field G1){

        Element xigema = G1.newOneElement();

        for (int i = 0; i < chal.size(); i++) {
            Element temp = tags.get(i).duplicate().powZn(chal.get(i));
            xigema = xigema.duplicate().mul(temp);
        }
        this.xi = xigema;

    }
}
