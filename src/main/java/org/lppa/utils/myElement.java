package org.lppa.utils;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;

import java.io.Serializable;
import java.math.BigInteger;

public class myElement implements Serializable, Element {

    @Override
    public Field getField() {
        return null;
    }

    @Override
    public int getLengthInBytes() {
        return 0;
    }

    @Override
    public boolean isImmutable() {
        return false;
    }

    @Override
    public Element getImmutable() {
        return null;
    }

    @Override
    public Element duplicate() {
        return null;
    }

    @Override
    public Element set(Element element) {
        return null;
    }

    @Override
    public Element set(int i) {
        return null;
    }

    @Override
    public Element set(BigInteger bigInteger) {
        return null;
    }

    @Override
    public BigInteger toBigInteger() {
        return null;
    }

    @Override
    public Element setToRandom() {
        return null;
    }

    @Override
    public Element setFromHash(byte[] bytes, int i, int i1) {
        return null;
    }

    @Override
    public int setFromBytes(byte[] bytes) {
        return 0;
    }

    @Override
    public int setFromBytes(byte[] bytes, int i) {
        return 0;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public byte[] toCanonicalRepresentation() {
        return new byte[0];
    }

    @Override
    public Element setToZero() {
        return null;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public Element setToOne() {
        return null;
    }

    @Override
    public boolean isEqual(Element element) {
        return false;
    }

    @Override
    public boolean isOne() {
        return false;
    }

    @Override
    public Element twice() {
        return null;
    }

    @Override
    public Element square() {
        return null;
    }

    @Override
    public Element invert() {
        return null;
    }

    @Override
    public Element halve() {
        return null;
    }

    @Override
    public Element negate() {
        return null;
    }

    @Override
    public Element add(Element element) {
        return null;
    }

    @Override
    public Element sub(Element element) {
        return null;
    }

    @Override
    public Element mul(Element element) {
        return null;
    }

    @Override
    public Element mul(int i) {
        return null;
    }

    @Override
    public Element mul(BigInteger bigInteger) {
        return null;
    }

    @Override
    public Element mulZn(Element element) {
        return null;
    }

    @Override
    public Element div(Element element) {
        return null;
    }

    @Override
    public Element pow(BigInteger bigInteger) {
        return null;
    }

    @Override
    public Element powZn(Element element) {
        return null;
    }

    @Override
    public ElementPowPreProcessing getElementPowPreProcessing() {
        return null;
    }

    @Override
    public Element sqrt() {
        return null;
    }

    @Override
    public boolean isSqr() {
        return false;
    }

    @Override
    public int sign() {
        return 0;
    }
}
