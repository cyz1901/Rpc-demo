import java.lang.reflect.InvocationHandler
class Man extends People {
  def add(): Unit ={
    println("hello world")
  }
}
object Man extends People {

}