package dk.yalibs.yaundo;

import java.util.ArrayList;
import java.util.List;

/**
 * The default implementation of {@code IUndoSystem}.
 * Maintains a stack of actions and will always undo / redo the top of the stack.
 * If an new action is inserted whilst the stack pointer is not at the top, all actions ahead of the pointer will be dropped.
 *
 * Example:
 * <pre>
 * {@code
 * var element = new ArrayList<Integer>();
 * var undosystem = new StackUndoSystem();
 * // Do the action
 * element.add(1);
 * // Add the undoable action
 * undosystem.push(new Undoable("add 1", () -> element.remove(0), () -> element.add(1)));
 * // Undo the action
 * undosystem.undo();
 * // Redo the action
 * undosystem.redo();
 * }
 * </pre>
 *
 * Note that any actions pushed are assumed to have already been executed.
 */
public class StackUndoSystem implements IUndoSystem {
    private int cursorIndex;
    private final ArrayList<Undoable> actions;

    /**
     * Constructs an instance of a stack based undo system.
     */
    public StackUndoSystem() {
        this.actions = new ArrayList<>();
        this.cursorIndex = -1;
    }

    @Override
    public Iterable<Undoable> getHistory() {
        return actions;
    }

    @Override
    public void push(Undoable action) {
        if(actions.size()-1 > cursorIndex)
            removeInRange(actions, cursorIndex+1, actions.size()-1);
        actions.add(++cursorIndex, action);
    }

    @Override
    public void undo() {
        if(cursorIndex-1 < -1)
            return;
        actions.get(cursorIndex--).undo();
    }

    @Override
    public void redo() {
        if(cursorIndex+1 > actions.size()-1)
            return;
        actions.get(++cursorIndex).redo();
    }

    private <T> void removeInRange(List<T> list, int startInd, int endInd) {
        for(var i = endInd; i >= startInd; i--)
            list.remove(i);
    }
}

