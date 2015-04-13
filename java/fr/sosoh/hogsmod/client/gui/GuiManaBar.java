package fr.sosoh.hogsmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.sosoh.hogsmod.common.entity.props.ExtendedEntityProps;

@SideOnly(Side.CLIENT)
public class GuiManaBar extends Gui{
	
	private Minecraft mc;
	private static final ResourceLocation texturemana = new ResourceLocation("hogsmod", "textures/gui/mana_barre.png");
	public int xPos = 100, yPos = 23;
	
	public GuiManaBar(Minecraft mc)
	{
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{
			return;
		}
	
		/** Start of my tutorial */
		
		// Get our extended player properties and assign it locally so we can easily access it
		ExtendedEntityProps props = ExtendedEntityProps.get(this.mc.thePlayer);
		
		// If for some reason these properties don't exist (perhaps in multiplayer?)
		// or the player doesn't have mana, return. Note that I added a new method
		// 'getMaxMana()' to ExtendedPlayer for this purpose
		if (props == null || props.getMaxMana() == 0)
		{
			return;
		}
	
		// Starting position for the mana bar - 2 pixels from the top left corner.
		
		
		// The center of the screen can be gotten like this during this event:
		// int xPos = event.resolution.getScaledWidth() / 2;
		// int yPos = event.resolution.getScaledHeight() / 2;
		
		// Be sure to offset based on your texture size or your texture will not be truly centered:
		// int xPos = (event.resolution.getScaledWidth() + textureWidth) / 2;
		// int yPos = (event.resolution.getScaledHeight() + textureHeight) / 2;
		
		// setting all color values to 1.0F will render the texture as it looks in your texture file
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Somewhere in Minecraft vanilla code it says to do this because of a lighting bug
		GL11.glDisable(GL11.GL_LIGHTING);
		
		// Bind your texture to the render engine
		this.mc.getTextureManager().bindTexture(texturemana);
		
		/*
		The parameters for drawTexturedModalRect are as follows:
		
		drawTexturedModalRect(int x, int y, int u, int v, int width, int height);
		
		x and y are the on-screen position at which to render.
		u and v are the coordinates of the most upper-left pixel in your texture file from which to start drawing.
		width and height are how many pixels to render from the start point (u, v)
		*/
		// First draw the background layer. In my texture file, it starts at the upper-
		// left corner (x=0, y=0), ends at 50 pixels (so it's 51 pixels long) and is 4 pixels thick (y value)
		this.drawTexturedModalRect(xPos, yPos, 0, 0, 139, 7);
		// Then draw the foreground; it's located just below the background in my
		// texture file, so it starts at x=0, y=4, is only 2 pixels thick and 49 length
		// Why y=4 and not y=5? Y starts at 0, so 0,1,2,3 = 4 pixels for the background
		
		// However, we want the length to be based on current mana, so we need a new variable:
		int manabarwidth = (int)(((float) props.getMana() / props.getMaxMana()) * 140);
		// Now we can draw our mana bar at yPos+1 so it centers in the background:
		this.drawTexturedModalRect(xPos, yPos + 1, 0, 7, manabarwidth, 5);	
	}
}
