package ac.dnd.dodal.domain.guide.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.guide.enums.GuideType;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "user_guides")
@NoArgsConstructor
public class UserGuide extends BaseEntity {

    @Id
    private Long userId;

    @Enumerated(EnumType.STRING)
    private GuideType type;

    private String content;
    
    public UserGuide(Long userId, GuideType type, String content) {
        if (userId == null || type == null || content == null) {
            throw new IllegalArgumentException(
                "Invalid UserGuide creation: required fields are missing");
        }
        this.userId = userId;
        this.type = type;
        this.content = content;
    }
}
