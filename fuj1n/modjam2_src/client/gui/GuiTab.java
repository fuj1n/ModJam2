package fuj1n.modjam2_src.client.gui;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fuj1n.modjam2_src.Helper;

public class GuiTab extends GuiButton {

	Minecraft mc = Minecraft.getMinecraft();
	RenderItem itemRenderer = new RenderItem();
	String tabTitle;
	List stringList;
	GuiContainer parent;
	int lastX, lastY;
	
    protected static final ResourceLocation background = new ResourceLocation("securemod:textures/gui/button_tab.png");
	
	public GuiTab(int par1, int par2, int par3, String par6String, List par7List, String tabTitle, GuiContainer parentGui) {
		super(par1, par2, par3, 28, 30, par6String);
		this.width = 28;
		this.height = 30;
		this.stringList = par7List;
		this.tabTitle = tabTitle;
		this.parent = parentGui;
	}

	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		if (this.drawButton) {
            this.lastX = par2;
            this.lastY = par3;
			FontRenderer fontrenderer = par1Minecraft.fontRenderer;
			par1Minecraft.func_110434_K().func_110577_a(background);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_82253_i);

			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);
//			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 0, 0, this.width / 2, this.height);
			this.mouseDragged(par1Minecraft, par2, par3);
			int l = 0xAAAAAA;
			
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}

	public void passTooltip(){
		this.field_82253_i = lastX >= this.xPosition && lastY >= this.yPosition && lastX < this.xPosition + this.width && lastY < this.yPosition + this.height;
		if (field_82253_i && this.drawButton) {
			drawItemStackTooltip(Helper.copyList(stringList), lastX - 125, lastY - 10);
		}
	}
	
	protected void drawItemStackTooltip(List list, int par2, int par3) {
		for (int k = 0; k < list.size(); ++k) {
			if (k == 0) {
				list.set(k, list.get(k));
			} else {
				list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
			}
		}
		drawHoveringText(list, par2, par3, mc.fontRenderer);
	}

	protected void drawHoveringText(List par1List, int par2, int par3, FontRenderer font) {
		float zl = this.zLevel;
		if (!par1List.isEmpty()) {
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int k = 0;
			Iterator iterator = par1List.iterator();

			while (iterator.hasNext()) {
				String s = (String) iterator.next();
				int l = font.getStringWidth(s);

				if (l > k) {
					k = l;
				}
			}

			int i1 = par2 + 12;
			int j1 = par3 - 12;
			int k1 = 8;

			if (par1List.size() > 1) {
				k1 += 2 + (par1List.size() - 1) * 10;
			}

			if (i1 + k > parent.width) {
				i1 -= 28 + k;
			}

			if (j1 + k1 + 6 > parent.height) {
				j1 = parent.height - k1 - 6;
			}

            this.zLevel = 300.0F;
            itemRenderer.zLevel = 300.0F;
			int l1 = -267386864;
			this.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
			this.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
			this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
			this.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
			this.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
			int i2 = 1347420415;
			int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
			this.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
			this.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
			this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
			this.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

			for (int k2 = 0; k2 < par1List.size(); ++k2) {
				String s1 = (String) par1List.get(k2);
				font.drawStringWithShadow(s1, i1, j1, -1);

				if (k2 == 0) {
					j1 += 2;
				}

				j1 += 10;
			}

            this.zLevel = zl;
            itemRenderer.zLevel = 0.0F;
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderHelper.enableStandardItemLighting();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}

}
