package net.auto.gg;

import net.minecraft.client.MinecraftClient;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendMessage {
    private static final ScheduledExecutorService SCHEDULER =
            Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "auto-gg-scheduler");
                t.setDaemon(true);
                return t;
            });

    public static void sendLater(String message, float delaySeconds) {
        if (message == null || message.isBlank()) return;
        MinecraftClient client = MinecraftClient.getInstance();

        SCHEDULER.schedule(
                () -> client.execute(() -> {
                    if (client.player != null) {
                        client.player.networkHandler.sendChatMessage(message);
                    }
                }),
                (long) (Math.max(0, delaySeconds) * 1000),
                TimeUnit.MILLISECONDS);
    }
}
