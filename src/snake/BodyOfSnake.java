package snake;

public class BodyOfSnake {

    private int velikost;
    private int sirkaRastru;
    private int vyskaRasteru;
    private boolean pohybBarvy;

    public BodyOfSnake(int velikost, int sirkaRastru, int vyskaRasteru, boolean pohybBarvy) {
        this.velikost = velikost;
        this.sirkaRastru = sirkaRastru;
        this.vyskaRasteru = vyskaRasteru;
        this.pohybBarvy = pohybBarvy;
    }

    public BodyOfSnake(int velikost, int sirkaRastru, int vyskaRasteru) {
        this.velikost = velikost;
        this.sirkaRastru = sirkaRastru;
        this.vyskaRasteru = vyskaRasteru;
        pohybBarvy = false;

    }

    public boolean isPohybBarvy() {
        return pohybBarvy;
    }

    public void setPohybBarvy(boolean pohybBarvy) {
        this.pohybBarvy = pohybBarvy;
    }

    public int getVelikost() {
        return velikost;
    }

    public void setVelikost(int velikost) {
        this.velikost = velikost;
    }

    public int getSirkaRastru() {
        return sirkaRastru;
    }

    public void setSirkaRastru(int sirkaRastru) {
        this.sirkaRastru = sirkaRastru;
    }

    public int getVyskaRasteru() {
        return vyskaRasteru;
    }

    public void setVyskaRasteru(int vyskaRasteru) {
        this.vyskaRasteru = vyskaRasteru;
    }
}
