import illustrator.Renderer
import illustrator.Shapes
import illustrator.Transforms
import scala.math

object Driver {
  def main(args: Array[String]): Unit = {
    val render = Renderer.render(width = 50, height = 15, char = '+')

    val ground = Shapes.createLine(width = 50, height = 0)
    val roof = Shapes.createTriangle(base = 10, height = 3)
    val frame = Shapes.createRectangle(width = 6, height = 5)
    val tree = Shapes.createTree(height = 5)

    val house = Transforms.combine(
      roof,
      Transforms.translate(frame, deltaX = 2, deltaY = 3)
    )

    val scene = Transforms.combine(
      Transforms.translate(tree, deltaX = 30, deltaY = 6),
      Transforms.translate(house, deltaX = 5, deltaY = 5),
      Transforms.translate(ground, deltaX = 0, deltaY = 14)
    )

    render(scene)
  }
}
