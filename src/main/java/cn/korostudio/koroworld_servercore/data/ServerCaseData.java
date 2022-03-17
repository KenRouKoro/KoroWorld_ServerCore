package cn.korostudio.koroworld_servercore.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class ServerCaseData {
    @Id
    private String UUIDWithID ;
    @Lob
    private String SNBT ;
    private String id;
    private String itemGroup;
    private boolean lock = false;
    private boolean use = false;
}
