package com.zzzj.replication.constant;

/**
 * @author zzzj
 * @create 2023-07-19 18:38
 */
public class MysqlCapability {

    public static final int CLIENT_LONG_PASSWORD = 1;
    public static final int CLIENT_FOUND_ROWS = 1 << 1;
    public static final int CLIENT_LONG_FLAG = 1 << 2;
    public static final int CLIENT_CONNECT_WITH_DB = 1 << 3;
    public static final int CLIENT_NO_SCHEMA = 1 << 4;
    public static final int CLIENT_COMPRESS = 1 << 5;
    public static final int CLIENT_ODBC = 1 << 6;
    public static final int CLIENT_LOCAL_FILES = 1 << 7;
    public static final int CLIENT_IGNORE_SPACE = 1 << 8;
    public static final int CLIENT_PROTOCOL_41 = 1 << 9;
    public static final int CLIENT_INTERACTIVE = 1 << 10;
    public static final int CLIENT_SSL = 1 << 11;
    public static final int CLIENT_IGNORE_SIGPIPE = 1 << 12;
    public static final int CLIENT_TRANSACTIONS = 1 << 13;
    public static final int CLIENT_RESERVED = 1 << 14;
    public static final int CLIENT_SECURE_CONNECTION = 1 << 15;
    public static final int CLIENT_MULTI_STATEMENTS = 1 << 16;
    public static final int CLIENT_MULTI_RESULTS = 1 << 17;
    public static final int CLIENT_PS_MULTI_RESULTS = 1 << 18;
    public static final int CLIENT_PLUGIN_AUTH = 1 << 19;
    public static final int CLIENT_CONNECT_ATTRS = 1 << 20;
    public static final int CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = 1 << 21;
    public static final int CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS = 1 << 22;
    public static final int CLIENT_SESSION_TRACK = 1 << 23;
    public static final int CLIENT_DEPRECATE_EOF = 1 << 24;
    public static final int CLIENT_OPTIONAL_RESULTSET_METADATA = 1 << 25;
    public static final int CLIENT_ZSTD_COMPRESSION_ALGORITHM = 1 << 26;
    public static final int CLIENT_QUERY_ATTRIBUTES = 1 << 27;
    public static final int MULTI_FACTOR_AUTHENTICATION = 1 << 28;
    public static final int CLIENT_CAPABILITY_EXTENSION = 1 << 29;
    public static final int CLIENT_SSL_VERIFY_SERVER_CERT = 1 << 30;
    public static final int CLIENT_REMEMBER_OPTIONS = 1 << 31;

    public static final int MY_CLIENT_CAPABILITY = CLIENT_PROTOCOL_41
            | CLIENT_PLUGIN_AUTH
            | CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA
            | CLIENT_LONG_FLAG | CLIENT_SECURE_CONNECTION;

}
