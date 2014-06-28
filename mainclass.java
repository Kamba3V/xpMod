package xpMod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="xpModID", name="xpMod", version="0.0.1")
//@NetworkMod(clientSideRequired=true) // not used in 1.7
public class mainclass {
	
	public static String modid="mainclass";
	public static xpContainer tutorialItem;
	public static xpSword ExpSword;
	
	public static final int WILDCARD_VALUE = Short.MAX_VALUE;
	

        // The instance of your mod that Forge uses.
        @Instance(value = "xpModID")
        public static mainclass instance;
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="xpMod.client.ClientProxy", serverSide="xpMod.CommonProxy")
         public static CommonProxy proxy;
       
        @EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
              
        tutorialItem=new xpContainer();
        ExpSword= new xpSword();
        
        
        GameRegistry.registerItem(tutorialItem, "ExpContainer", "xpContainer");
        GameRegistry.registerItem(ExpSword,"BeginnerSword");
        
        
        ItemStack voidContainer=new ItemStack(tutorialItem, 1, 0);
        ItemStack xpContainer=new ItemStack(tutorialItem, 1, 1);
        ItemStack MediumxpContainer= new ItemStack(tutorialItem, 1, 2);
        ItemStack FinalxpContainer = new ItemStack(tutorialItem, 1, 3);
         
        ItemStack ExpSwStack=new ItemStack(ExpSword);
     
        LanguageRegistry.addName(voidContainer, "voidContainer");
   	 	LanguageRegistry.addName(xpContainer, "xpContainer");
        LanguageRegistry.addName(MediumxpContainer, "MediumxpContainer");
   	 	LanguageRegistry.addName(FinalxpContainer, "FinalxpContainer");	
   	 	LanguageRegistry.addName(ExpSword,"BeginnerSword");
   	 	
   	 	
   	 	
   	 	ItemStack dirtStack= new ItemStack(Blocks.dirt);
   	 	GameRegistry.addShapelessRecipe (voidContainer, dirtStack);
   	 	GameRegistry.addShapelessRecipe( ExpSwStack, xpContainer);
   	 	/*GameRegistry.addShapelessRecipe (dirtStack, xpContainer);
   	 	GameRegistry.addShapelessRecipe (dirtStack, MediumxpContainer);*/
   	 	
        	
        }
       
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
        
        
        	 
        	
        		//ItemStack	expStack= new ItemStack(handler);
        		
        		//tutStack.stackTagCompound.setDouble("xpStored", 0);
        		
        		//ItemStack dirtStack= new ItemStack(Blocks.dirt);
        		
        		
        		//GameRegistry.addShapelessRecipe (dirtStack, new ItemStack(handler,1,WILDCARD_VALUE));
        		
        	
        		
        		
        		/*GameRegistry.addRecipe (new ItemStack (tutorialItem,1,0), new Object[]{
        				"XY ",
        				'X',dirtStack,'Y', new ItemStack(tutorialItem,1,1)});
                proxy.registerRenderers();*/
        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}