package fr.sosoh.hogsmod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import fr.sosoh.hogsmod.common.entity.props.ExtendedEntityProps;

public class ItemElderWand extends Item
{
	public ItemElderWand(){
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,EntityPlayer player) {
		
		ExtendedEntityProps props = ExtendedEntityProps.get(player);
		props.removeMana(10);
		return itemstack;
	}
}
