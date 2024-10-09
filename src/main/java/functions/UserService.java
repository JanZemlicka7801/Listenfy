package functions;

public class UserService {

    public boolean register(String username, String password, String creditCard, String email) {
        creditCard = creditCard.trim();
        return cardService.cardRegister(creditCard);
    }

}
