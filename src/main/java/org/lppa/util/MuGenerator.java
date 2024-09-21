package org.lppa.util;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lppa.util.ZeroGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Base64;

@Getter
@AllArgsConstructor
public class MuGenerator {

    private static final Logger log = LogManager.getLogger(MuGenerator.class);
    private File path;
    private Field field;
    private List<Element> challenges;
    private List<Integer> randomNumbers;
    private Integer blockSize;

    public Element generate() {
        ZeroGenerator zeroGenerator = new ZeroGenerator(field);
        String[] fileList = path.list();
        Element mu = zeroGenerator.generate();
        byte[] buffer = new byte[blockSize];
        int indexCount = 0;
        for(int i = 0; i < challenges.size(); i++) {
            String filePath = fileList[i];
            try(BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path + "\\" + filePath))) {
                Element element = zeroGenerator.generate();
                for (int j = 0; fileStream.read(buffer) != -1; j++) {
                    String encoded = Base64.getEncoder().encodeToString(buffer);
                    BigInteger mij = new BigInteger(buffer);
                    Element r = field.newElement(randomNumbers.get(j));
                    Element mul = r.duplicate().mul(mij);
                    element = element.duplicate().add(mul);
                }
                mu = mu.duplicate().add(challenges.get(i).duplicate().mul(element));
            } catch (IOException fnfe) {
                log.error("Block was missing");
            }
        }
        return mu;
    }
}
