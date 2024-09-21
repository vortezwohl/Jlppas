package org.lppa.Run;

import bean.Tag;
import bean.miu;
import bean.xigema;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        File file = new File("D:\\competition\\output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();


        byte[] gb = new byte[128];
        FileInputStream fileInputStream = new FileInputStream("D:\\competition\\properties\\properties-g");
        fileInputStream.read(gb);
        Element g = G1.newElementFromBytes(gb);


        //公钥g^x
        byte[] g_x_b = new byte[128];
        FileInputStream fileInputStream2 = new FileInputStream("D:\\competition\\properties\\properties-g_x");
        fileInputStream2.read(g_x_b);
        Element g_x = G1.newElementFromBytes(g_x_b);

        Tag tag1 = new Tag();
        tag1.tagfromfile(G1);

    }



    public Element miugen(Tag tag1,Field G1,Field Zr, ArrayList<Element>chal) throws IOException, ClassNotFoundException {
        long startTimemiu = System.currentTimeMillis();

        ArrayList<Integer> rand = tag1.rand;

        File file1 = new File("D:\\competition\\output1");
        miu miu = new miu();
        miu.miugen(file1,Zr,chal,rand);
        Element mu = miu.mu;

        long endtTimemiu = System.currentTimeMillis();

        long miutime = endtTimemiu - startTimemiu;

        System.out.println("μ生成时间：" + (double)miutime/1000 + "s");

        return mu;
    }

    public Element xigemagen(Tag tag1,ArrayList<Element>chal,Field G1){
        long startTimexigema = System.currentTimeMillis();

        xigema xigema = new xigema();
        xigema.xigen(tag1.tags,chal,G1);
        long endtTimexigema = System.currentTimeMillis();

        long xigematime = endtTimexigema - startTimexigema;

        System.out.println("σ生成时间：" + (double)xigematime/1000 + "s" );
        return xigema.xi;
    }

    public Element hash(Field G1,Tag tag1,ArrayList<Element>chal,int num){
        Element oneElement = G1.newOneElement();

        for (int i = 0; i < num ; i++) {
            Element temp = tag1.hashs.get(i).duplicate().powZn(chal.get(i));
            oneElement = temp.duplicate().mul(oneElement);
        }
        return oneElement;
    }

    public Tag tagformfile(Field G1) throws IOException, ClassNotFoundException {
        Tag tag1 = new Tag();
        tag1.tagfromfile(G1);
        return tag1;
    }
}
