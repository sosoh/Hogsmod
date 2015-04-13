package fr.sosoh.hogsmod.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fr.sosoh.hogsmod.client.models.items.ModelElderWand;

public class ItemElderWandRender implements IItemRenderer{

	protected ModelElderWand model;
	protected static final ResourceLocation texture = new ResourceLocation("hogsmod", "textures/items/ElderWand.png"); 
	public ItemElderWandRender()
	{
		model = new ModelElderWand();
	}

	@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch(type)
        {
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case INVENTORY:
                return true;
            case ENTITY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case EQUIPPED:
            {
            	GL11.glPushMatrix();
                GL11.glRotatef(-70F, -1F, 0.0F, 0.0F);
                GL11.glTranslatef(0.4F, -1.2F, -0.5F);
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED_FIRST_PERSON:
            {
            	GL11.glPushMatrix();
                GL11.glRotatef(-100F, -0.5F, 0F, 0F);
                GL11.glTranslatef(0.5F, -1.6F, -0.7F);
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            case INVENTORY:
            {
            	GL11.glPushMatrix();
                GL11.glTranslatef(25F, -6F, 0.0F);
                GL11.glRotatef(50F, 0F, 0.0F, 1F);
                GL11.glRotatef(90F, 0.0F, 1F, 0.0F);
                GL11.glScalef(17F, 17F, 17F);
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            case ENTITY:
            {
            	GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glTranslatef(0.1F, -1.9F, 0.0F);
                GL11.glScalef(1.4F, 1.4F, 1.4F);
                GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
                model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            }
            default:
                break;
        }
    }
}
