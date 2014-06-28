package xpMod;
 
import java.awt.Component;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import org.lwjgl.input.Keyboard;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.SlotCrafting;
 
public class xpContainer extends Item
{
    public xpContainer() 
    {
    	super();
        this.setUnlocalizedName("xpContainer");
        this.setCreativeTab(CreativeTabs.tabTools);
        this.maxStackSize = 1;
        this.setNoRepair();
        this.setContainerItem(this);     
        
    }
    
    public boolean getShareTag()
    {
        return true;
    }
 
    
    
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
    	double stored=itemStack.stackTagCompound.getDouble("xpStored");
    	if ( stored > 10000 && stored < 11000 || stored<200) return true;
        return false;
    }
    
    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
    	//double stored=itemStack.stackTagCompound.getDouble("xpStored");
    	
    	/*if(isSmall(itemStack))
    	{
        ItemStack copiedStack = itemStack.copy();
        copiedStack.stackTagCompound.setDouble("xpStored", stored-100);
        copiedStack.stackSize = 1;
        return copiedStack;
    	}
    	
    	else if(isMedium(itemStack))
    	{
            ItemStack copiedStack = itemStack.copy();
            copiedStack.stackTagCompound.setDouble("xpStored", stored-1000);
            copiedStack.stackSize = 1;
            return copiedStack;
        }
    	
    	else if(isFinal(itemStack))
    	{
            ItemStack copiedStack = itemStack.copy();
            copiedStack.stackTagCompound.setDouble("xpStored", stored-10000);
            copiedStack.stackSize = 1;
            return copiedStack;
        }
    	
    	return itemStack;*/
    	
    	ItemStack copiedStack = itemStack.copy();
        //copiedStack.stackTagCompound.setDouble("xpStored", stored);
        copiedStack.stackSize = 1;
        return itemStack;
    }
   
    
    public void onUpdate(ItemStack itemStack, World par2World, Entity player, int par4, boolean par5) 
    {
    	
    	// Caso in cui il container sia preso dalla creative
    	if( itemStack.stackTagCompound == null ) {
    		
    		itemStack.stackTagCompound = new NBTTagCompound();
            itemStack.stackTagCompound.setString("owner", "none");
            itemStack.stackTagCompound.setDouble("xpStored", 0);
    	}
    	
    	//Caso in cui l'oggetto sia craftato
    	double stored=itemStack.stackTagCompound.getDouble("xpStored");
    	
    	if (isFinal(itemStack)) 
    		{
    			itemStack.setItemDamage(3);
    			metadata=3;
    		}
		
		else if (isMedium(itemStack))
			{
				itemStack.setItemDamage(2);
				relativeMaxStore=100000;
				metadata=2;
			}
		else if (isSmall(itemStack))
		{
			itemStack.setItemDamage(1);
			relativeMaxStore=100000;
			metadata=1;
		}
		
		else 
			{
				relativeMaxStore=10000;
				itemStack.setItemDamage(0);
				metadata=0;
			}
    	
    }
    
    
    
    public String[] Names= new String[] {"Void","Initial", "Medium", "Final"};
    
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 3);
        return super.getUnlocalizedName() + "." + Names[i];
    }
    
    
    
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) 
    {
    	
    	
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setString("owner", player.getDisplayName());
        itemStack.stackTagCompound.setDouble("xpStored", 0);
    }
    
    
    
    public  ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer player)
    {
    	System.out.println(this.getDamage(itemStack));
    	//PREMERE LSHIFT-Right- click per accumulare exp nella spada
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) 
    	{

			double stored=itemStack.stackTagCompound.getDouble("xpStored");
			System.out.println("XPSTORED IN XPCONTAINER= "+stored);
			
			if (stored<= maxStore)
			{
		    	
		    	int level=player.experienceLevel;
				
				if(level>31)
				{
					xp= 3.5*level*level - 151.5*level + 2220;
				}
				else if (level>=16 && level <=31)
				{
					
					xp= 1.5*level*level - 29.5*level + 360;
				}
				else 
				{
					xp=17*level;
				}
				
				itemStack.stackTagCompound.setDouble("xpStored", (stored+xp));
				
				player.addExperienceLevel(-1);
			}
    	}
		
		return itemStack;
        
    }
    
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) 
    {
    
    	if (itemStack.stackTagCompound != null) 
    	{
    		double stored=itemStack.stackTagCompound.getDouble("xpStored");
    		
    		String owner = itemStack.stackTagCompound.getString("owner");
            double store = itemStack.stackTagCompound.getDouble("xpStored");
           
            list.add("owner: " + owner);
            if (stored>=maxStore) list.add(EnumChatFormatting.RED + "xpStored: " + maxStore + " / "+maxStore);
            else list.add(EnumChatFormatting.GREEN + "xpStored: " + store + " / "+relativeMaxStore);
           
    	}
    }
    
    
    

 // ICON*********************************************************************************************************************************+  
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
           Icons = new IIcon[Names.length];

            for (int i = 0; i < Icons.length; ++i)
            {
                    Icons[i] = par1IconRegister.registerIcon("xpmod" + ":" + Names[i]);
            }
    }
    
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int x = 0; x < 4; x++)
		{
			par3List.add(new ItemStack(this, 1, x));
		}
	}
    
    public IIcon getIconFromDamage(int par1)
    {
    int j = MathHelper.clamp_int(par1, 0, 4);
    return this.Icons[j];
    }
//CONDIZIONI*****************************************************************************************************************************************
    
    public boolean isSmall(ItemStack itemStack)
    {
    	double stored=itemStack.stackTagCompound.getDouble("xpStored");
    	if (stored>100 && stored<10000) return true;
    	return false;
    }
    
    public boolean isMedium(ItemStack itemStack)
    {
    	double stored=itemStack.stackTagCompound.getDouble("xpStored");
    	if (stored>10000 && stored<100000) return true;
    	return false;
    }
    
    public boolean isFinal(ItemStack itemStack)
    {
    	double stored=itemStack.stackTagCompound.getDouble("xpStored");
    	if (stored>100000) return true;
    	return false;
    }
    
    public int metadata;
    public NBTTagCompound stackTagCompound;
    public double maxStore=100000;
    public double relativeMaxStore;
    public IIcon[] Icons;
    public double xpStored;
    public double xp;
   
    //public ItemStack xpCont= new ItemStack(this,1,metadata);
   
    
}






    
