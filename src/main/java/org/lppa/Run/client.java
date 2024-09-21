package org.lppa.Run;

import bean.Tag;
import bean.xigema;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class client {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {



        File file = new File("D:\\competition\\output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();

        //g


        byte[] gb = new byte[128];
        FileInputStream fileInputStream = new FileInputStream("D:\\competition\\properties\\properties-g");
        fileInputStream.read(gb);
        Element g = G1.newElementFromBytes(gb);


        //私钥x
        byte[] xb = new byte[20];
        FileInputStream fileInputStream1 = new FileInputStream("D:\\competition\\properties\\properties-x");
        fileInputStream1.read(xb);
        Element x = Zr.newElementFromBytes(xb);



        //公钥g^x
        byte[] g_x_b = new byte[128];
        FileInputStream fileInputStream2 = new FileInputStream("D:\\competition\\properties\\properties-g_x");
        fileInputStream2.read(g_x_b);
        Element g_x = G1.newElementFromBytes(g_x_b);


        //文件上传

        if(args.length > 0 && args[0].equals("fileup")){
            client.fileup();
        }else if(args.length > 0 && args[0].equals("audit")){
            //文件验证
            client.audit();
            }else{
            System.out.println("请输入指令");
        }







    }

    public static void fileup() throws IOException, NoSuchAlgorithmException {
        File file = new File("D:\\competition\\output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();

        //g

        byte[] gb = new byte[128];
        FileInputStream fileInputStream = new FileInputStream("D:\\competition\\properties\\properties-g");
        fileInputStream.read(gb);
        Element g = G1.newElementFromBytes(gb);

        //私钥x
        byte[] xb = new byte[20];
        FileInputStream fileInputStream1 = new FileInputStream("D:\\competition\\properties\\properties-x");
        fileInputStream1.read(xb);
        Element x = Zr.newElementFromBytes(xb);

        //公钥g^x
        byte[] g_x_b = new byte[128];
        FileInputStream fileInputStream2 = new FileInputStream("D:\\competition\\properties\\properties-g_x");
        fileInputStream2.read(g_x_b);
        Element g_x = G1.newElementFromBytes(g_x_b);

        //生成tag
        client.clienttag(file,G1,Zr,x,g);

    }


    public static void clienttag(File file,Field G1,Field Zr,Element x,Element g) throws IOException, NoSuchAlgorithmException {
        //生成tag
        long startTimetag = System.currentTimeMillis();
        Tag tag = new Tag();
        tag.taggen(file,G1,Zr,x,g);
        long endtTimetag = System.currentTimeMillis();

        long tagtime = endtTimetag - startTimetag;

        System.out.println("标签生成时间：" + (double)tagtime/1000 + "s");
    }


    public static ArrayList<Element> challenge(Field Zr,int num){
        long startTimechal = System.currentTimeMillis();

        ArrayList<Element> chal = new ArrayList<>();


        for (int i = 0; i < num; i++) {
            chal.add(Zr.newRandomElement());
        }


        long endtTimechal = System.currentTimeMillis();

        long chaltime = endtTimechal - startTimechal;

        System.out.println("用户chal生成时间：" + (double)chaltime/1000 + "s");
        return chal;
    }

    public static void audit() throws IOException, ClassNotFoundException {


        File file = new File("D:\\competition\\output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();

        //g


        byte[] gb = new byte[128];
        FileInputStream fileInputStream = new FileInputStream("D:\\competition\\properties\\properties-g");
        fileInputStream.read(gb);
        Element g = G1.newElementFromBytes(gb);


        //私钥x
        byte[] xb = new byte[20];
        FileInputStream fileInputStream1 = new FileInputStream("D:\\competition\\properties\\properties-x");
        fileInputStream1.read(xb);
        Element x = Zr.newElementFromBytes(xb);



        //公钥g^x
        byte[] g_x_b = new byte[128];
        FileInputStream fileInputStream2 = new FileInputStream("D:\\competition\\properties\\properties-g_x");
        fileInputStream2.read(g_x_b);
        Element g_x = G1.newElementFromBytes(g_x_b);
        //验证

        int num = 200;

        ArrayList<Element> chal = client.challenge(Zr,num);


        server server = new server();
        Tag tag1 = server.tagformfile(G1);

        Element hash = server.hash(G1, tag1, chal,num);

        Element xigema = server.xigemagen(tag1, chal, G1);

        Element mu = server.miugen(tag1, G1, Zr, chal);


        long startTimeverify = System.currentTimeMillis();


        Element t1 = pairing.pairing(xigema,g);
        Element t2 = pairing.pairing(hash.duplicate().mul(g.duplicate().powZn(mu)),g_x);
        boolean equals = t1.equals(t2);
        System.out.println("数据验证结果为：" + equals);
        long endtTimeverify = System.currentTimeMillis();

        long verifytime = endtTimeverify - startTimeverify;

        System.out.println("用户验证时间：" + (double)verifytime/1000 + "s" );
    }








}
