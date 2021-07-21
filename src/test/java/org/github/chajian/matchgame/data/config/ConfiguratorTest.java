package org.github.chajian.matchgame.data.config;


import lombok.extern.slf4j.Slf4j;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.github.chajian.matchgame.MatchGame;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
public class ConfiguratorTest {
    /*配置文件*/
    public File configFile;
    /*文件配置*/
    public YamlConfiguration config;

    @Test
    public void read() throws IOException, InvalidConfigurationException {
        log.info(System.getProperty("config.yml"));
        config = new YamlConfiguration();
        File directory = new File("F:\\backed\\Eback\\study\\java\\mc\\MatchGame\\src\\main\\resources\\newconfig.yml");//参数为空
//        configFile = new File("F:\\backed\\Eback\\study\\java\\mc\\MatchGame\\src\\main\\resources\\config.yml");
        config.load(directory);
//        ConfigurationSection configurationSection = config.getConfigurationSection("games");
        List<Object> list = (List<Object>) config.getList("games");
        Map<String,Object> o = (Map<String, Object>) list.get(0);
        log.info("config信息:"+o.get("name"));
    }

}
