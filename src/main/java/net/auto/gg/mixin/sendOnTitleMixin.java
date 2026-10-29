package net.auto.gg.mixin;

import net.auto.gg.ModConfig;
import net.auto.gg.SendMessage;
import net.auto.gg.ServerDetector;
import net.auto.gg.server.ServerHandler;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.network.ClientPlayNetworkHandler.class)
public class sendOnTitleMixin {

	@Unique private static final long COOLDOWN = 3000;
	@Unique private static long lastEndTime = 0;
	@Unique private static long lastStartTime = 0;


	@Inject(method = "onTitle", at = @At("HEAD"))
	private void autogg$onTitle(TitleS2CPacket packet, CallbackInfo ci) {

		if (!ModConfig.INSTANCE.active) return;

		ServerHandler handler = ServerDetector.current();
		if (handler == null) return;

		String currentTitle = packet.text().getString();

		long now = System.currentTimeMillis();

		if (handler.GameEnd(currentTitle)) {
			if (now - lastEndTime < COOLDOWN) return;
			lastEndTime = now;
			SendMessage.sendLater(ModConfig.INSTANCE.atEnd, ModConfig.INSTANCE.delayAtEnd);
		} else if (handler.GameStart(currentTitle) && ModConfig.INSTANCE.sendAtStart) {
			if (now - lastStartTime < COOLDOWN) return;
			lastStartTime = now;
			SendMessage.sendLater(ModConfig.INSTANCE.atStart, ModConfig.INSTANCE.delayAtStart);
			}
	}
}