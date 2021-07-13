package org.github.chajian.matchgame.data.config;

import lombok.Data;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.github.chajian.matchgame.MatchGame;

import java.io.File;
import java.io.IOException;

/**
 * 配置器
 * @author Chajian
 */
@Data
public class Configurator {
    /*配置文件*/
    public File configFile;
    /*文件配置*/
    public FileConfiguration config;
    /*插件本体*/
    public final MatchGame matchGame;


    public Configurator(MatchGame matchGame) {
        this.matchGame = matchGame;
    }

    /**
     * 初始化默认配置
     */
    public void initConfig() throws IOException, InvalidConfigurationException {
        File dataFolder = matchGame.getDataFolder();
        configFile = new File(dataFolder,"config.yml");
        config = new YamlConfiguration();
        if(!configFile.exists())
            configFile.createNewFile();
        config.load(configFile);
    }
}
