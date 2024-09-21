package org.lppa.Run;

import bean.Properties;
import bean.Tag;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.testng.annotations.Test;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class datacheck {

    @Test
    public void propertiesGen() throws Exception {


        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        Element g = G1.newRandomElement();
        //私钥x
        Element x = Zr.newRandomElement();
        //公钥g^x
        Element g_x = g.duplicate().powZn(x);

        //生成U
        Element u =  G1.newRandomElement();

        Properties properties = new Properties(pairing, G1, Zr, g, x, u, g_x);

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\competition\\inputoutputtest\\Properties\\Properties.dat");

        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(properties);

        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void taggen() throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        Tag tag = new Tag();
        ArrayList<Element> arrayList = new ArrayList<>();
        //读入文件块
        File file = new File("D:\\competition\\output");
        String[] list = file.list();

        FileInputStream fileInputStream = new FileInputStream("D:\\competition\\inputoutputtest\\Properties\\Properties.dat");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        Properties pro = (Properties)inputStream.readObject();


//        System.out.println(pro.getG().toString());


//        for (int i = 0; i < list.length; i++) {
//            String s = list[i];
//
//            FileInputStream fs = new FileInputStream( "D:\\competition\\output\\"+ s);
//            byte[] b = new byte[1024];
//            fs.read(b);
//
//            //文件mi
//
//            Element mi = pro.getZr().newElementFromBytes(b);
//            //文件h(mi)哈希值
//
//            MessageDigest md = MessageDigest.getInstance("sha");
//            md.update(b);
//
//            byte[] digest= md.digest();
//
//            Element hmi = pro.getG1().newElementFromHash(digest,0,digest.length);
//
//            //生成西格玛i
//
//            Element umi = pro.getU().duplicate().powZn(mi);
//
//            Element hmiumi = umi.duplicate().mul(hmi);
//
//            Element xigemai = hmiumi.duplicate().powZn(pro.getX());
//
//            arrayList.add(xigemai);
//
//            tag.setXigemai(arrayList);
//
//            FileOutputStream fileOutputStream = new FileOutputStream("D:\\competition\\inputoutputtest\\Tags\\Tags");
//            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
//            outputStream.writeObject(tag);
//            outputStream.flush();
//            outputStream.close();
//
//        }


    }




}
