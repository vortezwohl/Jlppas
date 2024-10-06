package run;

import bean.Tag;
import bean.Mu;
import bean.Sigma;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static consts.Const.basePath;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        File file = new File(basePath + "\\competition\\output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();


        byte[] gb = new byte[128];
        FileInputStream fileInputStream = new FileInputStream(basePath + "\\competition\\properties\\properties-g");
        fileInputStream.read(gb);
        Element g = G1.newElementFromBytes(gb);


        //公钥g^x
        byte[] g_x_b = new byte[128];
        FileInputStream fileInputStream2 = new FileInputStream(basePath + "\\competition\\properties\\properties-g_x");
        fileInputStream2.read(g_x_b);
        Element g_x = G1.newElementFromBytes(g_x_b);

        Tag tag1 = new Tag();
        tag1.tagFromFile(G1);

    }



    public Element muGen(Tag tag1, Field G1, Field Zr, ArrayList<Element>chal) throws IOException, ClassNotFoundException {
        long startTimemiu = System.currentTimeMillis();

        ArrayList<Integer> rand = tag1.rand;

        File file1 = new File(basePath + "\\competition\\output1");
        Mu Mu = new Mu();
        Mu.generate(file1,Zr,chal,rand);
        Element mu = Mu.mu;

        long endtTimemiu = System.currentTimeMillis();

        long miutime = endtTimemiu - startTimemiu;

        System.out.println("μ生成时间：" + (double)miutime/1000 + "s");

        return mu;
    }

    public Element sigmaGen(Tag tag1, ArrayList<Element>chal, Field G1){
        long startTimexigema = System.currentTimeMillis();

        Sigma sigma = new Sigma(tag1.tags,chal,G1);
        long endtTimexigema = System.currentTimeMillis();

        long xigematime = endtTimexigema - startTimexigema;

        System.out.println("σ生成时间：" + (double)xigematime/1000 + "s" );
        return sigma.xi;
    }

    public Element hash(Field G1,Tag tag1,ArrayList<Element>chal,int num){
        Element oneElement = G1.newOneElement();

        for (int i = 0; i < num ; i++) {
            Element temp = tag1.hashs.get(i).duplicate().powZn(chal.get(i));
            oneElement = temp.duplicate().mul(oneElement);
        }
        return oneElement;
    }

    public Tag tagFromFile(Field G1) throws IOException, ClassNotFoundException {
        Tag tag1 = new Tag();
        tag1.tagFromFile(G1);
        return tag1;
    }
}
