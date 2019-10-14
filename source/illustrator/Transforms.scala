package illustrator

import illustrator.structures.Point
import illustrator.structures.Segment
import illustrator.structures.Shape

object Transforms {
  
  // Combine shapes into a single shape.
  def combine(shapes: Shape*): Shape = {
    val segmentArrays = shapes.map(
      (shape: Shape) => {
        shape.segments
      }
    )

    // Create a new shape that contains all of the segments from all of the
    // input shapes.
    Shape(
      segmentArrays.reduce(
        (leftSegments: Array[Segment], rightSegments: Array[Segment]) => {
          leftSegments ++ rightSegments
        }
      )
    )
  }

  // Move a shape along the x and y axes.
  def translate(shape: Shape, deltaX: Int, deltaY: Int): Shape = {
    Shape(
      shape.segments.map(
        (segment: Segment) => {
          Segment(
            Point(segment.start.x + deltaX, segment.start.y + deltaY),
            Point(segment.end.x + deltaX, segment.end.y + deltaY)
          )
        }
      ).toArray
    )
  }
}
