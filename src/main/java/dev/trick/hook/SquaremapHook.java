package dev.trick.hook;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.World;
import dev.trick.SquaremapGriefPrevention;
import dev.trick.task.SquaremapTask;
import xyz.jpenilla.squaremap.api.BukkitAdapter;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.MapWorld;
import xyz.jpenilla.squaremap.api.Squaremap;
import xyz.jpenilla.squaremap.api.SimpleLayerProvider;
import xyz.jpenilla.squaremap.api.SquaremapProvider;
import xyz.jpenilla.squaremap.api.WorldIdentifier;

public final class SquaremapHook {
    private static final Key GP_LAYER_KEY = Key.of("griefprevention");

    private final Map<WorldIdentifier, SquaremapTask> tasks = new HashMap<>();

    public SquaremapHook(SquaremapGriefPrevention plugin) {
        final Squaremap providerApi = SquaremapProvider.get();
        if (providerApi == null) {
            throw new IllegalStateException("squaremap API provider is not available");
        }

        for (final MapWorld world : providerApi.mapWorlds()) {
            final World bukkitWorld = BukkitAdapter.bukkitWorld(world);
            if (bukkitWorld == null || !GPHook.isWorldEnabled(bukkitWorld.getUID())) {
                continue;
            }

            SimpleLayerProvider provider = SimpleLayerProvider
                .builder(plugin.config().controlLabel())
                .showControls(plugin.config().controlShow())
                .defaultHidden(plugin.config().controlHide())
                .zIndex(plugin.config().zIndex())
                .layerPriority(plugin.config().layerPriority())
                .build();
            world.layerRegistry().register(GP_LAYER_KEY, provider);
            SquaremapTask task = new SquaremapTask(plugin, world, provider);
            task.runTaskTimerAsynchronously(plugin, 0L, 20L * plugin.config().updateInterval());
            this.tasks.put(world.identifier(), task);
        }
    }

    public void disable() {
        this.tasks.values().forEach(SquaremapTask::disable);
        this.tasks.clear();
    }
}
