package cn.korostudio.koroworld_servercore.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PlayerData {
    @Id
    protected String UUID;
    protected String nickName;
}
