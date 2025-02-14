
package net.mcreator.mkstech.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.mkstech.world.inventory.PhoneguiappclockMenu;
import net.mcreator.mkstech.procedures.PhoneguiZnachieniieProcedure;
import net.mcreator.mkstech.network.PhoneguiappclockButtonMessage;
import net.mcreator.mkstech.MksTechMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class PhoneguiappclockScreen extends AbstractContainerScreen<PhoneguiappclockMenu> {
	private final static HashMap<String, Object> guistate = PhoneguiappclockMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_day;
	Button button_night;
	ImageButton imagebutton_home_button;

	public PhoneguiappclockScreen(PhoneguiappclockMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 350;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("mks_tech:textures/screens/smartphone_ui_backgr.png"));
		this.blit(ms, this.leftPos + 2, this.topPos + 23, 0, 0, 180, 300, 180, 300);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack,

				PhoneguiZnachieniieProcedure.execute(world), 46, 67, -1);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		button_day = new Button(this.leftPos + 30, this.topPos + 79, 40, 20, new TranslatableComponent("gui.mks_tech.phoneguiappclock.button_day"), e -> {
			if (true) {
				MksTechMod.PACKET_HANDLER.sendToServer(new PhoneguiappclockButtonMessage(0, x, y, z));
				PhoneguiappclockButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_day", button_day);
		this.addRenderableWidget(button_day);
		button_night = new Button(this.leftPos + 102, this.topPos + 79, 51, 20, new TranslatableComponent("gui.mks_tech.phoneguiappclock.button_night"), e -> {
			if (true) {
				MksTechMod.PACKET_HANDLER.sendToServer(new PhoneguiappclockButtonMessage(1, x, y, z));
				PhoneguiappclockButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_night", button_night);
		this.addRenderableWidget(button_night);
		imagebutton_home_button = new ImageButton(this.leftPos + 66, this.topPos + 311, 50, 9, 0, 0, 9, new ResourceLocation("mks_tech:textures/screens/atlas/imagebutton_home_button.png"), 50, 18, e -> {
			if (true) {
				MksTechMod.PACKET_HANDLER.sendToServer(new PhoneguiappclockButtonMessage(2, x, y, z));
				PhoneguiappclockButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:imagebutton_home_button", imagebutton_home_button);
		this.addRenderableWidget(imagebutton_home_button);
	}
}
