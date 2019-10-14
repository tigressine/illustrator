import illustrator.Renderer
import illustrator.Shapes
import illustrator.Transforms

object Driver {

  // The main entry point of the program.
  def main(args: Array[String]): Unit = {

    // Configure the render function with function currying.
    val render = Renderer.render(width = 50, height = 15, char = '+')

    // Create a collection of shapes. Note that these shapes are all initially
    // positioned at the top-left origin.
    val ground = Shapes.createLine(width = 50, height = 0)
    val roof = Shapes.createTriangle(base = 10, height = 3)
    val frame = Shapes.createRectangle(width = 6, height = 5)
    val tree = Shapes.createTree(height = 5)

    // Combine the roof and frame into a house.
    val house = Transforms.combine(
      roof,
      Transforms.translate(frame, deltaX = 2, deltaY = 3)
    )

    // Position all shapes and combine them into a scene.
    val scene = Transforms.combine(
      Transforms.translate(tree, deltaX = 30, deltaY = 6),
      Transforms.translate(house, deltaX = 5, deltaY = 5),
      Transforms.translate(ground, deltaX = 0, deltaY = 14)
    )

    render(scene)
  }
}
