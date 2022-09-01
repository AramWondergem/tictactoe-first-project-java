package novi.basics;

public class Field {

    private String token;
    private boolean fieldOccupied;

    public Field(String token) {
        this.token = token;
        this.fieldOccupied = false;
    }

    public String getToken(){
        return token;
    }

    public boolean setToken(String currentPlayerToken) {

        if (!fieldOccupied){
            this.token = currentPlayerToken;
        }
        else {
            return false;
        }

        fieldOccupied = true;
        return true;
    }
}
