package illustrator

import illustrator.structures.Point
import illustrator.structures.Segment
import illustrator.structures.Shape

object Shapes {

  // Create a line from the origin to (width, height).
  def createLine(width: Int, height: Int): Shape = {
    Shape(Array(Segment(Point(0, 0), Point(width, height))))
  }

  // Create a triangle with a base width and specified height.
  def createTriangle(base: Int, height: Int): Shape = {
    Shape(
      Array(
        Segment(Point(0, height), Point(base, height)),
        Segment(Point(0, height), Point(base / 2, 0)),
        Segment(Point(base, height), Point(base / 2, 0))
      )
    )
  }

  // Create a rectangle with a specified width and height.
  def createRectangle(width: Int, height: Int): Shape = {
    Shape(
      Array(
        Segment(Point(0, 0), Point(0, height)),
        Segment(Point(0, height), Point(width, height)),
        Segment(Point(width, height), Point(width, 0)),
        Segment(Point(width, 0), Point(0, 0))
      )
    )
  }

  // Create an ugly tree.
  def createTree(height: Int): Shape = {
    Shape(
      Array(
        Segment(Point(0, height / 2), Point(height / 2, 0)),
        Segment(Point(height / 2, 0), Point(height, height / 2)),
        Segment(Point(height, height / 2), Point((3 * height) / 2, 0)),
        Segment(Point((3 * height) / 2, 0), Point(2 * height, height / 2)),
        Segment(Point(height, height / 2), Point(height, (3 * height) / 2))
      )
    )
  }
}
