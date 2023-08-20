package dk.yalibs.yaundo;

/**
 * Represents a unit of work that can be undone and redone by an {@link IUndoSystem}.
 */
public class Undoable {
    private final Runnable undoAction;
    private final Runnable redoAction;
    private final String description;

    /**
     * Constructs a new unit of undoable work
     * @param undoAction
     * @param redoAction
     */
    public Undoable(Runnable undoAction, Runnable redoAction) {
        this("<n/a>", undoAction, redoAction);
    }

    public Undoable(String description, Runnable undoAction, Runnable redoAction) {
        this.redoAction = redoAction;
        this.undoAction = undoAction;
        this.description = description;
    }

    public void redo() {
        redoAction.run();
    }

    public void undo() {
        undoAction.run();
    }

    public String getDescription() {
        return description;
    }
}

