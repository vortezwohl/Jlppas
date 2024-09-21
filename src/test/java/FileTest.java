import bean.Tag;
import bean.miu;
import bean.verify;
import bean.xigema;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class FileTest {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        File file = new File("D:\\competition\\output1");
        int length = file.list().length;
        //初始化
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



        //生成tag
        long startTimetag = System.currentTimeMillis();
        Tag tag = new Tag();
        tag.taggen(file,G1,Zr,x,g);

        long endtTimetag = System.currentTimeMillis();

        long tagtime = endtTimetag - startTimetag;

        System.out.println("标签生成时间：" + (double)tagtime/1000 + "s");


        //生成挑战

        long startTimechal = System.currentTimeMillis();

        ArrayList<Element> chal = new ArrayList<>();


        for (int i = 0; i < 50; i++) {
            chal.add(Zr.newRandomElement());
        }


        long endtTimechal = System.currentTimeMillis();

        long chaltime = endtTimechal - startTimechal;

        System.out.println("chal生成时间：" + (double)chaltime/1000 + "s");


        //生成miu


        long startTimemiu = System.currentTimeMillis();

        ArrayList<Integer> rand = tag.rand;



        File file1 = new File("D:\\competition\\output1");
        miu miu = new miu();
        miu.miugen(file1,Zr,chal,rand);
        Element mu = miu.mu;

        long endtTimemiu = System.currentTimeMillis();

        long miutime = endtTimemiu - startTimemiu;

        System.out.println("miu生成时间：" + (double)miutime/1000 + "s");


        //生成xigema


        long startTimexigema = System.currentTimeMillis();

        xigema xigema = new xigema();
        xigema.xigen(tag.tags,chal,G1);
        long endtTimexigema = System.currentTimeMillis();

        long xigematime = endtTimexigema - startTimexigema;

        System.out.println("xigema生成时间：" + (double)xigematime/1000 + "s" );

        //验证

//        verify r = new verify();
//        r.verifycompute(tag.hashs,chal,u,G1,mu);
//
//        Element t1 = pairing.pairing(xigema.xi,g);
//
//        System.out.println(t1);
//
//        Element t2 = pairing.pairing(r.rl,g);

//
//        System.out.println(t2);
//        boolean equals = t1.equals(t2);
//        System.out.println(equals);


        long startTimeverify = System.currentTimeMillis();


        Element oneElement = G1.newOneElement();

        for (int i = 0; i < 50; i++) {
            Element temp = tag.hashs.get(i).duplicate().powZn(chal.get(i));
            oneElement = temp.duplicate().mul(oneElement);
        }


        Element t1 = pairing.pairing(xigema.xi,g);
        Element t2 = pairing.pairing(oneElement.duplicate().mul(g.duplicate().powZn(mu)),g_x);
        boolean equals = t1.equals(t2);
        System.out.println(equals);
        long endtTimeverify = System.currentTimeMillis();

        long verifytime = endtTimeverify - startTimeverify;

        System.out.println("验证时间：" + (double)verifytime/1000 + "s" );


    }
}
