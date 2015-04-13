package fr.sosoh.hogsmod.common.event;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.sosoh.hogsmod.common.entity.props.ExtendedEntityProps;
import fr.sosoh.hogsmod.proxy.CommonProxy;

public class EventHandlerExtendedProps {

	private CommonProxy proxy;
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) 
	{
		if (event.entity instanceof EntityPlayer && ExtendedEntityProps.get((EntityPlayer) event.entity) == null)
			ExtendedEntityProps.register((EntityPlayer) event.entity);
	}
	
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) 
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) 
		{
			NBTTagCompound playerData = new NBTTagCompound();
			((ExtendedEntityProps) (event.entity.getExtendedProperties(ExtendedEntityProps.EXT_PROP_NAME))).saveNBTData(playerData);
			proxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName(), playerData);
			ExtendedEntityProps.saveProxyData((EntityPlayer) event.entity);
		}
		else 
		{

		}
	}
 
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) 
		{
			NBTTagCompound playerData = proxy.getEntityData(((EntityPlayer) event.entity).getDisplayName());
			if (playerData != null) 
			{
				((ExtendedEntityProps) (event.entity.getExtendedProperties(ExtendedEntityProps.EXT_PROP_NAME))).loadNBTData(playerData);
			}

			((ExtendedEntityProps) (event.entity.getExtendedProperties(ExtendedEntityProps.EXT_PROP_NAME))).sync();
		}
    }
	
}
