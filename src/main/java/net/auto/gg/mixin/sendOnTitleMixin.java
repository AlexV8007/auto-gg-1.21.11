package net.auto.gg.mixin;

import net.auto.gg.ModConfig;
import net.auto.gg.ServerDetector;
import net.auto.gg.server.ServerHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.network.ClientPlayNetworkHandler.class)
public class sendOnTitleMixin {

	@Unique
	private String lastTitle = "";

	@Inject(method = "onTitle", at = @At("HEAD"))
	private void onTitle(TitleS2CPacket packet, CallbackInfo ci) throws InterruptedException {

		if (!ModConfig.INSTANCE.active) return;

		ServerHandler handler = ServerDetector.current();
		if (handler == null) return;

		String currentTitle = packet.text().getString();
		if (currentTitle.equals(lastTitle)) return;

		lastTitle = currentTitle;

		if (handler.GameEnd(currentTitle)) {
			assert MinecraftClient.getInstance().player != null;
			Thread.sleep((long) (ModConfig.INSTANCE.delayAtEnd * 1000));
			MinecraftClient.getInstance().player.networkHandler.sendChatMessage(ModConfig.INSTANCE.atEnd);

		} else if (handler.GameStart(currentTitle) && ModConfig.INSTANCE.sendAtStart) {
			assert MinecraftClient.getInstance().player != null;
			Thread.sleep((long) (ModConfig.INSTANCE.delayAtStart * 1000));
			MinecraftClient.getInstance().player.networkHandler.sendChatMessage(ModConfig.INSTANCE.atStart);

			}
	}
}