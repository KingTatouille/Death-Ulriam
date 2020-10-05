package fr.hillwalk.death.runnable;

import fr.hillwalk.death.Death;
import fr.hillwalk.death.config.CustomPotions;
import fr.hillwalk.death.listener.ConsumeEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PotionsEffectsAfterMilk extends BukkitRunnable {


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

    public PotionsEffectsAfterMilk(Player player){

    this.player = player;

    }

    ConsumeEvent util = new ConsumeEvent();

    Death instance = JavaPlugin.getPlugin(Death.class);

    int miracle = 0;
    public void run() {


        for(String str : CustomPotions.getItems().getConfigurationSection("potions").getKeys(false)){

            String name = CustomPotions.getItems().getString("potions." + str + ".type");


            if(!player.getActivePotionEffects().contains(PotionEffectType.getByName(name))){

                player.addPotionEffect(util.listEffects.get(miracle));

            }

        miracle++;
        }
        util.listEffects.clear();
        this.cancel();

    }


    public Player getPlayer(){


        return player;
    }


}
