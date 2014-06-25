package xpMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class xpContainer extends TileEntity{
	
	public xpContainer()
	{}
	
	public void ItemActivated(ItemStack par1ItemStack, World par2World, EntityPlayer player)
    {
		System.out.println("PLAYER.EXPERIENCE: "+player.experience);
		System.out.println("PLAYER.EXPERIENCETOT: "+player.experienceTotal);
		System.out.println("PLAYER.EXPERIENCELEV: "+player.experienceLevel);
		
		int level=player.experienceLevel;
		
		if(level>31)
			xp= 3.5F*level*level - 151.5F*level + 2220;
		else if (level>=16 && level <=31)
			xp= 1.5F*level*level - 29.5F*level + 360;
		else 
			xp=17F*level;
		
		xpStored=xpStored+xp/2;
		EXPSTORED=xpStored;
		System.out.println("XPSTORED: " + EXPSTORED);
		System.out.println("XP: " + xp);
		player.addExperienceLevel(-1);
        
    }
	
	
	@Override
    public void readFromNBT(NBTTagCompound nbt)
	{
		EXPSTORED+=nbt.getFloat("EXPSTORED");
	}
	
	@Override
    public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setFloat("EXPSTORED", EXPSTORED);
		
	}
	
    
    private float xp;
    private float xpStored;
    private float EXPSTORED;

}
