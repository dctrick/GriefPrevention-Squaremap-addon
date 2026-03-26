package dev.trick;

import org.bukkit.plugin.java.JavaPlugin;
import dev.trick.config.GPConfig;
import dev.trick.hook.SquaremapHook;

public final class SquaremapGriefPrevention extends JavaPlugin {
    private SquaremapHook squaremapHook;
    private GPConfig config;

    public GPConfig config() {
        return this.config;
    }

    @Override
    public void onEnable() {
        this.config = new GPConfig(this);
        this.config.reload();

        if (this.getServer().getPluginManager().getPlugin("squaremap") == null) {
            this.getLogger().warning("squaremap was not found, disabling.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.squaremapHook = new SquaremapHook(this);
    }

    @Override
    public void onDisable() {
        if (this.squaremapHook != null) {
            this.squaremapHook.disable();
        }
    }
}
