package fr.sosoh.hogsmod.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

@SideOnly(Side.CLIENT)
public class GuiHealthBar extends Gui {

	private Minecraft mc;
	private static final ResourceLocation texturehealth = new ResourceLocation("hogsmod", "textures/gui/vie_barre.png");
	public int xPos = 100, yPos = 15;
	
	public GuiHealthBar(Minecraft mc)
	{
		super();
		this.mc = mc;
	}
	
	public int getXPos(){
		return this.xPos;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		if(event.type == ElementType.HEALTH){
			event.setCanceled(true);
		}
		
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{
			return;
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Somewhere in Minecraft vanilla code it says to do this because of a lighting bug
		GL11.glDisable(GL11.GL_LIGHTING);
		
		// Bind your texture to the render engine
		this.mc.getTextureManager().bindTexture(texturehealth);
		
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
		int viebarwidth = (int)(((float) this.mc.thePlayer.getHealth() / this.mc.thePlayer.getMaxHealth()) * 140);
		// Now we can draw our mana bar at yPos+1 so it centers in the background:
		this.drawTexturedModalRect(xPos, yPos + 1, 0, 7, viebarwidth, 5);
	}
	
}
