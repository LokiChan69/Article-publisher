public class Main {
    public static void main(String[] args) {
        ManagerLogic logic = new ManagerLogic();
        DatabaseManager data = new DatabaseManager();
        ManagerGUI gui = new ManagerGUI(logic,data);
        
        data.saveProduct("Test Product", 10, 20000, "Seoul factory", "Category A");
        gui.show(); 
}
}