import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import static consts.Const.basePath;

import java.io.File;

public class ComputeTest {

    public static void main(String[] args) {

        File file = new File(basePath + "/competition/output1");
        int length = file.list().length;
        //初始化
        Pairing pairing = PairingFactory.getPairing("a.properties");
        Field G1 = pairing.getG1();
        Field Zr = pairing.getZr();
        Element g1 = G1.newRandomElement();
        Element z1 = Zr.newRandomElement();


//        long startTimemul= System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            z1.duplicate().mul(z1);
//        }
//
//        long endtTimemul = System.currentTimeMillis();
//
//        long multime = endtTimemul - startTimemul;
//
//        System.out.println("时间：" + (double)multime/1000 + "s");
//
//
//        long startTimepow= System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            g1.duplicate().powZn(z1);
//        }
//
//        long endtTimepow = System.currentTimeMillis();
//
//        long powtime = endtTimepow - startTimepow;
//
//        System.out.println("时间：" + (double)powtime/1000 + "s");





        long startTime1= System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            z1.duplicate().mul(z1);
            g1.duplicate().powZn(z1);

        }

        long endtTime1 = System.currentTimeMillis();

        long time1 = endtTime1 - startTime1;

        System.out.println("文献[5,section3.3]标签生成时间：" + (double)time1/1000 + "s");


        long startTime2= System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            z1.duplicate().mul(z1);
            z1.duplicate().add(z1);
        }
        for (int i = 0; i < 200; i++) {
            g1.duplicate().powZn(z1);
        }

        long endtTime2 = System.currentTimeMillis();

        long time2 = endtTime2 - startTime2;

        System.out.println("本作品标签生成时间：" + (double)time2/1000 + "s");



        long startTime3= System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            pairing.pairing(g1,g1);
        }


        for (int i = 0; i < 1200; i++) {
            z1.duplicate().mul(z1);
        }
        for (int i = 0; i < 1200; i++) {
            g1.duplicate().powZn(z1);
        }

        long endtTime3 = System.currentTimeMillis();

        long time3 = endtTime3 - startTime3;

        System.out.println("公开验证时间：" + (double)time3/1000 + "s");



        long startTime4= System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            pairing.pairing(g1,g1);
        }


        for (int i = 0; i < 10000; i++) {
            z1.duplicate().mul(z1);
            z1.duplicate().add(z1);
        }
        for (int i = 0; i < 20; i++) {
            g1.duplicate().powZn(z1);
        }

        long endtTime4 = System.currentTimeMillis();

        long time4 = endtTime4 - startTime4;

        System.out.println("私自验证时间：" + (double)time4/1000 + "s");


    }
}
