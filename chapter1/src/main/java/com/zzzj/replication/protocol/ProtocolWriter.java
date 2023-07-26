package com.zzzj.replication.protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ProtocolWriter {

    private final OutputStream out;

    public ProtocolWriter(OutputStream out) {
        this.out = out;
    }

    public void writeInt(int value, int len) throws IOException {

        if (len > 4) throw new IllegalArgumentException("Illegal int len : " + len);

        for (int i = 0; i < len; i++)
            out.write((value >> (i << 3)) & 0xff);

    }

    public void skip(long len) throws IOException {
        for (int i = 0; i < len; i++)
            out.write(0);
    }

    public void writeBytes(byte[] bytes) throws IOException {
        out.write(bytes);
    }

    public void writeStringNullEnd(String value) throws IOException {
        writeBytes(value.getBytes(StandardCharsets.UTF_8));
        out.write(0);

    }

    public void writeAndFlush(Command command) throws IOException {
        writeBytes(command.toBytes());
        out.flush();
    }

    public void writeLengthEncoded(byte[] bytes) throws IOException {

        int length = bytes.length;

        if (length < 251) {
            out.write(length);
        } else if (length < 65536) {
            out.write(252);
            writeInt(length, 2);
        } else if (length < 16777216) {
            out.write(253);
            writeInt(length, 3);
        } else {
            out.write(254);
            writeInt(length, 4);
        }

        writeBytes(bytes);
    }

    public void writeLengthEncodedString(String str) throws IOException {
        writeLengthEncoded(str.getBytes(StandardCharsets.UTF_8));
    }

}
