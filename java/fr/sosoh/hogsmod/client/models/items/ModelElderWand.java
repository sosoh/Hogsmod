package fr.sosoh.hogsmod.client.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelElderWand extends ModelBase
{

    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public ModelElderWand()
    {
    	textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape1.setRotationPoint(0F, 20.36667F, -8F);
        Shape1.setTextureSize(64, 32);
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 36, 0);
        Shape2.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape2.setRotationPoint(-0.5F, 19.86667F, 6F);
        Shape2.setTextureSize(64, 32);
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 36, 0);
        Shape3.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape3.setRotationPoint(-0.5F, 19.9F, 3F);
        Shape3.setTextureSize(64, 32);
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 43, 0);
        Shape4.addBox(0F, 0F, 0F, 3, 3, 1);
        Shape4.setRotationPoint(-1F, 19.5F, 0F);
        Shape4.setTextureSize(64, 32);
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 36, 0);
        Shape5.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape5.setRotationPoint(-0.5F, 19.9F, -2.5F);
        Shape5.setTextureSize(64, 32);
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 31, 0);
        Shape6.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape6.setRotationPoint(0F, 20.1F, -6F);
        Shape6.setTextureSize(64, 32);
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 31, 0);
        Shape7.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape7.setRotationPoint(0F, 20.6F, -6F);
        Shape7.setTextureSize(64, 32);
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 31, 0);
        Shape8.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape8.setRotationPoint(0.3F, 20.35F, -6F);
        Shape8.setTextureSize(64, 32);
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 31, 0);
        Shape9.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape9.setRotationPoint(-0.3F, 20.4F, -6F);
        Shape9.setTextureSize(64, 32);
        setRotation(Shape9, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

