package illustrator

import illustrator.structures.Point
import illustrator.structures.Segment
import illustrator.structures.Shape
import scala.math

object Renderer {

  // Render a shape in the terminal. This function must be curried (passed
  // initial configuration parameters and then saved as a new function).
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

  // Get all of the points on a grid that are required to represent a shape.
  private def getPoints(shape: Shape): Set[Point] = {

    // For each segment in the shape, calculate the line equation and use this
    // equation to get y values for each discrete x value along the line.
    val horizontalPoints = shape.segments.flatMap(
      (segment: Segment) => {
        val line = (x: Int) => {
          Math.round(
            (getSlope(segment) * (x - segment.start.x)) + segment.start.y
          )
        }

        val range = getRange(segment.start.x, segment.end.x)
        range.map(
          (x: Int) => Point(x, line(x))
        )
      }
    )
    
    // Get x values for each discrete y value along a line. This calculation
    // might seem duplicated, but it ensures that very steep lines are properly
    // filled in.
    val verticalPoints = shape.segments.flatMap(
      (segment: Segment) => {
        val line = (y: Int) => {
          Math.round(
            ((y - segment.start.y) / getSlope(segment)) + segment.start.x
          )
        }

        val range = getRange(segment.start.y, segment.end.y)
        range.map(
          (y: Int) => Point(line(y), y)
        )
      }
    )

    horizontalPoints.toSet.union(verticalPoints.toSet)
  }

  // Calculate the slope of a segment.
  private def getSlope(segment: Segment): Float = {
    val deltaY = (segment.start.y - segment.end.y).toFloat
    val deltaX = segment.start.x - segment.end.x

    deltaY / deltaX
  }

  // Get a range of numbers from a start point to an end point. This function
  // enables segments to be defined backwards without any issues.
  private def getRange(start: Int, end: Int): Range = {
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
