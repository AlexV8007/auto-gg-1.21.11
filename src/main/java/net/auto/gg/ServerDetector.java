package net.auto.gg;

import net.auto.gg.server.McPvp;
import net.auto.gg.server.ServerHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import java.util.List;

public class ServerDetector {

    private static final List<ServerHandler> HANDLERS = List.of(
            new McPvp()
    );

    public static ServerHandler current() {
        ServerInfo entry = MinecraftClient.getInstance().getCurrentServerEntry();
        if (entry == null) return null;

        String ip = entry.address.toLowerCase().split(":")[0];
        for (ServerHandler h : HANDLERS) {
            if (h.matchesIp(ip)) return h;
        }
        return null;
    }
}
