package illustrator

import illustrator.structures.Point
import illustrator.structures.Segment
import illustrator.structures.Shape
import scala.math

object Renderer {

  def render(width: Int, height: Int, char: Char): (Shape) => Unit = {
    (shape: Shape) => {
      val points = getPoints(shape)

      for (y <- 0 to height; x <- 0 to width) {
        if (points.contains(Point(x, y))) {
          print(char)
        }
        else {
          print(' ')
        }

        if (x == width) {
          print('\n')
        }
      }
    }
  }

  private def getPoints(shape: Shape): Set[Point] = {
    val horizontalPoints = shape.segments.flatMap(
      (segment: Segment) => {
        val line = (x: Int) => {
          Math.round(
            (getSlope(segment) * (x - segment.start.x)) + segment.start.y
          )
        }

        getValidRange(segment.start.x, segment.end.x).map(
          (x: Int) => Point(x, line(x))
        )
      }
    )
    
    val verticalPoints = shape.segments.flatMap(
      (segment: Segment) => {
        val line = (y: Int) => {
          Math.round(
            ((y - segment.start.y) / getSlope(segment)) + segment.start.x
          )
        }

        getValidRange(segment.start.y, segment.end.y).map(
          (y: Int) => Point(line(y), y)
        )
      }
    )

    horizontalPoints.toSet.union(verticalPoints.toSet)
  }

  private def getSlope(segment: Segment): Float = {
    val deltaY = (segment.start.y - segment.end.y).toFloat
    val deltaX = segment.start.x - segment.end.x

    deltaY / deltaX
  }

  private def getValidRange(start: Int, end: Int): Range = {
    if (start < end) {
      start to end
    }
    else if (start == end) {
      start until end
    }
    else {
      start to end by -1
    }
  }
}
