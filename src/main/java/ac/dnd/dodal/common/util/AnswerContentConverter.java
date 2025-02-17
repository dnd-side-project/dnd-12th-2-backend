package ac.dnd.dodal.common.util;

import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AnswerContentConverter implements AttributeConverter<AnswerContent, String> {
    
    @Override
    public String convertToDatabaseColumn(AnswerContent attribute) {
        return attribute != null ? attribute.getContent() : null;
    }

    @Override
    public AnswerContent convertToEntityAttribute(String dbData) {
        return dbData != null ? AnswerContent.valueOf(dbData) : null;
    }
}
