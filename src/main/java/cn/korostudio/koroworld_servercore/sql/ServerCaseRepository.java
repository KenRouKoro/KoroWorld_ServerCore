package cn.korostudio.koroworld_servercore.sql;

import cn.korostudio.koroworld_servercore.data.PlayerItems;
import cn.korostudio.koroworld_servercore.data.ServerCaseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerCaseRepository extends JpaRepository<ServerCaseData, String> {
    ServerCaseData findByUUIDWithID(String UUIDWithID);
}
