package fr.herllox.hroulette.utils;

import fr.herllox.hroulette.HRoulette;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.*;

public class inventoryUtil {
    
    private static FileConfiguration config = HRoulette.getInstance().getConfig();
    
    public static Inventory mainInventory(){
        
        Inventory inv = Bukkit.createInventory(null, 54, config.getString("settings.inventory.menu").replace("&","§"));
        rdmInv(inv);
        
        ItemStack green = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta greenx = green.getItemMeta();
        greenx.setDisplayName(config.getString("settings.inventory.money-add").replace("&","§"));
        greenx.setLore(Util.getLore("settings.inventory.money-add-lore"));
        green.setItemMeta(greenx);
        inv.setItem(47, green);

        ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta redx = red.getItemMeta();
        redx.setDisplayName(config.getString("settings.inventory.money-remove").replace("&","§"));
        redx.setLore(Util.getLore("settings.inventory.money-remove-lore"));
        red.setItemMeta(redx);
        inv.setItem(51, red);

        ItemStack select = new ItemStack(Material.BOOK);
        ItemMeta selectx = select.getItemMeta();
        selectx.setDisplayName(config.getString("settings.inventory.select").replace("&","§"));
        selectx.setLore(Util.getLore("settings.inventory.select-lore"));
        select.setItemMeta(selectx);
        inv.setItem(49, select);

        inv.setItem(13, selectTop());
        inv.setItem(31, selectBot());

        return inv;

    }

    private static void rdmInv(Inventory inv){
        ItemStack white = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta whitex = white.getItemMeta();
        whitex.setDisplayName("&e");
        whitex.setLore(Arrays.asList("&e"));
        white.getItemMeta();

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(9, 10,11,12,13,14,15,16,17,27,28,29,30,31,32,33,34,35));

        ItemStack orange = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta orangex = orange.getItemMeta();
        orangex.setDisplayName("&e");
        orangex.setLore(Arrays.asList("&e"));
        orange.getItemMeta();

        for(int i = 0; i < 54; i++) {
            if(list.contains(i)){
                inv.setItem(i,white);
            }else{
                inv.setItem(i,orange);
            }
        }

        for(int a = 18; a < 27; a++){
            inv.setItem(a, getRdmItem());
        }
    }

    public static void rollInv(Inventory inv, Integer time, Player player){
        if(time == 1){
            switchItems(inv, player);
        }else if(time == 3){
            switchItems(inv, player);
        }else if(time == 5){
            switchItems(inv, player);
        }else if(time == 7){
            switchItems(inv, player);
        }else if(time == 9){
            switchItems(inv, player);
        }else if(time == 11){
            switchItems(inv, player);
        }else if(time == 13){
            switchItems(inv, player);
        }else if(time == 15){
            switchItems(inv, player);
        }else if(time == 17){
            switchItems(inv, player);
        }

    }

    private static void switchItems(Inventory inv, Player p){
        inv.setItem(18, inv.getItem(19));
        inv.setItem(19, inv.getItem(20));
        inv.setItem(20, inv.getItem(21));
        inv.setItem(21, inv.getItem(22));
        inv.setItem(22, inv.getItem(23));
        inv.setItem(23, inv.getItem(24));
        inv.setItem(24, inv.getItem(25));
        inv.setItem(25, inv.getItem(26));
        inv.setItem(26, getRdmItem());
        p.updateInventory();
    }

    private static ItemStack getRdmItem(){

        ItemStack lime = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta limex = lime.getItemMeta();
        limex.setDisplayName("&a");
        limex.setLore(Arrays.asList("&e"));
        lime.getItemMeta();

        ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta redx = red.getItemMeta();
        redx.setDisplayName("&a");
        redx.setLore(Arrays.asList("&e"));
        red.getItemMeta();

        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackx = black.getItemMeta();
        blackx.setDisplayName("&a");
        blackx.setLore(Arrays.asList("&e"));
        black.getItemMeta();

        Random random = new Random();
        Integer x = random.nextInt(100);

        if(x <= 2){
            return lime;
        }else if(x > 2 && x <= 51){
            return red;
        }else if(x > 51 && x <= 100 ){
            return black;
        }
        return null;
    }

    private static ItemStack selectTop(){

        ItemStack it = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta)it.getItemMeta();
        String url = "http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf";
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException var8) {
            var8.printStackTrace();
        }

        it.setItemMeta(itemMeta);
        return it;

    }

    private static ItemStack selectBot(){

        ItemStack it = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta)it.getItemMeta();
        String url = "http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10";
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException var8) {
            var8.printStackTrace();
        }

        it.setItemMeta(itemMeta);
        return it;

    }

}
