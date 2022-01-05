package cn.korostudio.koroworld_servercore.service;

import cn.korostudio.koroworld_servercore.data.PlayerItems;
import cn.korostudio.koroworld_servercore.sql.PlayerItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping(value = "/item")
public class ItemAPIService {
    protected static Logger logger = LoggerFactory.getLogger(ItemAPIService.class);
    @Autowired
    private PlayerItemsRepository playerItemsRepository;

    @PostMapping("/upload")
    public String upload(@RequestParam Map<String, Object> params) {
        String UUID = (String) params.get("UUID");
        String playerItemData = (String) params.get("SNBT");

        logger.info("Data from Player UUID: "+UUID+" is get.");

        PlayerItems playerItems =playerItemsRepository.findByUUID(UUID) == null ? new PlayerItems(): playerItemsRepository.findByUUID(UUID);

        playerItems.setUUID(UUID);
        playerItems.setPlayerItemData(playerItemData);
        playerItemsRepository.save(playerItems);

        return "get";
    }

    @PostMapping("/download")
    public String download(@RequestParam Map<String, Object> params){
        String UUID = (String) params.get("UUID");

        PlayerItems playerItems = playerItemsRepository.findByUUID(UUID);
        if(playerItems==null) {
            return "non";
        }
        else{
            return playerItems.getPlayerItemData();
        }
    }
    @PostMapping("/lock")
    public String lock(@RequestParam Map<String, Object> params){
        String key = (String) params.get("key");
        String UUID = (String)params.get("UUID");

        PlayerItems playerItems = playerItemsRepository.findByUUID(UUID);

        if(playerItems == null){
            return "null";
        }
        if(key.equals("get")){
            return playerItems.isLock()?"true":"false";
        }else if(key.equals("set")){
            playerItems.setLock(params.get("value").equals("true"));
            playerItemsRepository.save(playerItems);
            return "ok";
        }
        return "error";
    }
}
