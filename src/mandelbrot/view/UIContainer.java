package mandelbrot.view;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UIContainer {
    private static final String[] RULES = {"Mandelbrot", "Julia"};
    private static final String INSTRUCTIONS = "Use WASD to pan.\nUp arrow zooms in.\nDown arrow zooms out.";

    private Pane pane;
    private CanvasView canvas;
    private ComboBox<String> dropDown;
    private Text text;

    public UIContainer(int maxIter) {
        pane = new Pane();
        canvas = new CanvasView(maxIter);
        dropDown = makeDropDown();
        text = setupText();
        pane.getChildren().addAll(canvas, dropDown, text);
    }

    public Pane getPane(){
        return pane;
    }

    public ComboBox<String> getDropDown(){
        return dropDown;
    }

    public void setPixel(int xIndex, int yIndex, int numIter){
        canvas.updatePixel(xIndex, yIndex, numIter);
    }

    private ComboBox<String> makeDropDown(){
        ComboBox<String> ret = new ComboBox<>(FXCollections.observableArrayList(RULES));
        ret.setLayoutX(CanvasView.WIDTH + 30);
        ret.setLayoutY(60);
        return ret;
    }

    private Text setupText() {
        Text txt = new Text(INSTRUCTIONS);
        txt.setX(CanvasView.WIDTH + 30);
        txt.setY(100);
        return txt;
    }
}
