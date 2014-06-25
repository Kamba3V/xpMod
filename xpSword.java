package xpMod;
 
import java.util.List;
 
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
 
public class xpSword extends Item implements ITileEntityProvider
{
    public xpSword() 
    {
        
        this.setUnlocalizedName("tutorialItem");
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
   
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
 
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[3];
 
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon(mainclass.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
        }
    }
 
    public static final String[] names = new String[] { "first", "second","third" };
 
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + names[i];
    }
 
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int x = 0; x < 3; x++)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }
    
    //TILEENTITY*********************************************************************************************
    
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
    {
    	xpContainer t= new xpContainer();
    	t.ItemActivated(par1ItemStack, par2World, player);
    	return par1ItemStack;
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		try
	    {
	        return new xpContainer();
	    }
	    catch (Exception var3)
	    {
	        throw new RuntimeException(var3);
	    }
	}


	
    
    
    
    
}