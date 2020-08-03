import akka.actor.{Actor, ActorSystem, Props}
import java.net.{ServerSocket, Socket}
import akka.pattern.ask
import java.io._
import java.lang.reflect.{Constructor, InvocationHandler, Method, Proxy}
import scala.reflect.runtime.universe._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.language.postfixOps
import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer




class RpcFramework {

}



object RpcFramework {
  implicit val timeout: Timeout = Timeout(10 second)

  class RpcServerSocket extends Actor{
    override def receive: Receive = {
      case  "start" =>
        try {
          val RpcServerSocket = new ServerSocket(5051)
          println("远程服务暴露")
          try while (true) {
            val RpcSocekt = RpcServerSocket.accept()
            println("接收到Socket",RpcSocekt.getLocalPort,RpcSocekt.getInetAddress)
            val in = new ObjectInputStream(RpcSocekt.getInputStream)
            val out = new ObjectOutputStream(RpcSocekt.getOutputStream)
            try {
              val methodName: String = in.readUTF



              // 读取参数类型
              val parameterTypes: Array[Class[_]] = in.readObject.asInstanceOf[Array[Class[_]]]


              // 读取参数值
              val arguments: Array[AnyRef] = in.readObject.asInstanceOf[Array[AnyRef]]

              // 获取方法
              //val method = RpcServerSocket.getClass.getMethod(methodName, parameterTypes:_*)

              // 处理结果
              //val result: Any = method.invoke(RpcSocekt, arguments:_*)
              val result :Any = Man.getClass.getMethod(methodName, parameterTypes:_*).invoke(Man,arguments:_*)
              // 写入结果
              out.writeObject(result)
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


        println("Is ready")

      case _  => println("nothing")
    }
  }

  class RpcClientSocket extends Actor {
    override def receive: Receive = {
      case Array(x,y:String,z:Int) =>
        val proxy = Proxy.newProxyInstance(x.getClass.getClassLoader,x.getClass.getInterfaces,new InvocationHandler {
          override def invoke(proxy: Any, method: Method, args: Array[AnyRef]): AnyRef = {
            var result :AnyRef = null
            try {
              val socket = new Socket(y, z)
              println("socekt链接成功"+y+z)
              val out = new ObjectOutputStream(socket.getOutputStream)
              val in = new ObjectInputStream(socket.getInputStream)
              try {
                out.writeUTF(method.getName)
                out.writeObject(method.getParameterTypes)
                out.writeObject(args)
                result = in.readObject
                result
              } catch {
                case e: Exception =>
                  e.printStackTrace()
                  null
              } finally {
                if (socket != null) socket.close()
                if (out != null) out.close()
                if (in != null) in.close()
              }
            }


          }
        })


        sender() ! proxy





      case _  => println("nothing")
    }
  }

  def export(service : AnyRef,port : Int): Unit ={
    if(service == null || port <=0 || port>=65535){
      throw new RuntimeException("Arguments error");
    }
    val system = ActorSystem("export")
    val exactor = system.actorOf(Props[RpcServerSocket],name = "exactor")
    exactor ! "start"

  }


  def refer[T](interfaceClass: AnyRef,host :String,port :Int): T ={
    if(interfaceClass == null ||host == null || port <=0 || port>=65535){
      throw new RuntimeException("Arguments error");
    }
    val system = ActorSystem("refer")
    val reactor = system.actorOf(Props[RpcClientSocket],name = "reactor")
    val rr = reactor ? Array(interfaceClass,host,port)
    val result = Await.result(rr,2 second).asInstanceOf[T]
    result


  }




  /*
    def test_srefer[T](interfaceClass: AnyRef): Unit = {
      val proxy = Proxy.newProxyInstance(interfaceClass.getClass.getClassLoader,interfaceClass.getClass.getInterfaces,new InvocationHandler {
        override def invoke(proxy: Any, method: Method, args: Array[AnyRef]): AnyRef = {
          val result:AnyRef = method.invoke(interfaceClass)
          result
        }
      })
      println(proxy)
    }

   */
  def main(args: Array[String]): Unit = {
    //println(refer(Man,"127.0.0.1",5050))
    //refer(Man,"127.0.0.1",5050)
    val cc = new Man()

    println(classOf[People].getClass.getInterfaces)









  }

}



