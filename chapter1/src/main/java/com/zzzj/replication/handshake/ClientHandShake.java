package com.zzzj.replication.handshake;

import com.zzzj.replication.protocol.Command;
import com.zzzj.replication.protocol.ProtocolWriter;
import com.zzzj.replication.utils.PasswordUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.zzzj.replication.constant.MysqlCapability.MY_CLIENT_CAPABILITY;

public class ClientHandShake implements Command {

    public static final int CHARSET_UTF8 = 0x21;

    public static final String PASSWORD_PLUGIN_NAME = "mysql_native_password";

    private final byte[] payload;

    public ClientHandShake(String salt, String username, String password) throws IOException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ProtocolWriter writer = new ProtocolWriter(output);

        writer.writeInt(MY_CLIENT_CAPABILITY, 4);
        writer.writeInt(Integer.MAX_VALUE, 4);
        writer.writeInt(CHARSET_UTF8, 1);
        writer.skip(23);
        writer.writeStringNullEnd(username);
        byte[] passwordBytes = PasswordUtils.nativePassword(password, salt);
        writer.writeLengthEncoded(passwordBytes);
        writer.writeStringNullEnd(PASSWORD_PLUGIN_NAME);

        this.payload = output.toByteArray();
    }

    @Override
    public byte[] toBytes() {
        return payload;
    }

}
