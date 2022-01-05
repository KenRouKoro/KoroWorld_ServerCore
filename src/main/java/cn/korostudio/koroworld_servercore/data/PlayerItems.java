package cn.korostudio.koroworld_servercore.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Data
public class PlayerItems {
    @Id
    private String UUID;
    @Lob
    private String playerItemData;
    private boolean lock=true;
}
