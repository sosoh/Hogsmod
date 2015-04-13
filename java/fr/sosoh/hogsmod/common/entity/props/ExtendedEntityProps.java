package fr.sosoh.hogsmod.common.entity.props;

import fr.sosoh.hogsmod.common.Hogsmod;
import fr.sosoh.hogsmod.common.packet.PacketMana;
import fr.sosoh.hogsmod.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntityProps implements IExtendedEntityProperties
{

	public final static String EXT_PROP_NAME = "ExtHogs";

	private final EntityPlayer player;

	public int mana;
    public int maxMana;
	
    public ExtendedEntityProps(EntityPlayer player) 
    {
		this.player = player;
		this.mana = 100;
		this.maxMana = 100;
	}
    

	@Override
	public void init(Entity entity, World world) 
	{
		
	}
	
	public static final void register(EntityPlayer player) 
	{
		player.registerExtendedProperties(ExtendedEntityProps.EXT_PROP_NAME,
		new ExtendedEntityProps(player));
	}

	public static final ExtendedEntityProps get(EntityPlayer player) 
	{
		return (ExtendedEntityProps) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{

		NBTTagCompound properties = new NBTTagCompound();
		compound.setTag(EXT_PROP_NAME, properties);
		properties.setInteger("Mana", this.mana);
		properties.setInteger("MaxMana", this.maxMana);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.mana = properties.getInteger("Mana");
		this.maxMana = properties.getInteger("MaxMana");
	}

	
	public final void sync() 
	{
		PacketMana packetMana = new PacketMana(this.mana, this.maxMana);

		if (!player.worldObj.isRemote) 
		{
			EntityPlayerMP player1 = (EntityPlayerMP) player;
			Hogsmod.network.sendTo(packetMana, player1);
		}
	}
	
	private static String getSaveKey(EntityPlayer player) 
	{
		return player.getDisplayName() + ":" + EXT_PROP_NAME;
	}
	
	public static void saveProxyData(EntityPlayer player) {
		ExtendedEntityProps playerData = ExtendedEntityProps.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	public static void loadProxyData(EntityPlayer player) 
	{
		ExtendedEntityProps playerData = ExtendedEntityProps.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

		if (savedData != null) 
		{
			playerData.loadNBTData(savedData);
		}
		playerData.sync();
	}
	
	public boolean removeMana(int amount) 
	{
		boolean sufficient = amount <= this.mana;

		if (sufficient) 
		{
			this.mana -= amount;
			this.sync();
		} else 
		{
			return false;
		}

		return sufficient;
	}

	public void addMana(int amount) 
	{
		this.mana += amount;
		this.sync();
	}

	public int getMana() 
	{
		return this.mana;
	}

	public void setMana(int newMana) 
	{
		this.mana = newMana;
		this.sync();
	}


	public int getMaxMana() {
		return this.maxMana;
	} 

}
