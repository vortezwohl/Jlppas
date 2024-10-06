package bean;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
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
}
