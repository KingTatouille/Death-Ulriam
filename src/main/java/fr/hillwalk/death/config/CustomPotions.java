package fr.hillwalk.death.config;

import fr.hillwalk.death.Death;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class CustomPotions implements Listener {

    private static File itemsFiles;
    private static File itemsFilesIn;
    private static FileConfiguration items;



    public static void setup() {
        Death instance = JavaPlugin.getPlugin(Death.class);
        itemsFilesIn = new File(Bukkit.getServer().getPluginManager().getPlugin(instance.getName()).getDataFolder(),"potions");
        itemsFiles = new File(itemsFilesIn, "potions.yml");


        if(!itemsFilesIn.exists()) {

            try {
                instance.saveResource("potions/potions.yml", false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if(!itemsFiles.exists()) {
            try {
                itemsFiles.createNewFile();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        items = YamlConfiguration.loadConfiguration(itemsFiles);


    }


    public static FileConfiguration getItems() {

        return items;
    }


    public static void save() {
        Death instance = JavaPlugin.getPlugin(Death.class);

        try {
            items.save(itemsFiles);
        } catch (IOException e) {

            instance.getLogger().info("Save doesn't work ! We have a problem sir!");
        }
    }

    public static void reload() {
        if (itemsFiles == null) {

            itemsFiles = new File(itemsFilesIn, "potions.yml");

        }

        items = YamlConfiguration.loadConfiguration(itemsFiles);



    }


}
