package chapter07.extend_;

public class Practice03Test {
    public static void main(String[] args) {
        Practice03PC pc = new Practice03PC("AMD5900x", 32, 2048, "惠普");
        Practice03NotePda notePade = new Practice03NotePda("AMD5950X", 64, 303072, "黑色");

        System.out.println(pc.getDetails());
        System.out.println(notePade.getDetails());
    }
}
