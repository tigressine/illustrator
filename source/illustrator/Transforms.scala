package illustrator

import illustrator.structures.Point
import illustrator.structures.Segment
import illustrator.structures.Shape

object Transforms {
  def combine(shapes: Shape*): Shape = {
    val segmentArrays = shapes.map(
      (shape: Shape) => {
        shape.segments
      }
    )

    Shape(
      segmentArrays.reduce(
        (leftSegments: Array[Segment], rightSegments: Array[Segment]) => {
          leftSegments ++ rightSegments
        }
      )
    )
  }

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
