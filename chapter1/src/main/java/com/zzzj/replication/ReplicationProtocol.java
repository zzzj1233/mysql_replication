package com.zzzj.replication;

import com.zzzj.replication.handshake.ClientHandShake;
import com.zzzj.replication.protocol.MysqlPackage;
import com.zzzj.replication.protocol.ProtocolReader;
import com.zzzj.replication.handshake.ServerHandShake;
import com.zzzj.replication.protocol.ProtocolWriter;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ReplicationProtocol {

    public static void main(String[] args) throws Exception {

        // open socket
        Socket socket = new Socket();

        // connection
        socket.connect(new InetSocketAddress("localhost", 3306));

        // =========================================================

        ProtocolReader protocolReader = new ProtocolReader(socket.getInputStream());

        ProtocolWriter protocolWriter = new ProtocolWriter(socket.getOutputStream());

        MysqlPackage serverPackage = protocolReader.readPackage();

        ServerHandShake serverHandShake = new ServerHandShake(serverPackage.payload);

        ClientHandShake clientHandShake = new ClientHandShake(serverHandShake.salt, "root", "123456");

        // Mysql协议要求客户端响应时的seq, 必须是服务端的seq + 1
        MysqlPackage responsePackage = new MysqlPackage(clientHandShake, serverPackage.seq + 1);

        protocolWriter.writeAndFlush(responsePackage);

        // =========================================================

        // close socket
        Thread.sleep(10000);

        socket.close();

    }

}