package ac.dnd.dodal.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ac.dnd.dodal.IntegrationTest;

public class JasyptConfigTest extends IntegrationTest {

    @Test
    public void testEncrypt() {
        // given
        String sample = "sample";

        // when
        String encryptedSample = stringEncryptor.encrypt(sample);
        String decryptedSample = stringEncryptor.decrypt(encryptedSample);

        // then
        assertEquals(sample, decryptedSample);
    }
}
