package ac.dnd.dodal.common.util;

import ac.dnd.dodal.domain.onboarding.enums.QuestionContent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class QuestionContentConverter implements AttributeConverter<QuestionContent, String> {

    @Override
    public String convertToDatabaseColumn(QuestionContent attribute) {
        return attribute != null ? attribute.getMainContent() : null;
    }

    @Override
    public QuestionContent convertToEntityAttribute(String dbData) {
        return dbData != null ? QuestionContent.valueOf(dbData) : null;
    }
}