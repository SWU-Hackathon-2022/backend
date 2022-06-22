package MOAI.moai.music;

import MOAI.moai.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MusicRequest extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "req_id")
    private Long id;

}
