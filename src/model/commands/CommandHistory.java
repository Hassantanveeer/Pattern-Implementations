package model.commands;
import model.persistence.ICommand;
import java.util.Stack;

public class CommandHistory {
    private static final Stack<ICommand> redoStack= new Stack<>();
    private static final Stack<ICommand> undoStack= new Stack<>();

    public static void add(ICommand comm){
        redoStack.clear();
        undoStack.push(comm);
    }

    public static boolean undo(){
        boolean solution = !undoStack.empty();
        if (solution) {
            ICommand comm = undoStack.pop();
            redoStack.push(comm);
            comm.undo();
        }
        return solution;
    }

    public static boolean redo(){
        boolean solution = !redoStack.empty();
        if (solution) {
            ICommand comm = redoStack.pop();
            redoStack.push(comm);
            comm.redo();
        }
        return solution;   
}
}
