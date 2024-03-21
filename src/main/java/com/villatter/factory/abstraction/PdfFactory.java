package com.villatter.factory.abstraction;

public interface PdfFactory extends SingleFactory{
    public byte[] getPdfByte() throws Exception;
}
