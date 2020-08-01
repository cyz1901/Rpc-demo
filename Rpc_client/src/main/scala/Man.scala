import java.lang.reflect.InvocationHandler
class Man extends People {
  override def add(a: Int, b: Int):Int = {
    a+b+b
  }
}
object Man extends People {
  override def add(a: Int, b: Int): Int = {
    a+b
  }
}

