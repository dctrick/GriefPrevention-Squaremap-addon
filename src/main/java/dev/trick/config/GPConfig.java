package dev.trick.config;

import java.awt.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class GPConfig {
    private final JavaPlugin plugin;

    private String controlLabel = "GriefPrevention";
    private boolean controlShow = true;
    private boolean controlHide = false;
    private int updateInterval = 300;
    private Color strokeColor = Color.GREEN;
    private int strokeWeight = 1;
    private double strokeOpacity = 1.0D;
    private Color fillColor = Color.GREEN;
    private double fillOpacity = 0.2D;
    private String stringsPublic = "Public";
    private int zIndex = 99;
    private int layerPriority = 99;
    private String claimTooltip = "Claim Owner: <span style=\"font-weight:bold;\">{owner}</span><br/>"
        + "Permission Trust: <span style=\"font-weight:bold;\">{managers}</span><br/>"
        + "Trust: <span style=\"font-weight:bold;\">{builders}</span><br/>"
        + "Container Trust: <span style=\"font-weight:bold;\">{containers}</span><br/>"
        + "Access Trust: <span style=\"font-weight:bold;\">{accessors}</span>";
    private String adminClaimTooltip = "<span style=\"font-weight:bold;\">Administrator Claim</span><br/>"
        + "Permission Trust: <span style=\"font-weight:bold;\">{managers}</span><br/>"
        + "Trust: <span style=\"font-weight:bold;\">{builders}</span><br/>"
        + "Container Trust: <span style=\"font-weight:bold;\">{containers}</span><br/>"
        + "Access Trust: <span style=\"font-weight:bold;\">{accessors}</span>";

    public GPConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        this.plugin.saveDefaultConfig();
        this.plugin.reloadConfig();

        final FileConfiguration config = this.plugin.getConfig();
        this.controlLabel = config.getString("settings.control.label", this.controlLabel);
        this.controlShow = config.getBoolean("settings.control.show", this.controlShow);
        this.controlHide = config.getBoolean("settings.control.hide-by-default", this.controlHide);
        this.updateInterval = Math.max(20, config.getInt("settings.update-interval", this.updateInterval));
        this.strokeColor = parseColor(config.getString("settings.style.stroke.color"), this.strokeColor);
        this.strokeWeight = Math.max(1, config.getInt("settings.style.stroke.weight", this.strokeWeight));
        this.strokeOpacity = clamp(config.getDouble("settings.style.stroke.opacity", this.strokeOpacity), 0.0D, 1.0D);
        this.fillColor = parseColor(config.getString("settings.style.fill.color"), this.fillColor);
        this.fillOpacity = clamp(config.getDouble("settings.style.fill.opacity", this.fillOpacity), 0.0D, 1.0D);
        this.stringsPublic = config.getString("settings.strings.public", this.stringsPublic);
        this.claimTooltip = config.getString("settings.region.tooltip.regular-claim", this.claimTooltip);
        this.adminClaimTooltip = config.getString("settings.region.tooltip.admin-claim", this.adminClaimTooltip);
        this.zIndex = config.getInt("settings.control.z-index", this.zIndex);
        this.layerPriority = config.getInt("settings.control.layer-priority", this.layerPriority);
    }

    public String controlLabel() {
        return this.controlLabel;
    }

    public boolean controlShow() {
        return this.controlShow;
    }

    public boolean controlHide() {
        return this.controlHide;
    }

    public int updateInterval() {
        return this.updateInterval;
    }

    public Color strokeColor() {
        return this.strokeColor;
    }

    public int strokeWeight() {
        return this.strokeWeight;
    }

    public double strokeOpacity() {
        return this.strokeOpacity;
    }

    public Color fillColor() {
        return this.fillColor;
    }

    public double fillOpacity() {
        return this.fillOpacity;
    }

    public String stringsPublic() {
        return this.stringsPublic;
    }

    public int zIndex() {
        return this.zIndex;
    }

    public int layerPriority() {
        return this.layerPriority;
    }

    public String claimTooltip() {
        return this.claimTooltip;
    }

    public String adminClaimTooltip() {
        return this.adminClaimTooltip;
    }

    private static Color parseColor(String raw, Color fallback) {
        if (raw == null || raw.isBlank()) {
            return fallback;
        }

        try {
            return Color.decode(raw.startsWith("#") ? raw : "#" + raw);
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
