package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class cardServiceTest {

    @Test
    public void cardRegister_VisaCard() {
        assertTrue(cardService.cardRegister("4111111111111111"));
        assertTrue(cardService.cardRegister("4111 1111 1111 1111"));
        assertFalse(cardService.cardRegister("3111111111111111"));
    }

    @Test
    public void cardRegister_MasterCard() {
        assertTrue(cardService.cardRegister("5111111111111111"));
        assertTrue(cardService.cardRegister("2221000000000000"));
        assertFalse(cardService.cardRegister("6111111111111111"));
    }

    @Test
    public void cardRegister_InvalidCard() {
        assertFalse(cardService.cardRegister("1111111111111111"));
        assertFalse(cardService.cardRegister("0000000000000000"));
    }

    @Test
    public void isBrandMaster_ValidMasterCard() {
        assertTrue(cardService.isBrandMaster("5111111111111111"));
        assertTrue(cardService.isBrandMaster("2221000000000000"));
        assertTrue(cardService.isBrandMaster("2720999999999999"));
    }

    @Test
    public void isBrandMaster_InvalidMasterCard() {
        assertFalse(cardService.isBrandMaster("1111111111111111"));
        assertFalse(cardService.isBrandMaster("4111111111111111"));
        assertFalse(cardService.isBrandMaster("6222000000000000"));
    }

    @Test
    public void isBrandVisa_ValidVisaCard() {
        assertFalse(cardService.isBrandVisa("4111111111111"));
        assertTrue(cardService.isBrandVisa("4111111111111111"));
    }

    @Test
    public void isBrandVisa_InvalidVisaCard() {
        assertFalse(cardService.isBrandVisa("5111111111111111"));
        assertFalse(cardService.isBrandVisa("3111111111111"));
        assertFalse(cardService.isBrandVisa("411111111111"));
    }

    @Test
    public void isValidMasterBin_InRange() {
        assertTrue(cardService.isValidMasterBin("2221001234567890"));
        assertTrue(cardService.isValidMasterBin("2720991234567890"));
    }

    @Test
    public void isValidMasterBin_OutOfRange() {
        assertFalse(cardService.isValidMasterBin("2219991234567890"));
        assertFalse(cardService.isValidMasterBin("2721001234567890"));
    }

    @Test
    public void isValidLuhn_ValidNumber() {
        assertTrue(cardService.isValidLuhn("4111111111111111"));
        assertTrue(cardService.isValidLuhn("79927398713"));
    }

    @Test
    public void isValidLuhn_InvalidNumber() {
        assertFalse(cardService.isValidLuhn("4111111111111112"));
        assertFalse(cardService.isValidLuhn("79927398710"));
    }
}