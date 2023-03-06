package mandelbrot;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import mandelbrot.model.Grid;
import mandelbrot.view.CanvasView;
import mandelbrot.view.UIContainer;

public class Controller extends Application {
    private static final int MAX_ITERATIONS = 1000;

    private Scene stage;
    private UIContainer scene;
    private Grid rulebook;
    private boolean loaded;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loaded = false;
        scene = new UIContainer(MAX_ITERATIONS);
        scene.getDropDown().setOnAction(handleDropDown());
        stage = new Scene(scene.getPane(), CanvasView.WIDTH + 200, CanvasView.WIDTH);
        stage.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        stage.setOnScroll(e -> handleScroll());
        primaryStage.setScene(stage);
        primaryStage.show();
    }

    private EventHandler<ActionEvent> handleDropDown() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    rulebook = (Grid) Class.forName("mandelbrot.model.rules." + scene.getDropDown().getValue())
                            .getDeclaredConstructor(Integer.TYPE, Integer.TYPE)
                            .newInstance(CanvasView.WIDTH, MAX_ITERATIONS);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException
                        | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                loaded = true;
                backToFront();
            }
        };
        return event;
    }

    private void handleKeyInput(KeyCode code) {
        switch (code) {
            case W -> {
                if (loaded) {

                }
            }
            default -> {
            }
        }
    }

    private void handleScroll() {

    }

    private void backToFront() {
        rulebook.updateGrid(MAX_ITERATIONS);
        for (int i = 0; i < CanvasView.WIDTH; i++) {
            for (int j = 0; j < CanvasView.WIDTH; j++) {
                scene.setPixel(i, j, rulebook.getPixelValue(i, j));
            }
        }
    }
}
