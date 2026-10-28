package net.auto.gg.server;

public interface ServerHandler {
    boolean matchesIp(String ip);
    boolean GameEnd(String title);
    boolean GameStart(String title);
}
