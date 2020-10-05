package fr.hillwalk.death.listener;

import fr.hillwalk.death.Death;
import fr.hillwalk.death.config.CustomPotions;
import fr.hillwalk.death.runnable.PotionsEffects;
import fr.hillwalk.death.runnable.PotionsEffectsAfterMilk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ConsumeEvent implements Listener {


    public static List<PotionEffect> listEffects = new ArrayList<PotionEffect>();


    Death instance = JavaPlugin.getPlugin(Death.class);

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerConsume(PlayerItemConsumeEvent e){
        PotionsEffectsAfterMilk task = new PotionsEffectsAfterMilk(e.getPlayer());

        int miracle = 0;

        if(e.getItem().getType() == Material.MILK_BUCKET){

            for (PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
                if(!listEffects.contains(effect)){
                    listEffects.add(effect);

                }

            }

            task.runTaskLater(instance, 0);
        }


    }




}
