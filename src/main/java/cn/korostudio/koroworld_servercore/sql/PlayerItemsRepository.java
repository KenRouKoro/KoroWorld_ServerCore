package cn.korostudio.koroworld_servercore.sql;

import cn.korostudio.koroworld_servercore.data.PlayerItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  PlayerItemsRepository extends JpaRepository<PlayerItems, String> {
    PlayerItems findByUUID(String UUID);
}
