/**
 * @Author: Redouan Bouziza IS205
 * Parent View Class
 */

package practicumopdracht.Views;

import javafx.scene.Parent;

public abstract class View {

    private Parent root;

    public View() {
        this.root = initializeView();
    }

    protected abstract Parent initializeView();

    public Parent getRoot(){
        return root;
    }


}
