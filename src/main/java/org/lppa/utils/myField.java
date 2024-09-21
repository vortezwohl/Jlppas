package org.lppa.utils;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;

import java.io.Serializable;
import java.math.BigInteger;

public class myField implements Serializable, Field {
    @Override
    public Element newElement() {
        return null;
    }

    @Override
    public Element newElement(int i) {
        return null;
    }

    @Override
    public Element newElement(BigInteger bigInteger) {
        return null;
    }

    @Override
    public Element newElement(Element element) {
        return null;
    }

    @Override
    public Element newElementFromHash(byte[] bytes, int i, int i1) {
        return null;
    }

    @Override
    public Element newElementFromBytes(byte[] bytes) {
        return null;
    }

    @Override
    public Element newElementFromBytes(byte[] bytes, int i) {
        return null;
    }

    @Override
    public Element newZeroElement() {
        return null;
    }

    @Override
    public Element newOneElement() {
        return null;
    }

    @Override
    public Element newRandomElement() {
        return null;
    }

    @Override
    public BigInteger getOrder() {
        return null;
    }

    @Override
    public boolean isOrderOdd() {
        return false;
    }

    @Override
    public Element getNqr() {
        return null;
    }

    @Override
    public int getLengthInBytes() {
        return 0;
    }

    @Override
    public int getLengthInBytes(Element element) {
        return 0;
    }

    @Override
    public int getCanonicalRepresentationLengthInBytes() {
        return 0;
    }

    @Override
    public Element[] twice(Element[] elements) {
        return new Element[0];
    }

    @Override
    public Element[] add(Element[] elements, Element[] elements1) {
        return new Element[0];
    }

    @Override
    public ElementPowPreProcessing getElementPowPreProcessingFromBytes(byte[] bytes) {
        return null;
    }

    @Override
    public ElementPowPreProcessing getElementPowPreProcessingFromBytes(byte[] bytes, int i) {
        return null;
    }
}
