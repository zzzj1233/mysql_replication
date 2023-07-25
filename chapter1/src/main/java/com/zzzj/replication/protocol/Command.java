package com.zzzj.replication.protocol;

import java.io.IOException;

public interface Command {

    byte[] toBytes() throws IOException;

}
