/*
 * These actions need to be completed in CommandHistory.
 */
package model.commands;

public class UndoCommand {
  //  @Override
    public void undo(){
        CommandHistory.undo();
    }
}
