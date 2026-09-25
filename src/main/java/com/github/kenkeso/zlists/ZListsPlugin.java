package com.github.kenkeso.zlists;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * ZLists - a tiny Minecraft 1.21.x plugin that shows the player's X coordinate with {@code /z}.
 */
public final class ZListsPlugin extends JavaPlugin implements CommandExecutor, TabCompleter {

    private static final String DEFAULT_PLAYER_ONLY = "&cこのコマンドはプレイヤーのみ実行できます。";
    private static final String DEFAULT_X_MESSAGE = "&aX座標: &e%x%";
    private static final int DEFAULT_DECIMALS = 2;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        PluginCommand command = getCommand("z");
        if (command == null) {
            getLogger().severe("コマンド 'z' を plugin.yml から読み込めませんでした。");
            return;
        }
        command.setExecutor(this);
        command.setTabCompleter(this);

        getLogger().info("ZLists を有効化しました。");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(message("player-only", DEFAULT_PLAYER_ONLY));
            return true;
        }

        double x = player.getLocation().getX();
        String formatted = String.format(Locale.ROOT, "%." + decimals() + "f", x);
        player.sendMessage(message("x", DEFAULT_X_MESSAGE).replace("%x%", formatted));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

    /** Reads a message from config.yml and translates &-color codes. */
    private String message(String key, String fallback) {
        String raw = getConfig().getString("messages." + key, fallback);
        if (raw == null || raw.isEmpty()) {
            raw = fallback;
        }
        return ChatColor.translateAlternateColorCodes('&', raw);
    }

    /** Reads and clamps the configured number of decimal places. */
    private int decimals() {
        int value = getConfig().getInt("decimals", DEFAULT_DECIMALS);
        if (value < 0) {
            return 0;
        }
        return Math.min(value, 10);
    }
}
