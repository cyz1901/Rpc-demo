import java.lang.reflect.InvocationHandler
class Man extends People {

}
object Man extends People {
  def add(a : Int,b : Int): Int ={
    a+b
  }
}