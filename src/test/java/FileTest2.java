import bean.Sigma;
import bean.Tag;
import bean.Mu;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static consts.Const.basePath;

public class FileTest2 {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        File file = new File(basePath + "\\competition\\output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();

        //g


        byte[] gb = new byte[128];
        FileInputStream fileInputStream = new FileInputStream(basePath + "\\competition\\properties\\properties-g");
        fileInputStream.read(gb);
        Element g = G1.newElementFromBytes(gb);


        //私钥x
        byte[] xb = new byte[20];
        FileInputStream fileInputStream1 = new FileInputStream(basePath + "\\competition\\properties\\properties-x");
        fileInputStream1.read(xb);
        Element x = Zr.newElementFromBytes(xb);



        //公钥g^x
        byte[] g_x_b = new byte[128];
        FileInputStream fileInputStream2 = new FileInputStream(basePath + "\\competition\\properties\\properties-g_x");
        fileInputStream2.read(g_x_b);
        Element g_x = G1.newElementFromBytes(g_x_b);


        //生成tag
        long startTimetag = System.currentTimeMillis();
        Tag tag = new Tag();
        tag.generate(file,G1,Zr,x,g);

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


        Tag tag1 = new Tag();
        tag1.tagFromFile(G1);


        ArrayList<Integer> rand = tag1.rand;

        File file1 = new File(basePath + "\\competition\\output1");
        Mu Mu = new Mu();
        Mu.generate(file1,Zr,chal,rand);
        Element mu = Mu.mu;

        long endtTimemiu = System.currentTimeMillis();

        long miutime = endtTimemiu - startTimemiu;

        System.out.println("miu生成时间：" + (double)miutime/1000 + "s");


        //生成xigema


        long startTimexigema = System.currentTimeMillis();

        Sigma sigma = new Sigma(tag1.tags,chal,G1);
        long endtTimexigema = System.currentTimeMillis();

        long xigematime = endtTimexigema - startTimexigema;

        System.out.println("xigema生成时间：" + (double)xigematime/1000 + "s" );

        //验证

//        Verify r = new Verify();
//        r.verifycompute(tag.hashs,chal,u,G1,mu);
//
//        Element t1 = pairing.pairing(Sigma.xi,g);
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

        for (int i = 0; i < 50 ; i++) {
            Element temp = tag1.hashs.get(i).duplicate().powZn(chal.get(i));
            oneElement = temp.duplicate().mul(oneElement);
        }



        Element t1 = pairing.pairing(sigma.xi,g);
        Element t2 = pairing.pairing(oneElement.duplicate().mul(g.duplicate().powZn(mu)),g_x);
        boolean equals = t1.equals(t2);
        System.out.println(equals);
        long endtTimeverify = System.currentTimeMillis();

        long verifytime = endtTimeverify - startTimeverify;

        System.out.println("验证时间：" + (double)verifytime/1000 + "s" );


    }
}
