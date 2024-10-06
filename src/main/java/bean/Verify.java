package bean;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;


import java.util.ArrayList;

public class Verify {
    public Element rl;

    public void verifycompute(ArrayList<Element> hashs, ArrayList<Element> chal, Element u, Field G1, Element mu){
        it.unisa.dia.gas.jpbc.Element rll = G1.newOneElement();

        for (int i = 0; i < chal.size(); i++) {
            it.unisa.dia.gas.jpbc.Element temp = hashs.get(i).duplicate().powZn(chal.get(i));
            rll = rll.duplicate().mul(temp);
        }
        this.rl = rll.duplicate().mul(u.duplicate().powZn(mu));
    }
}
