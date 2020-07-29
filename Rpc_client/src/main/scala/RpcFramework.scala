import akka.actor.{Actor, ActorSystem, Props}
import java.net.{ServerSocket, Socket}
import java.io._
import java.lang.Class
import java.lang.reflect.Proxy





class RpcFramework {

}



object RpcFramework {

  class RpcServerSocket extends Actor{
    override def receive: Receive = {
      case  "start" =>
        /*
                try {
                  val RpcServerSocket = new ServerSocket(5050)
                  try while (true) {
                    val RpcSocekt = RpcServerSocket.accept()
                    val in = new ObjectInputStream(RpcSocekt.getInputStream)
                    val out = new ObjectOutputStream(RpcSocekt.getOutputStream)
                    try {
                      val methodName: String = in.readUTF

                      // 读取参数类型
                      val parameterTypes: Class[_] = in.readObject.asInstanceOf[Class[_]]
                      println("hello")
                      // 读取参数值
                      val arguments: Any = in.readObject()

                      // 获取方法
                      val method: Method = RpcSocekt.getClass.getMethod(methodName, parameterTypes)

                      // 处理结果
                      val result: Any = method.invoke(RpcSocekt, arguments)

                      // 写入结果
                    }catch {
                      case e: Exception =>
                        e.printStackTrace()
                    }finally {
                      if (RpcSocekt != null) RpcSocekt.close()
                      if (in != null) in.close()
                      if (out != null) out.close()
                    }

                  }catch {
                    case e1: IOException =>
                      e1.printStackTrace()
                  } finally if (RpcServerSocket != null) RpcServerSocket.close()

                }

         */
        println("Is ready")

      case _  => println("nothing")
    }
  }

  class RpcClientSocket extends Actor{
    override def receive: Receive = {
      case  "start" =>
        /*
                try {
                  val RpcServerSocket = new ServerSocket(5050)
                  try while (true) {
                    val RpcSocekt = RpcServerSocket.accept()
                    val in = new ObjectInputStream(RpcSocekt.getInputStream)
                    val out = new ObjectOutputStream(RpcSocekt.getOutputStream)
                    try {
                      val methodName: String = in.readUTF

                      // 读取参数类型
                      val parameterTypes: Class[_] = in.readObject.asInstanceOf[Class[_]]
                      println("hello")
                      // 读取参数值
                      val arguments: Any = in.readObject()

                      // 获取方法
                      val method: Method = RpcSocekt.getClass.getMethod(methodName, parameterTypes)

                      // 处理结果
                      val result: Any = method.invoke(RpcSocekt, arguments)

                      // 写入结果
                    }catch {
                      case e: Exception =>
                        e.printStackTrace()
                    }finally {
                      if (RpcSocekt != null) RpcSocekt.close()
                      if (in != null) in.close()
                      if (out != null) out.close()
                    }

                  }catch {
                    case e1: IOException =>
                      e1.printStackTrace()
                  } finally if (RpcServerSocket != null) RpcServerSocket.close()

                }

         */
        println("Is ready")

      case _  => println("nothing")
    }
  }

  def export(service : Any,port : Int): Unit ={
    if(service == null || port <=0 || port>=65535){
      throw new RuntimeException("Arguments error");
    }
    val system = ActorSystem("export")
    val exactor = system.actorOf(Props[RpcServerSocket],name = "exactor")
    exactor ! "start"

  }


  /*
    def refer[T](interfaceClass: Class[T], host: String, port: Int): T = {
      if (interfaceClass == null || !interfaceClass.isInterface || host == null || port <= 0 || port > 65535) throw new RuntimeException("Arguments error")
      System.out.println("正在调用远程服务")
      val proxy = Proxy.newProxyInstance(interfaceClass.getClassLoader, Array[Class[_]](interfaceClass), new InvocationHandler() {
        def invoke(proxy: Any, method: Method, args: Array[AnyRef]): Any = {
          var result = AnyRef.asInstanceOf[AnyRef]
          try {
            val socket = new Socket(host, port)
            val out = new ObjectOutputStream(socket.getOutputStream)
            val in = new ObjectInputStream(socket.getInputStream)
            try {
              out.writeUTF(method.getName)
              out.writeObject(method.getParameterTypes)
              out.writeObject(args)
              result = in.readObject()
            } catch {
              case e: Exception =>
                e.printStackTrace()
            } finally {
              if (socket != null) socket.close()
              if (out != null) out.close()
              if (in != null) in.close()
            }
          }
          result
        }


      }).asInstanceOf[T]
      proxy
    }


   */
  def test_srefer[T](interfaceClass: Class[T]): Unit = {
    val proxy = Proxy.getProxyClass(interfaceClass.getClassLoader,interfaceClass)
    println(proxy)
  }


  def main(args: Array[String]): Unit = {
    //export(AnyRef,10)
    //test_srefer(Man.getClass)
    val p = Proxy.getProxyClass(Man.getClass.getClassLoader,classOf[People])
    println(p)
  }
}



