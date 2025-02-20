package ac.dnd.dodal.domain.guide.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import ac.dnd.dodal.domain.guide.enums.GuideType;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserGuideId implements Serializable {

    private Long userId;
    private GuideType type;
}
