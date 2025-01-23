import java.awt.{Color, Graphics, Graphics2D}
import javax.swing.{JFrame, JPanel, WindowConstants}

object HSVWheel extends App {

  class HSVWheelPanel(size: Int) extends JPanel {

    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)
      val g2d = g.asInstanceOf[Graphics2D]

      val centerX = size / 2
      val centerY = size / 2
      val radius = size / 2

      for (y <- 0 until size; x <- 0 until size) {
        val dx = x - centerX
        val dy = y - centerY
        val distance = math.sqrt(dx * dx + dy * dy)

        if (distance <= radius) {
          // Вычисляем угол и насыщенность для пикселя
          val hue = (math.atan2(dy, dx) / (2 * math.Pi) + 0.5).toFloat // Преобразуем угол в диапазон [0, 1]
          val saturation = (distance / radius).toFloat                // Преобразуем расстояние в насыщенность
          val brightness = 1.0f                                       // Максимальная яркость

          // Преобразуем HSV в RGB и устанавливаем цвет пикселя
          val color = Color.getHSBColor(hue, saturation, brightness)
          g2d.setColor(color)

          // Рисуем закрашенный пиксель
          g2d.fillRect(x, y, 1, 1)
        }
      }
    }
  }

  // Настройка окна
  val size = 600
  val frame = new JFrame("HSV Wheel")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setSize(size, size)
  frame.setContentPane(new HSVWheelPanel(size))
  frame.setVisible(true)
}