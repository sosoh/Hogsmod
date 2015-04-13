package fr.sosoh.hogsmod.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.sosoh.hogsmod.common.entity.props.ExtendedEntityProps;

public class PacketMana implements IMessage
{
	
	private static int mana;
	private static int manaMax;
	
	public PacketMana()
	{
		
	}

	public PacketMana(int mana, int manaMax)
	{
		this.mana = mana;
		this.manaMax = manaMax;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.mana = buf.readInt();
		this.manaMax = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(mana);
		buf.writeInt(manaMax);
	}
	public static class Handler implements IMessageHandler<PacketMana, IMessage>
	{

		@Override
		public IMessage onMessage(PacketMana message, MessageContext ctx) 
		{
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			ExtendedEntityProps props = ExtendedEntityProps.get(player);	
			props.mana = mana;
			props.maxMana = manaMax;
			return null;
		}
		
	}
}
