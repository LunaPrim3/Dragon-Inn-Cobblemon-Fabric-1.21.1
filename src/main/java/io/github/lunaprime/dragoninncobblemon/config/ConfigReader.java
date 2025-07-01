package io.github.lunaprime.dragoninncobblemon.config;
import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import io.github.lunaprime.dragoninncobblemon.entity.custom.WormholeStatic;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;


public class ConfigReader {

    private static FabricLoader fabricLoader = FabricLoader.getInstance();
    private static final Path configurationDirectory = fabricLoader.getConfigDir();
    private static String configFilePath;
    public static HashMap<String, Double> configHashMap; //Initializes a public hashmap containing the config settings


    public static void registerConfigReader(){
        DragonInnCobblemonMod.LOGGER.info("Preparing mod config for " + DragonInnCobblemonMod.MOD_ID);

        //Some servers use / instead of \ in directory paths, this is setup so it should always go into the config folder.
        //DragonInnCobblemonMod.LOGGER.info("Enviromnent: {}", fabricLoader.getEnvironmentType());
        if (fabricLoader.getEnvironmentType().equals(EnvType.SERVER)){
            configFilePath = configurationDirectory + "/dragoninncobblemon.json";
        }
        else {
            configFilePath = configurationDirectory + "\\dragoninncobblemon.json";
        }

        DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: Config File Path: {}", configFilePath);

        //This handles the creation of the configuration file if it's missing, and catches any errors it might run into.
        try{
            File config = new File(configFilePath);
            if (config.createNewFile()){
                DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: Creating new configuration file for " + DragonInnCobblemonMod.MOD_ID);
            } else{
                DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: Dragon Inn Cobblemon configuration file already exists, skipping step");
            }
        }
        catch (IOException e){
            DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: An error occurred when creating the Dragon Inn Cobblemon configuration file.");
        }

        configHashMap = getConfigHashMap();
        DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: Static Wormhole X: {}", ConfigReader.configHashMap.get("WormholeStaticPositionX"));
        DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: Static Wormhole Y: {}", ConfigReader.configHashMap.get("WormholeStaticPositionY"));
        DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: Static Wormhole X: {}", ConfigReader.configHashMap.get("WormholeStaticPositionZ"));
    }




    public static HashMap<String, Double> getConfigHashMap(){ //Reads the configuration file and returns the integer value set
        HashMap<String, Double> configHashMap = new HashMap<>();
        File config = new File(configFilePath);
        try(Scanner readConfig = new Scanner(config)){
            while ((readConfig.hasNextLine())){
                String[] keyValuePair = readConfig.nextLine().split(":",2);
                configHashMap.put(keyValuePair[0], Double.parseDouble(keyValuePair[1]));
                DragonInnCobblemonMod.LOGGER.info("ConfigReader: {}: {}", keyValuePair[0], keyValuePair[1]);
            }
        }
        catch (Exception e ){
            DragonInnCobblemonMod.LOGGER.info("DragonInnCobblemon: An error occurred while attempting to read the Dragon Inn Cobblemon configuration file: "+ e);
        }

        return configHashMap;
    }





}
