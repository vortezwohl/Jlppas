package org.lppa.Run;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TagGen {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {


        double time1,time2,time3=0;

        time1 = System.nanoTime();

        //读入文件块
        File file = new File("D:\\competition\\output");
        String[] list = file.list();


        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        Element g = G1.newRandomElement();
        //私钥x
        Element x = Zr.newRandomElement();
        //公钥g^x
        Element g_x = g.duplicate().powZn(x);


        Element xigema = G1.newOneElement();



        //生成U
        Element u =  G1.newRandomElement();


        //生成vi,假设vi都一样

        Element vi = Zr.newRandomElement();

        //生成miu
        Element miu = Zr.newZeroElement();

        //1-s hmivi
        Element hmivi = G1.newOneElement();


        //读入文件，生成xigema
        for (int i = 0; i < list.length; i++) {
            String s = list[i];

            FileInputStream fs = new FileInputStream( "D:\\competition\\output\\"+ s);
            byte[] b = new byte[(int) s.length()];
            fs.read(b);

            //文件mi

            Element mi = Zr.newElementFromBytes(b);
            //文件h(mi)哈希值

            MessageDigest md = MessageDigest.getInstance("sha");
            md.update(b);

            byte[] digest= md.digest();

            Element hmi = G1.newElementFromHash(digest,0,digest.length);

            //生成西格玛i

            Element umi = u.duplicate().powZn(mi);

            Element hmiumi = umi.duplicate().mul(hmi);

            Element xigemai = hmiumi.duplicate().powZn(x);


            Element xigemaivi = xigemai.duplicate().powZn(vi);

            xigema = xigema.duplicate().mul(xigemaivi);

            //生成μ
            miu = miu.duplicate().add(vi.duplicate().mulZn(mi));


            //生成1-s hmivi
            Element hmivii = hmi.duplicate().powZn(vi);

            hmivi = hmivi.duplicate().mul(hmivii);

        }


        //验证
        Element t1 = pairing.pairing(xigema,g);


        System.out.println(t1);


        Element umiu = u.duplicate().powZn(miu);
        Element t2 = pairing.pairing(hmivi.duplicate().mul(umiu),g_x);

        System.out.println(t2);


        boolean equals = t1.equals(t2);
        System.out.println(equals);

        time2 = System.nanoTime();

        time3 = time2-time1;
        time3 = time3/1000000000;
        System.out.println(time3);
    }
}
