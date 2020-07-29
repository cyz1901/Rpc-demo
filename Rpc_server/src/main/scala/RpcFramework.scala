import akka.actor.{Actor, ActorSystem, Props}
import java.net.{ServerSocket, Socket}
import java.io._
import java.lang.reflect
import java.lang.reflect.Method

import scala.util.Try

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


  def export(service : Any,port : Int): Unit ={
    if(service == null || port <=0 || port>=65535){
      throw new RuntimeException("Arguments error");
    }
    val system = ActorSystem("export")
    val exactor = system.actorOf(Props[RpcServerSocket],name = "exactor")
    exactor ! "start"

  }



  def main(args: Array[String]): Unit = {
    export(AnyRef,10)
  }
}
