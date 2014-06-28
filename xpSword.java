package xpMod;
 
import java.awt.Component;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
 
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Key;

import org.lwjgl.input.Keyboard;
import net.minecraft.block.BlockContainer;
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
 
public class xpSword extends Item
{
    public xpSword() 
    {
        super();
        this.setMaxDamage(40);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.maxStackSize = 1;
        this.setUnlocalizedName("xpSword");
        
    }
    
   
    
    
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        itemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }
    
    
    
                                                                                                     //NON FUNZIONA
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) 
    {
    	
    	
    	itemStack.stackTagCompound.getCompoundTag("xpStored");
        itemStack.stackTagCompound.setString("owner", player.getDisplayName());
    	double store=itemStack.stackTagCompound.getDouble("xpStored");
    	System.out.println("XPSTORED IN XPSWORD PRIMA DEL SETTAGGIO= "+store);
    	itemStack.stackTagCompound.setDouble("xpStored", store-100);
    	store=itemStack.stackTagCompound.getDouble("xpStored"); // DA TOGLIERE
    	System.out.println("XPSTORED IN XPSWORD DOPO SETTAGGIO= "+store);
    
    	
    }
    
    
 
    
   
    
    public NBTTagCompound stackTagCompound;
    public double maxStore=2000;
    public double relativeMaxStore;
    public IIcon[] Icons;
    public double xpStored;
    public double xp;
    
    
}






    






    
    