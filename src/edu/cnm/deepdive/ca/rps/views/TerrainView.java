package edu.cnm.deepdive.ca.rps.views;


import edu.cnm.deepdive.ca.rps.models.Breed;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * View class for the Rock-Paper-Scissors cellular automaton (CA). This class implements the draw
 * method to render an image of the RPS terrain, on a toroidal space, on which a
 * lattice is overlayed with agents of one of the 3 types at each of the lattice points.
 * <p>
 * As currently implemented, all of the drawing is done on the UI thread; delegating this
 * work to other threads is one of the planned refinements for the future.
 *
 * @author Nicholas Bennett &amp; Deep Dive Coding Java+Android+SalesForce Bootcamp Cohort 2
 * @version 1.0, 2017-10-20
 */
public class TerrainView extends Canvas {


  private Breed[][] source = null;
  private Breed[][] rendered = null;
  private boolean drawing = false;



  public void draw() {
    if (source != null && source != rendered) {
      rendered = source;
      Breed[][] cells = source;
      GraphicsContext context = getGraphicsContext2D();
      double cellSize = Math.min(1.0 * getHeight() / cells.length,
          1.0 * getWidth() / cells[0].length);
      for (int i = 0; i < cells.length; i++) {
        for (int j = 0; j < cells[i].length; j++) {
          switch (cells[i][j]) {
            case ROCK:
              context.setFill(Color.GREENYELLOW);
              break;
            case PAPER:
              context.setFill(Color.NAVY);
              break;
            case SCISSORS:
              context.setFill(Color.GRAY);
              break;
          }
          context.fillRect(
              j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

  /**
   * Specifies a 2-dimensional array of RPS {@link Breed} instances, for use in drawing. This array
   * should be effectively non-volatile &ndash; that is, the contents should not change while
   * drawing is being performed.
   *
   * @param source array of RPS population members
   */
  public synchronized void setSource(Breed[][] source) {
    this.source = source;
  }

}
