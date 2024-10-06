package bean;

import java.util.Base64;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import static consts.Const.basePath;

public class Tag implements Serializable {
     private static final long serialVersionUID = 1123313131256310L;
     public ArrayList<Element> tags;
     public ArrayList<Element> hashs;
     public ArrayList<Integer> rand;

    public void generate(File file, Field G1, Field Zr, Element x, Element g) throws IOException, NoSuchAlgorithmException {
        String[] list = file.list();
        ArrayList<Element> list1 = new ArrayList<>();
        ArrayList<Element> list2 = new ArrayList<>();
        ArrayList<Integer> list4 = new ArrayList<>();
        BufferedInputStream bufferedInputStream = null;
        FileInputStream fileInputStream = null;
        Random random = new Random();
        for (int j = 0; j < 52429; j++) {
            int nextInt = random.nextInt();
            list4.add(nextInt);
        }
        for (int s = 0; s < list.length; s++) {
            String i = list[s];
            File file2 = new File(file + "\\" + i);
            fileInputStream = new FileInputStream(file2);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] b = new byte[20];
            Element temp = Zr.newZeroElement();
            int count = 0;
            while (bufferedInputStream.read(b) != -1) {
                Integer integer = list4.get(count);
                Element randomElement = Zr.newElement(integer);
                String encode = Base64.getEncoder().encodeToString(b);
                byte[] bytes = encode.getBytes();
                BigInteger mij = new BigInteger(bytes);
                //生成umij
                Element mul = randomElement.duplicate().mul(mij);
                temp = temp.duplicate().add(mul);
                count++;
            }
            //哈希值h(name||i)
            MessageDigest md = MessageDigest.getInstance("sha-256");
            String s1 = file.toString();
            String s2 = s1 + i;
            byte[] bytes = s2.getBytes();
            md.update(bytes);
            byte[] digest = md.digest();
            Element hnamei = G1.newElementFromHash(digest, 0, digest.length);
            list2.add(hnamei);
            //生成sigma_i
            Element powZn = g.duplicate().powZn(temp);
            Element mul = powZn.duplicate().mul(hnamei);
            Element sigma_i = mul.duplicate().powZn(x);
            list1.add(sigma_i);
//            Element mul = temp.duplicate().mul(hnamei);
//
//
//            Element sigma_i = mul.duplicate().powZn(x);
            File file1 = new File(basePath + "\\competition\\tags\\");
            FileOutputStream fileOutputStream = new FileOutputStream(file1 + "\\" + s);
            fileOutputStream.write(sigma_i.toCanonicalRepresentation());
            File file3 = new File(basePath + "\\competition\\hashs\\");
            FileOutputStream fileOutputStream2 = new FileOutputStream(file3 + "\\" + s);
            fileOutputStream2.write(hnamei.toCanonicalRepresentation());
        }
        File file4 = new File(basePath + "\\competition\\rand");
        FileOutputStream fileOutputStream1 = new FileOutputStream(file4);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream1);
        objectOutputStream.writeObject(list4);
        this.tags = list1;
        this.hashs = list2;
        this.rand = list4;
        bufferedInputStream.close();
        fileInputStream.close();
    }

    public void tagFromFile(Field G1) throws IOException, ClassNotFoundException {
        File file1 = new File(basePath + "\\competition\\tags\\");
        String[] list = file1.list();
        ArrayList<Element> list1 = new ArrayList<Element>();
        ArrayList<Element> list2 = new ArrayList<Element>();
        ArrayList<Integer> list4;
        for (int i = 0; i < list.length; i++) {
            FileInputStream fileInputStream = new FileInputStream(file1 + "\\" + i);
            byte[] b1 = new byte[128];
            fileInputStream.read(b1);
            Element element = G1.newElementFromBytes(b1);
            list1.add(i,element);
            fileInputStream.close();
            File file3 = new File(basePath + "\\competition\\hashs\\");
            FileInputStream fileInputStream2 = new FileInputStream(file3 + "\\" + i);
            byte[] b2 = new byte[128];
            fileInputStream2.read(b2);
            Element element2 = G1.newElementFromBytes(b2);
            list2.add(i,element2);
            fileInputStream2.close();
        }
            File file4 = new File(basePath + "\\competition\\rand");
            FileInputStream fileInputStream = new FileInputStream(file4);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list4  = (ArrayList<Integer>) objectInputStream.readObject();
        this.tags = list1;
        this.hashs = list2;
        this.rand = list4;
    }
}
