package net.auto.gg;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import java.util.List;

public class ServerDetector {

    public static boolean isMcPVP() {
        MinecraftClient client = MinecraftClient.getInstance();
        ServerInfo entry = client.getCurrentServerEntry();
        if (entry == null) return false;

        String IpAddress = entry.address.toLowerCase().split(":")[0];

        //noinspection ConditionCoveredByFurtherCondition
        return IpAddress.equals("mcpvp.club") || IpAddress.endsWith("mcpvp.club");
    }

    public static boolean isSupportedServer() {
        return isMcPVP();
    }
}
