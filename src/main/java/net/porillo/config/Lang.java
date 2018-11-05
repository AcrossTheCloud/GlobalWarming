package net.porillo.config;

import lombok.Getter;
import net.porillo.GlobalWarming;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.text.MessageFormat;

public enum Lang {
    /**
     * See lang.yml for translations
     */
    ENGINE_DISABLED(""),
    GENERIC_PERMISSION(""),
    NOTIFICATION_DEFAULT_LOW(""),
    NOTIFICATION_DEFAULT_OK(""),
    NOTIFICATION_DEFAULT_HIGH(""),
    NOTIFICATION_FARM_LOW(""),
    NOTIFICATION_FARM_OK(""),
    NOTIFICATION_FARM_HIGH(""),
    NOTIFICATION_ICE_LOW(""),
    NOTIFICATION_ICE_OK(""),
    NOTIFICATION_ICE_HIGH(""),
    NOTIFICATION_MOB_LOW(""),
    NOTIFICATION_MOB_OK(""),
    NOTIFICATION_MOB_HIGH(""),
    NOTIFICATION_SEALEVEL_LOW(""),
    NOTIFICATION_SEALEVEL_OK(""),
    NOTIFICATION_SEALEVEL_HIGH(""),
    NOTIFICATION_SNOW_LOW(""),
    NOTIFICATION_SNOW_OK(""),
    NOTIFICATION_SNOW_HIGH(""),
    SCORE_CHAT(""),
    SCORE_TEMPERATURE(""),
    TEMPERATURE_BALANCED(""),
    TEMPERATURE_HIGH(""),
    TEMPERATURE_LOW(""),
    WIKI_ADDED(""),
    WIKI_NAME(""),
    WIKI_AUTHOR(""),
    WIKI_LORE(""),
    WIKI_INTRODUCTION(""),
    WIKI_SCORES(""),
    WIKI_EFFECTS(""),
    WIKI_BOUNTY(""),
    WIKI_OTHER("");

    private String def;
    private static LangConfig langConfig;

    Lang(String def) {
        this.def = def;
    }

    public String get() {
        return color(getRaw());
    }

    public String get(Object... args) {
        return color(MessageFormat.format(getRaw(), args));
    }

    public String getRaw() {
        return langConfig.getLangConf().getString(getPath(), getDef());
    }

    public String getPath() {
        return name().replace('_', '.');
    }

    public String getDef() {
        return def;
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }


    private static class LangConfig extends ConfigLoader {

        @Getter private String locale;

        private LangConfig() {
            super("lang.yml");
            super.saveIfNotExist();
            super.load();

            GlobalWarming.getInstance().getLogger().info("Lang Loaded: " + locale);
        }

        private ConfigurationSection getLangConf() {
            return conf.getConfigurationSection("lang");
        }

        @Override
        protected void loadKeys() {
            locale = conf.getString("locale", "en-US");
        }

    }

    public static void init() {
        langConfig = new LangConfig();
    }

}
