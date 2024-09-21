import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.junit.internal.runners.statements.FailOnTimeout;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;

public class propertiesTest {


    public static void main(String[] args) throws IOException {


        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        Element g = G1.newRandomElement();
        //私钥x
        Element x = Zr.newRandomElement();

        //公钥g^x
        Element g_x = g.duplicate().powZn(x);

        byte[] g_bytes = g.toCanonicalRepresentation();
        File g_file = new File("D:\\competition\\properties\\properties-g");
        FileOutputStream fileOutputStream = new FileOutputStream(g_file);
        fileOutputStream.write(g_bytes);
        System.out.println(g_bytes.length);


        byte[] x_bytes = x.toCanonicalRepresentation();
        File x_file = new File("D:\\competition\\properties\\properties-x");
        FileOutputStream fileOutputStream1 = new FileOutputStream(x_file);
        fileOutputStream1.write(x_bytes);
        System.out.println(x_bytes.length);


        byte[] g_x_bytes = g_x.toCanonicalRepresentation();
        File g_x_file = new File("D:\\competition\\properties\\properties-g_x");
        FileOutputStream fileOutputStream2 = new FileOutputStream(g_x_file);
        fileOutputStream2.write(g_x_bytes);
        System.out.println(g_x_bytes.length);

        fileOutputStream.close();
        fileOutputStream1.close();
        fileOutputStream2.close();

    }
}
