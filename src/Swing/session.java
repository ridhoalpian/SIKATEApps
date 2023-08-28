package Swing;
public class session {
    private static String id_prod;
    private static String id_akun;
    
    public static String get_id(){
        return id_prod;
    }
    public static void set_id(String id_prod){
        session.id_prod = id_prod;
    }
}
