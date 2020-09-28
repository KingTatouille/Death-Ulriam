package fr.hillwalk.death;

import fr.hillwalk.death.commands.Commandes;
import fr.hillwalk.death.config.CustomPotions;
import fr.hillwalk.death.listener.DeathEvent;
import fr.hillwalk.death.listener.HitEvent;
import fr.hillwalk.death.listener.RespawnEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class Death extends JavaPlugin {

    private Logger log;
    private HashMap<UUID, Boolean> hit = new HashMap<UUID, Boolean>();

    public static String prefix;


    public Death(){

        this.log = getLogger();

    }




    @Override
    public void onEnable(){

        log.info("est fonctionnel !");


        //On sauvegarde la config
        saveDefaultConfig();


        //On sauvegarde les potions
        CustomPotions.setup();

        getCommand("ulriam").setExecutor(new Commandes());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new DeathEvent(), this);
        pm.registerEvents(new HitEvent(), this);
        pm.registerEvents(new RespawnEvent(), this);



        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix") + " ");


    }


    @Override
    public void onDisable(){

        log.info("est unloaded!");

    }

    //On prend la hashMap list
    public HashMap<UUID,Boolean> hitList(){


        return hit;
    }


}
