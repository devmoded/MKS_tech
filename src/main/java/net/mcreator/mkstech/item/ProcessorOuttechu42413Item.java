
package net.mcreator.mkstech.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import net.mcreator.mkstech.init.MksTechModTabs;

import java.util.List;

public class ProcessorOuttechu42413Item extends Item {
	public ProcessorOuttechu42413Item() {
		super(new Item.Properties().tab(MksTechModTabs.TAB_MKSTECHPCCOMPLECT).stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
		return 0F;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Model: \u00A7lu4_2413"));
	}
}
