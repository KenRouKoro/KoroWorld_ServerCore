package cn.korostudio.koroworld_servercore.sql;

import cn.korostudio.koroworld_servercore.data.PlayerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PlayerDataRepository extends JpaRepository<PlayerData, String> {
    PlayerData findByUUID(String UUID);
}
