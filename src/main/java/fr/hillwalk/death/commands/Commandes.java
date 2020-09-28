package fr.hillwalk.death.commands;

import fr.hillwalk.death.Death;
import fr.hillwalk.death.config.CustomPotions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Commandes implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Death instance = JavaPlugin.getPlugin(Death.class);


        if(!(sender.isOp()) || !(sender.hasPermission("death.reload"))){

            sender.sendMessage(instance.prefix + ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("messages.player.permission")));
            return true;

        }

        if(args.length == 0){


            sender.sendMessage("Syntax : /ulriam reload");
            return true;

        }

        if(args[0].equalsIgnoreCase("reload")){


            //On reload la config de base
            instance.reloadConfig();
            instance.saveDefaultConfig();

            //On reload les potions
            CustomPotions.reload();
            CustomPotions.save();

            instance.prefix = ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("prefix") + " ");

            sender.sendMessage(Death.prefix + "La config est maintenant chargée !");



            return true;
        }




        return false;
    }
}
