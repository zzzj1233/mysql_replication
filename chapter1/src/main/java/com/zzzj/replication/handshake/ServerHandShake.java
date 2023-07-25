package com.zzzj.replication.handshake;

import com.zzzj.replication.constant.MysqlCapability;
import com.zzzj.replication.protocol.ProtocolReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ServerHandShake {

    public final String salt;

    public final int capability;

    public ServerHandShake(byte[] payload) throws IOException {

        ProtocolReader reader = new ProtocolReader(new ByteArrayInputStream(payload));

        int protocol = reader.readInt(1);

        String version = reader.readNullTerminatedString();

        int threadId = reader.readInt(4);

        String saltPrefix = reader.readString(8);

        // 跳过filter
        reader.skip(1);

        // capability低16字节
        int capabilityLow = reader.readInt(2);

        int charset = reader.readInt(1);

        int statusFlag = reader.readInt(2);

        // capability高16字节
        int capabilityHigh = reader.readInt(2);

        // auth_plugin_data_len
        int authPluginDateLen = reader.readInt(1);

        // reversed
        reader.skip(10);

        // $length = MAX(13, auth_plugin_data_len - 8)
        // 注意：这就是前面说的文档有误的地方, 不应该读取$length, 而是读取 string<NUL>
        String saltSuffix = reader.readNullTerminatedString();

        String authPluginName = null;

        // 仅保留两个成员属性
        this.salt = saltPrefix + saltSuffix;

        this.capability = capabilityHigh << 16 | (capabilityLow);

        if ((capability & MysqlCapability.CLIENT_PLUGIN_AUTH) != 0) authPluginName = reader.readNullTerminatedString();

        // 可以打印一下其他属性, 和WireShake抓包工具的对比一下

        System.out.println("protocol = " + protocol);
        System.out.println("version = " + version);
        System.out.println("threadId = " + threadId);
        System.out.println("statusFlag = " + statusFlag);
        System.out.println("authPluginName = " + authPluginName);

    }

}
