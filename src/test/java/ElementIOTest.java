import bean.Tag;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import static consts.Const.basePath;

public class ElementIOTest {

    @Test
    public void ElementIO() throws FileNotFoundException {
        Pairing pairing =  PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr =  pairing.getZr();
        Element g =  G1.newRandomElement();
        System.out.println(g);
//        File file = new File("D:\\competition\\inputoutputtest\\IOtest");
//        FileOutputStream outputStream = new FileOutputStream(file);

    }
    @Test
    public  void testTag() throws IOException, NoSuchAlgorithmException {
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        //私钥x
        Element x = Zr.newRandomElement();
        File file = new File( basePath + "/competition/output");
        //生成U
        Element u =  G1.newRandomElement();
        Tag tag = new Tag();
        tag.generate(file,G1,Zr,x,u);
        for (int i = 0; i < tag.tags.size(); i++) {
            System.out.println(tag.tags.get(i));
        }

    }
}
