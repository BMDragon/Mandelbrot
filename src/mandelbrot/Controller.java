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
    private static final int BASE_ITERATIONS = 300;
    private static final int PAN_AMOUNT = 20;

    private Scene scene;
    private UIContainer uiContainer;
    private Grid rulebook;
    private boolean loaded;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loaded = false;
        uiContainer = new UIContainer(BASE_ITERATIONS);
        uiContainer.getDropDown().setOnAction(handleDropDown());
        scene = new Scene(uiContainer.getPane(), CanvasView.WIDTH + 200, CanvasView.WIDTH);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private EventHandler<ActionEvent> handleDropDown() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    rulebook = (Grid) Class.forName("mandelbrot.model.rules." + uiContainer.getDropDown().getValue())
                            .getDeclaredConstructor(Integer.TYPE, Integer.TYPE)
                            .newInstance(CanvasView.WIDTH, BASE_ITERATIONS);
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
                    rulebook.panUpUpdate(BASE_ITERATIONS, PAN_AMOUNT);
                    backToFront();
                }
            }
            case S -> {
                if (loaded) {
                    rulebook.panDownUpdate(BASE_ITERATIONS, PAN_AMOUNT);
                    backToFront();
                }
            }
            case A -> {
                if (loaded) {
                    rulebook.panLeftUpdate(BASE_ITERATIONS, PAN_AMOUNT);
                    backToFront();
                }
            }
            case D -> {
                if (loaded) {
                    rulebook.panRightUpdate(BASE_ITERATIONS, PAN_AMOUNT);
                    backToFront();
                }
            }
            case E -> {
                if (loaded) {
                    rulebook.setWidth(rulebook.getWidth() * 0.9);
                    rulebook.initGrid(BASE_ITERATIONS);
                    backToFront();
                }
            }
            case Q -> {
                if (loaded) {
                    rulebook.setWidth(rulebook.getWidth() / 0.9);
                    rulebook.initGrid(BASE_ITERATIONS);
                    backToFront();
                }
            }
            default -> {
            }
        }
    }

    private void backToFront() {
        for (int i = 0; i < CanvasView.WIDTH; i++) {
            for (int j = 0; j < CanvasView.WIDTH; j++) {
                uiContainer.setPixel(i, j, rulebook.getPixelValue(i, j));
            }
        }
    }
}
