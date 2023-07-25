package com.zzzj.replication.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ProtocolReader {

    private final InputStream inputStream;

    public ProtocolReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int readInt(int size) throws IOException {

        if (size <= 0 | size > 4)
            throw new IllegalArgumentException("Illegal int size " + size + " , expect : [ 1 - 4 ]");

        int value = 0;

        for (int i = 0; i < size; i++)
            value |= inputStream.read() << (i << 3);

        return value;
    }

    public byte[] readBytes(int size) throws IOException {
        byte[] bytes = new byte[size];

        inputStream.read(bytes);

        return bytes;
    }

    public String readString(int size) throws IOException {
        return new String(readBytes(size), StandardCharsets.UTF_8);
    }

    public String readNullTerminatedString() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte b;

        while ((b = ((byte) inputStream.read())) != 0) output.write(b);

        return output.toString(StandardCharsets.UTF_8.name());
    }

    public void skip(long n) throws IOException {
        inputStream.skip(n);
    }

    public MysqlPackage readPackage() throws IOException {

        int payloadLength = readInt(3);

        int seq = readInt(1);

        byte[] payload = readBytes(payloadLength);

        return new MysqlPackage(payloadLength, seq, payload);
    }

}
