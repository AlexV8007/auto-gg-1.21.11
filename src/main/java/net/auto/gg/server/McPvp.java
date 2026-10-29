package net.auto.gg.server;

public class McPvp implements ServerHandler {
    @Override
    public boolean matchesIp(String ip) {
        return ip.contains("mcpvp.club");
    }

    @Override
    public boolean GameEnd(String title) {
        return title.contains("Won")
                || title.contains("Died")
                || title.contains("DEFEAT")
                || title.contains("VICTORY")
                || title.contains("Draw");
    }

    @Override
    public boolean GameStart(String title) {
        return title.contains("FIGHT");
    }
}
