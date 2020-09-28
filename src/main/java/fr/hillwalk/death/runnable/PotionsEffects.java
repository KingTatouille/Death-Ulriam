package fr.hillwalk.death.runnable;

import fr.hillwalk.death.Death;
import fr.hillwalk.death.config.CustomPotions;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PotionsEffects extends BukkitRunnable {


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    private Player player;

    public PotionsEffects(Player player){

    this.player = player;

    }

    Death instance = JavaPlugin.getPlugin(Death.class);
    public void run() {

        for(String str : CustomPotions.getItems().getConfigurationSection("potions").getKeys(false)){

            String name = CustomPotions.getItems().getString("potions." + str + ".type");
            int duration = CustomPotions.getItems().getInt("potions." + str + ".duration");
            int niveau = CustomPotions.getItems().getInt("potions." + str + ".niveau");

            PotionEffect effect = new PotionEffect(PotionEffectType.getByName(name), duration * 20, niveau);

            player.addPotionEffect(effect);

        }
        player.setFoodLevel(instance.getConfig().getInt("food"));

        this.cancel();

    }


    public Player getPlayer(){


        return player;
    }


}
