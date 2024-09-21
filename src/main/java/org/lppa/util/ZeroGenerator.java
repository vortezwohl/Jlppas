package org.lppa.util;

import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Element;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ZeroGenerator {
    private Field field;
    public Element generate() {
        return field.newZeroElement();
    }
}
