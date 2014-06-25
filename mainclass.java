package xpMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
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
	public static Item tutorialItem;
	

        // The instance of your mod that Forge uses.
        @Instance(value = "xpModID")
        public static mainclass instance;
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="xpMod.client.ClientProxy", serverSide="xpMod.CommonProxy")
         public static CommonProxy proxy;
       
        @EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
              
        tutorialItem=new xpSword();
        GameRegistry.registerItem(tutorialItem, "ExpSword", "xpSword");
        LanguageRegistry.addName(new ItemStack(tutorialItem, 1, 0), "xpSword");
		LanguageRegistry.addName(new ItemStack(tutorialItem, 1, 1), "MediumXpSword");
		LanguageRegistry.addName(new ItemStack(tutorialItem, 1, 2), "FinalxpSword");
        	
        	
        }
       
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
        }
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}