package dk.yalibs.yaundo;

/**
 * Interface specification for an undo system.
 *
 * Concrete implementations: {@link StackUndoSystem}
 */
public interface IUndoSystem {
    /**
     * Get the whole history currently stored in the undo system.
     * @return a collection with all the {@code Undoable} instances currently known in the undo system
     */
    Iterable<Undoable> getHistory();

    /**
     * Add a new entry into the undo system.
     * @param action an undoable unit of work
     */
    void push(Undoable action);

    /**
     * Undo an action. Which action is decided by the concrete interface implementation.
     */
    void undo();

    /**
     * Redo an action. Which action is decided by the concrete interface implementation.
     */
    void redo();
}

