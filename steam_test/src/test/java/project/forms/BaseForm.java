package project.forms;

import framework.pageElements.BaseElement;

public abstract class BaseForm {
    protected BaseElement uniqueElement;

    public boolean isOnThePage() {
        return this.uniqueElement.isDisplayed();
    }
}
