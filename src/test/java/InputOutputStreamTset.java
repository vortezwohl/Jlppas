import bean.Properties;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class InputOutputStreamTset {



    @Test
    public  void outtest() throws Exception {
        File file = new File("D:\\competition\\inputoutputtest\\object.dat");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

//        outputStream.writeObject(new String("我爱北京天安门"));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);

//        outputStream.writeObject(new person("TOM",23,list));

        outputStream.writeObject(new person("Rose",20,list));
        outputStream.writeObject(new person("adad",20,list));

        outputStream.flush();
        outputStream.close();


    }


    @Test
    public  void intest() throws Exception {

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

        File file = new File("D:\\competition\\inputoutputtest\\object.dat");

        FileOutputStream stream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);



        Properties properties = new Properties(pairing, G1, Zr, g, x, g_x, u);

        Object o = (Object) properties;



        outputStream.writeObject(o);

        outputStream.flush();
        outputStream.close();


    }




}
