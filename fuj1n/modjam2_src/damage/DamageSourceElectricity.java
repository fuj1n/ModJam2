package fuj1n.modjam2_src.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class DamageSourceElectricity extends DamageSource {

	public DamageSourceElectricity() {
		super("electrical");
		this.setDamageBypassesArmor();
	}

	@Override
	public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase) {
		EntityLivingBase entitylivingbase1 = par1EntityLivingBase.func_94060_bK();
		String s = "death.attack." + this.damageType;
		String s1 = s + ".player";
		return entitylivingbase1 != null && StatCollector.func_94522_b(s1) ? ChatMessageComponent.func_111082_b(s1, new Object[] { par1EntityLivingBase.getTranslatedEntityName(), entitylivingbase1.getTranslatedEntityName() }) : ChatMessageComponent.func_111082_b(s, new Object[] { par1EntityLivingBase.getTranslatedEntityName() });
	}

}
