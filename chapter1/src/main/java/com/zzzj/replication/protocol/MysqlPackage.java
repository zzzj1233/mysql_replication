package com.zzzj.replication.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MysqlPackage implements Command {

    public int payloadLength;

    public int seq;

    public byte[] payload;

    public MysqlPackage(int payloadLength, int seq, byte[] payload) {
        this.payloadLength = payloadLength;
        this.seq = seq;
        this.payload = payload;
    }

    public MysqlPackage(Command command, int seq) throws IOException {
        byte[] payload = command.toBytes();

        this.payloadLength = payload.length;
        this.seq = seq;
        this.payload = payload;
    }

    @Override
    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ProtocolWriter protocolWriter = new ProtocolWriter(out);

        protocolWriter.writeInt(payloadLength, 3);
        protocolWriter.writeInt(seq, 1);
        protocolWriter.writeBytes(payload);

        return out.toByteArray();
    }

}
