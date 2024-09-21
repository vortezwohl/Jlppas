import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.*;

public class propertiesTest2 {

    public static void main(String[] args) throws IOException {

        File file = new File("D:\\competition\\output");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        Element g = G1.newRandomElement();
        //私钥x
        Element x = Zr.newRandomElement();



        System.out.println(x);



        byte[] bytes = x.toCanonicalRepresentation();


        File file1 = new File("D:\\competition\\key");
//        FileOutputStream fileOutputStream = new FileOutputStream(file1);
//        fileOutputStream.write(bytes);




//        FileInputStream fileInputStream = new FileInputStream(file1);
//        byte[] bytes2 = new byte[160];
//        fileInputStream.read(bytes2);
//
//        Element x2 = Zr.newElementFromBytes(bytes2);
//
//        System.out.println("****************************");
//        System.out.println(x2);

    }
}
