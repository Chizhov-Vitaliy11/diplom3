import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest extends BaseTest {


    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testBunsSection() {
        mainPage.clickSaucesSection();

        mainPage.clickBunsSection();
        mainPage.isBunsSectionActive();

        System.out.println(mainPage.getActiveSectionText());
        assertEquals("Булки", mainPage.getActiveSectionText());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testSaucesSection() {
        mainPage.clickSaucesSection();
        assertEquals("Соусы", mainPage.getActiveSectionText());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testFillingsSection() {
        mainPage.clickFillingsSection();
        assertEquals("Начинки", mainPage.getActiveSectionText());
    }
}