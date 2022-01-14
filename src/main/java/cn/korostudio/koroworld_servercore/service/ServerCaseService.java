package cn.korostudio.koroworld_servercore.service;

import cn.korostudio.koroworld_servercore.data.PlayerItems;
import cn.korostudio.koroworld_servercore.data.ServerCaseData;
import cn.korostudio.koroworld_servercore.sql.ServerCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping(value = "/servercase")
public class ServerCaseService {
    protected static Logger logger = LoggerFactory.getLogger(ServerCaseService.class);

    @Autowired
    protected ServerCaseRepository serverCaseRepository;

    @PostMapping("/lock")
    public String lock(@RequestParam Map<String, Object> params){
        String key = (String) params.get("key");
        String UUID = (String)params.get("UUID");
        String id = (String) params.get("id");

        ServerCaseData serverCaseData = serverCaseRepository.findByUUIDWithID(UUID+id);

        if(serverCaseData == null){
            return "null";
        }
        if(key.equals("get")){
            return serverCaseData.isLock()?"true":"false";
        }else if(key.equals("set")){
            serverCaseData.setLock(params.get("value").equals("true"));
            serverCaseRepository.save(serverCaseData);
            return "ok";
        }
        return "error";
    }


    @PostMapping("/use")
    public String use(@RequestParam Map<String, Object> params){
        String UUID = (String)params.get("UUID");
        String id = (String) params.get("id");

        ServerCaseData serverCaseData = serverCaseRepository.findByUUIDWithID(UUID+id);

        if(serverCaseData == null){
            return "null";
        }
        return serverCaseData.isUse()?"true":"false";
    }


    @PostMapping("/upload")
    public String upload(@RequestParam Map<String, Object> params) {
        String UUID = (String) params.get("UUID");
        String itemData = (String) params.get("SNBT");
        String id = (String) params.get("id");

        logger.info("ServerCaseData from Player UUID: "+UUID+" id is:"+id+"is get.");

        ServerCaseData serverCaseData =serverCaseRepository.findByUUIDWithID(UUID+id) == null ? new ServerCaseData(): serverCaseRepository.findByUUIDWithID(UUID+id);

        serverCaseData.setUUIDWithID(UUID+id);
        serverCaseData.setSNBT(itemData);
        serverCaseData.setUse(false);
        serverCaseData.setId(id);

        serverCaseRepository.save(serverCaseData);

        return "get";
    }

    @PostMapping("/download")
    public String download(@RequestParam Map<String, Object> params){
        String UUID = (String) params.get("UUID");
        String id = (String) params.get("id");

        ServerCaseData serverCaseData = serverCaseRepository.findByUUIDWithID(UUID+id);
        if(serverCaseData==null) {
            return "non";
        }
        else{
            serverCaseData.setUse(true);
            serverCaseRepository.save(serverCaseData);
            return serverCaseData.getSNBT();
        }
    }



}
