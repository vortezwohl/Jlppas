package bean;

import java.util.Base64;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class Mu {
    public Element mu;

    public void generate(File file, Field Zr, ArrayList<Element> chal, ArrayList<Integer> rand) throws IOException {
        String[] list = file.list();
        Element mu = Zr.newZeroElement();

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            for (int i = 0; i < chal.size(); i++) {
                String s = list[i];
                fileInputStream = new FileInputStream(file + "\\" + s);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                int len = -1;
                byte[] b = new byte[20];
                Element element = Zr.newZeroElement();
                int j = 0;
                while ((len = bufferedInputStream.read(b)) != -1) {
                    String encode = Base64.getEncoder().encodeToString(b);
                    byte[] bytes = encode.getBytes();
                    BigInteger mij = new BigInteger(bytes);
                    Element r = Zr.newElement(rand.get(j));
                    Element mul = r.duplicate().mul(mij);
                    element = element.duplicate().add(mul);
                    j++;
                }
                Element temp = chal.get(i).duplicate().mul(element);
                mu = mu.duplicate().add(temp);

            }
        } catch (Exception e) {
            System.out.println("文件块丢失，验证失败");
        } finally {
            bufferedInputStream.close();
            fileInputStream.close();
        }
        this.mu = mu;
    }
}
