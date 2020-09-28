package fr.hillwalk.death.utils;

import fr.hillwalk.death.Death;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public class UtilsRef {

    Death instance = JavaPlugin.getPlugin(Death.class);


    public boolean checkPlayer(UUID uuid){

        File file = new File(Bukkit.getServer().getPluginManager().getPlugin(instance.getName()).getDataFolder() + File.separator + "players", uuid + ".yml");

        for (Player player : Bukkit.getServer().getOnlinePlayers()){

            if(!file.exists()){

                return false;
            }

        }


        return true;
    }



}
