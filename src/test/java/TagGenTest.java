import bean.Tag;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static consts.Const.basePath;

public class TagGenTest {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

//初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        it.unisa.dia.gas.jpbc.Element g = G1.newRandomElement();
        //私钥x
        it.unisa.dia.gas.jpbc.Element x = Zr.newRandomElement();
        //公钥g^x
        it.unisa.dia.gas.jpbc.Element g_x = g.duplicate().powZn(x);

        //生成U
        Element u =  G1.newRandomElement();

        Tag tag = new Tag();
        File file = new File(basePath + "\\competition\\output\\output\\");
        tag.generate(file,G1,Zr,x,u);
    }
}
