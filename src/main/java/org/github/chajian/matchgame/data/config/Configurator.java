package org.github.chajian.matchgame.data.config;

import lombok.Data;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.github.chajian.matchgame.MatchGame;

import java.io.File;
import java.io.IOException;

/**
 * 配置器
 * - 单例模式
 * - 封装读取接口从而.yml自定义变量读取数据库数据
 * @author Chajian
 */
@Data
public class Configurator {

    private static Configurator configurator = null;
    /*配置文件*/
    public File configFile;
    /*文件配置*/
    public YamlConfiguration config;
    /*插件本体*/
    public final MatchGame matchGame;
    public File dataFolder;

    private Configurator() {
        this.matchGame = MatchGame.getMatchGame();
        dataFolder = matchGame.getDataFolder();
        dataFolder.mkdir();
        try {
            initConfig();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化默认配置
     */
    public void initConfig() throws IOException, InvalidConfigurationException {
        File dataFolder = matchGame.getDataFolder();
        configFile = new File(dataFolder,"config.yml");
        config = new YamlConfiguration();
        if(!configFile.exists())
            getMatchGame().saveResource("config.yml",false);
        config.load(configFile);
    }

    public static Configurator getConfigurator() {
        if (configurator == null)
            configurator = new Configurator();
        return configurator;
    }


}
