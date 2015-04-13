package fr.sosoh.hogsmod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import fr.sosoh.hogsmod.client.gui.GuiHealthBar;
import fr.sosoh.hogsmod.client.gui.GuiManaBar;
import fr.sosoh.hogsmod.client.render.items.ItemElderWandRender;
import fr.sosoh.hogsmod.common.Hogsmod;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRender()
	{
		MinecraftForgeClient.registerItemRenderer(Hogsmod.itemElderWand, (IItemRenderer)new ItemElderWandRender());
		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new GuiHealthBar(Minecraft.getMinecraft()));
	}
}