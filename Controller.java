package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

public class Controller {
    public TextField txfSource;
    public ImageView imvGarbage;
    public ComboBox cmbAjoutTexte;

    public void txfSourceDragDetected(MouseEvent mouseEvent) {
        if (!txfSource.getText().isEmpty()){
            TextField ref = (TextField) mouseEvent.getSource();
            Dragboard db = ref.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(ref.getText());
            db.setContent(content);
        }
    }

    public void imvTargetDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void imvTargetDragDropped(DragEvent dragEvent) {
        TextField refSource = (TextField) dragEvent.getGestureSource();
        Dragboard db = dragEvent.getDragboard();
        refSource.clear();
    }

    public void cmbTargetDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void cmbTargetDragDropped(DragEvent dragEvent) {
        TextField refSource = (TextField) dragEvent.getGestureSource();
        ComboBox refTarget = (ComboBox) dragEvent.getGestureTarget();
        Dragboard db = dragEvent.getDragboard();
        refTarget.getItems().addAll(refSource.getText());
        refTarget.getSelectionModel().selectLast();
    }

    public void txfTargetDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void txfTargetDragDropped(DragEvent dragEvent) {
        TextField refSource = (TextField) dragEvent.getGestureSource();
        TextField refTarget = (TextField) dragEvent.getGestureTarget();
        Dragboard db = dragEvent.getDragboard();
        if (refSource != refTarget) {
            refTarget.setText(refSource.getText());
            refSource.clear();
        }
    }

}
